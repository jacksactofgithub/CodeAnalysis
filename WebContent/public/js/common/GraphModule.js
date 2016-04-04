/*==================================
    GraphModule for svg
    依赖：snap.svg.js
====================================
用法
    var graphData = {
        "1": {
            "preds": [],
            "succs": [2]
        },
        "2": {
            "preds": [1],
            "succs": [3]
        }
    };
    var svgGraph = new GraphModule('svgId', graphData);

=====================================*/

(function(Snap){
    // global constants
    var STROKE_WIDTH = 2;
    var TEXT_FONT_SIZE = 16;
    var TEXT_XBIAS = -4;
    var TEXT_YBIAS = 5;

    var RADIUS = 20;
    var BASE_XASIS = 200;
    var BASE_INTERVAL = 4*RADIUS;


    var GraphModule = function(svgId, graphData){
        this.svgId = svgId;
        this.svg = Snap('#' + svgId);
        this.graphData = graphData;

        this.posMap = {};
        this.numY = 1;
        this.curY = 0;

        this.getNextPosition = function(number, flows){
            if(typeof this.posMap[number] === 'undefined'){
                this.posMap[number] = [BASE_XASIS, this.curY + BASE_INTERVAL];
                this.curY = this.posMap[number][1];
                this.numY++;

                // 对后继数大于等于2的子节点
                if(typeof flows !== 'undefined' && flows.length > 1){
                    var len = flows.length;
                    var py = this.curY + BASE_INTERVAL;
                    this.curY = py;
                    this.numY++;

                    for(var i=0; i<len; i++){
                        var px = (i % 2 == 0) ? BASE_XASIS - 1.414*BASE_INTERVAL : BASE_XASIS + 1.414*BASE_INTERVAL;
                        this.posMap[flows[i]] = [px, py];
                    }
                }
            }
            return this.posMap[number];
        };

        this.drawNode = function(number, px, py){
            this.svg.paper.circle(px, py, RADIUS)
                .attr({
                    fill: '#99bfe6',
                    stroke: '#000',
                    strokeWidth: STROKE_WIDTH
                });
            this.svg.paper.text(px + TEXT_XBIAS, py + TEXT_YBIAS, number)
                .attr({
                    fontSize: TEXT_FONT_SIZE
                });
        };

        this.drawLine = function(number1, number2){
            var pos1 = this.posMap[number1];
            var pos2 = this.posMap[number2];

            var x1 = pos1[0],
                y1 = pos1[1]+RADIUS,
                x2 = pos2[0],
                y2 = pos2[1]-RADIUS;

            if(pos1[0] > pos2[0]){
                x2 = pos2[0] + RADIUS;
                y2 = pos2[1];
            }
            if(pos1[0] < pos2[0]){
                x2 = pos2[0] - RADIUS;
                y2 = pos2[1];
            }
            if(pos1[1] > pos2[1]){
                y1 = pos1[1] - RADIUS;
                // 反向line
            }

            this.svg.paper.line(x1, y1, x2, y2)
                .attr({
                    stroke: '#000',
                    strokeWidth: STROKE_WIDTH
                });
        };

        this.draw = function(graphData){
            // draw node
            for(var node in graphData){
                var number = node;
                var points = this.getNextPosition(number, graphData[node]['succs']);
                this.drawNode(number, points[0], points[1]);
            }

            // draw line
            for(var node in graphData){
                var from = node;
                for(var j=0; j<graphData[node]['succs'].length; j++){
                    var to = graphData[node]['succs'][j];
                    this.drawLine(from, to);
                }
            }

            // update svg height
            document.getElementById(this.svgId).setAttribute('height', this.numY * BASE_INTERVAL);
        }

        // execution
        this.draw(this.graphData);
    };

    
    GraphModule.prototype = {
        // for events and effects
    };


    // ================================
    // export
    // ================================
    window.GraphModule = GraphModule;

})(Snap);