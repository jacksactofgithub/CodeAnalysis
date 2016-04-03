/*==================================
    CFGModule for svg
    依赖：jquery, hljs, myGlobal
    假设：一个页面只能有一个CFGModule实例
====================================
用法
    var codeData = [
        {
            'codes': 'xxxx',
            'blocks': [1]
        },
        {
            'codes': 'yyyy',
            'blocks': [2, 3]
        }
    ];
    var $svg = $('svg#svgId');
    var cfg = new CFGModule($svg, codeData, 'svgWrapper');
    // NOTE: svgWrapper is the parent div of svg element
    // NOTE: codeData为空时，节点click不显示代码

=====================================*/

(function($, hljs, myGlobal){
    // constants
    var HILIGHT_COLOR = '#08c';
    var DEFAULT_EDGE_COLOR = 'black';

    var CFG_BLOCK_ID_PREFIX = 'cfgCodeBlock';
    var CFG_BLOCK_CLASS = 'cfg-code-block';


    var CFGModule = function($svg, codeData, wrapperId){
        this.wrapperId = wrapperId;
        this.init($svg, codeData);
    };
    CFGModule.prototype = {
        init: function($svg, codeData){
            this.$svg = $svg;
            this.codeData = codeData;

            // codeData不空时，添加node点击事件
            if(codeData.length > 0){
                // 为了让event函数里能访问到this
                var that = this;

                var watchNodeEvent = function(evt){
                    var nodeNo = $(this).find('title').text();
                    // that.toggleNode($(this));
                    if($('#' + CFG_BLOCK_ID_PREFIX + nodeNo).length == 0){
                        that.showNodeCodes(nodeNo, evt.clientY + 20, evt.clientX + 20);
                    }
                    else{
                        that.closeNodeCodes(nodeNo);
                    }
                };

                var closeWatchEvent = function(evt){
                    var eleId = $(this).closest('.' + CFG_BLOCK_CLASS).prop('id');
                    var nodeNo = eleId.split(CFG_BLOCK_ID_PREFIX)[1];
                    that.closeNodeCodes(nodeNo);
                };

                // watch
                this.$svg.find('g[class="node"]').css('cursor', 'pointer')
                    .unbind('click').bind('click', watchNodeEvent);

                // close watch
                $('#' + this.wrapperId).find('.' + CFG_BLOCK_CLASS + ' .close')
                    .live('click', closeWatchEvent);
            }
        },
        reset: function($svg, codeData){
            this.init($svg, codeData);
            // clear all popups
            $('#' + this.wrapperId).find('.' + CFG_BLOCK_CLASS).remove();
        },


        getNode: function(nodeNo){
            var nodes = this.$svg.find('g[class="node"]');
            for(var i=0; i<nodes.length; i++){
                if($(nodes[i]).find('title').text() == nodeNo){
                    return $(nodes[i]);
                }
            }
        },
        getEdge: function(fromNo, toNo){
            // not used
        },


        setNodeColor: function($node, color){
            var $polygon = $node.find('polygon');
            $polygon.attr('data-fill', $polygon.attr('fill'));
            $polygon.attr('fill', color);
            // 节省内存
            $polygon = null;
        },
        resetNodeColor: function($node){
            var $polygon = $node.find('polygon');
            if(typeof $polygon.attr('data-fill') !== 'undefined'){
                $polygon.attr('fill', $polygon.attr('data-fill'));
            }
            // 节省内存
            $polygon = null;
        },
        resetAllNodes: function(){
            var that = this;
            that.$svg.find('g[class="node"]').each(function(){
                that.resetNodeColor($(this));
            });
            that = null;
        },


        setEdgeColor: function($edge, color){
            var $path = $edge.find('path');
            var $polygon = $edge.find('polygon');
            $path.attr('stroke', color);
            $polygon.attr('stroke', color);
            $polygon.attr('fill', color);
            // 节省内存
            $path = null;
            $polygon = null;
        },
        resetEdgeColor: function($edge){
            this.setEdgeColor($edge, DEFAULT_EDGE_COLOR);
        },
        resetAllEdges: function(){
            var that = this;
            that.$svg.find('g[class="edge"]').each(function(){
                that.resetEdgeColor($(this));
            });
            that = null;
        },


        // nodes: ['1', '2', '3', ...] e.g.
        highlightNodes: function(nodes){
            var that = this;

            that.$svg.find('g[class="node"]').each(function(){
                that.resetNodeColor($(this));
                var nodeNo = $(this).find('title').text();

                for(var i=0; i<nodes.length; i++){
                    if(nodes[i] == nodeNo){
                        that.setNodeColor($(this), HILIGHT_COLOR);
                        break;
                    }
                }
            });

            that = null;
        },
        // paths e.g. ['1-2-3', '1-2-4'] or ['1-2-3'] or ['1']
        highlightPaths: function(paths){
            var that = this;

            // 同时highlight多条路径
            var pathNodes = [];  // [[1, 2, 3], [1, 2, 4]]
            var nodes = [];  // [1, 2, 3, 1, 2, 4]

            for(var i=0; i<paths.length; i++){
                var temp = paths[i].split('-');
                nodes = nodes.concat(temp);
                pathNodes.push(temp);
            }

            // for node
            that.highlightNodes(nodes);

            // for edge
            that.$svg.find('g[class="edge"]').each(function(){
                that.resetEdgeColor($(this));

                for(var i=0; i<pathNodes.length; i++){
                    var found = false;

                    for(var j=0; j<pathNodes[i].length-1; j++){
                        if($(this).find('title').text() == [pathNodes[i][j], pathNodes[i][j+1]].join('->')){
                            that.setEdgeColor($(this), HILIGHT_COLOR);
                            found = true;
                            break;
                        }
                    }

                    if(found){
                        break;
                    }
                }
            });

            that = null;
        },


        toggleNode: function($node){
            if(typeof $node.attr('data-active') === 'undefined'){
                $node.attr('data-active', 'active');
                this.setNodeColor($node, HILIGHT_COLOR);
            }
            else{
                $node.removeAttr('data-active');
                this.resetNodeColor($node);
            }
        },
        showNodeCodes: function(nodeNo, top, left){
            // 反向获取code
            var codes = [];
            for(var i=0, len=this.codeData.length; i<len; i++){
                var blocks = (this.codeData)[i]['blocks'];
                for(var j=0; j<blocks.length; j++){
                    if(blocks[j] == nodeNo){
                        codes.push((this.codeData)[i]['codes']);
                    }
                }
            }

            // 生成popup
            var $wrapper = $('#' + this.wrapperId);
            if($wrapper.find('#' + CFG_BLOCK_ID_PREFIX + nodeNo).length == 0){
                // 生成codes
                var $codeDiv = $('<div class="codes"></div>');
                for(var i=0; i<codes.length; i++){
                    $codeDiv.append('<pre><code>' + myGlobal.codeReplace(codes[i]) + '</pre></code>');
                }

                // hljs初始化
                $codeDiv.find('code').each(function(i, block) {
                    hljs.highlightBlock(block);
                });

                var $codeBlock = $('<div class="' + CFG_BLOCK_CLASS + '" id="' + CFG_BLOCK_ID_PREFIX + nodeNo + '"></div>');
                $codeBlock.append('<div class="close"><a href="javascript:void(0)" title="关闭">x</a></div>');
                $codeBlock.append($codeDiv);

                $codeBlock.css({
                    'top': top + 'px',
                    'left': left + 'px'
                });
                $wrapper.append($codeBlock);
            }
        },
        closeNodeCodes: function(nodeNo){
            $('#' + this.wrapperId).find('#' + CFG_BLOCK_ID_PREFIX + nodeNo).remove();
        }
    };


    // export
    window.CFGModule = CFGModule;

})(jQuery, hljs, myGlobal);