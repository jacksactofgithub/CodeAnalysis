/*==================================
    AjaxChart
    依赖：highcharts.js
====================================
用法
var oneAjaxChart = new AjaxChart({
    wrapperId: 'elementId',
    chartType: 'column',
    ajaxUrl: 'yourAjaxUrl',
    params: {
        param1: 1,
        param2: function(){ reutrn 2; }
    }
});
oneAjaxChart.refresh({
    chartData: {series: []},                //optional
    clickHandler: function(xLabel, y){},    //optional
    afterUpdate: function(){}               //optional
});
如果有chartData，则直接使用该数据更新chart，不进行ajax

NOTE: 
    chartType: 仅限于'line', 'pie', 'column', 'histogram', 'area'
    params: 可以是值 或 function, function则取返回值
    ajax result format: {series: [{name: "seriesName", data: [["label", value], ...]}, ...]}

=====================================*/

(function($){
    // ================================
    // Highchart Options 全局设置
    // ================================
    var chartOptionsList = {
        //全局Highcharts配置
        global:{
            title: null,
            xAxis: {
                //注意，这里假设每个series的数据项都一样，因此取第一个series来作为分类即可。
                categories: function(){
                    var cate = [];
                    //用data数组生成categories保证不同类型的图data格式一致
                    $.each(series[0].data, function(k,v){
                        cate.push(v[0]);
                    });
                    return cate;
                }
            },
            tooltip: {
                pointFormat: '<span style="color:{series.color}">{series.name}</span>: {point.y} <br/>'
            },
            legend: {
                enabled: false
                // ,layout: 'vertical'
            },
            yAxis:{
                title: {
                    enabled: false
                }
                ,min: 0
            }
        },
        //折线图配置项
        line: {
            chart: { type:'line' },          
            plotOptions: {
                line: {
                    dataLabels: {
                        enabled: true
                    }
                }
            },  
            legend: {
                enabled: false
            }
        },
        //饼图配置项
        pie: {
            chart: { type:'pie' },
            tooltip: {
                pointFormat: '<span style="color:{series.color}">{series.name}</span>: {point.percentage:,.1f}% <br/>'
            }
        },
        //柱形图配置项
        column: {
            chart: { type:'column' },
            plotOptions: {
                column: {
                    // cursor: 'pointer',
                    point: {
                        events: {
                            click: function() {
                                // alert(this.name);
                            }
                        }
                    },
                    dataLabels: {
                        enabled: true
                    }
                }
            }
        },
        //直方图配置项
        histogram: {
            chart: { type:'column' },
            plotOptions: {
                column: {
                    dataLabels: {
                        enabled: true
                    }
                },
                series: {
                    groupPadding: 0,
                    pointPadding: 0
                }
            }
        },
        //区域图
        area: {
            chart:{ type:'area' },
            plotOptions: {
                area: {
                    marker: {
                        enabled: false,
                        symbol: 'circle',
                        radius: 2,
                        states: {
                            hover: {
                                enabled: true
                            }
                        }
                    }
                }
            }
        }
    };

    // set default global options
    if(typeof Highcharts !== 'undefined'){
        Highcharts.setOptions(chartOptionsList['global']);
    }




    // ================================
    // Ajax Highchart
    // ================================
    var AjaxChart = function(config){
        this._id = config.wrapperId;
        this._url = config.ajaxUrl;
        this._params = config.params;
        this._chartType = config.chartType;
    };
    AjaxChart.prototype = {
        _getAjaxParams: function(){
            if(typeof this._params === 'undefined'){
                return {};
            }

            var result = {};
            for(key in this._params){
                result[key] = (typeof this._params[key] == 'function') ? (this._params[key])() : this._params[key];
            }
            return result;
        },

        _updateChart: function(result, handler){
            if('series' in result == false){
                return false;
            }
            
            // 选择图类型
            var chartOptions = $.extend({}, chartOptionsList['global'], chartOptionsList[this._chartType]);
            // 选择容器
            chartOptions.chart.renderTo = this._id;

            /* S1 - 处理series */
            // patch: 把value=0的数据项设置为null，这样可以使它不在表格上显示
            for (var i = result['series'].length - 1; i >= 0; i--) {
                var data = result['series'][i]['data']
                for (var j = data.length - 1; j >= 0; j--) {
                    if(data[j][1] == 0){
                        data[j][1] = null;
                    }
                };
            };
            // 载入数据项
            chartOptions.series = result['series']; 

            //如果是多个series的，显示legend(图表说明)
            if(chartOptions.series.length > 1){
                chartOptions.legend.enabled = true;
            }
            else{
                // NOTE: 默认值会被前一次覆盖
                chartOptions.legend.enabled = false;
            }

            /* S2 - 处理xAxis categories */
            if('categories' in result){
                if(chartOptions.xAxis instanceof Array){
                    for (var i = chartOptions.xAxis.length - 1; i >= 0; i--) {
                        chartOptions.xAxis[i].categories = result['categories'];
                    };
                }
                else{
                    chartOptions.xAxis.categories = result['categories'];
                }
            }

            /* S3 - 处理events */
            if(typeof handler === 'function'){
                chartOptions.plotOptions = $.extend(chartOptions.plotOptions, {
                    series: {
                        cursor: 'pointer',
                        events: {
                            click: function(evt){
                                var xLabel = evt.point.name || evt.point.category;
                                handler(xLabel, evt.point.y);
                            }
                        }
                    }
                });
            }

            // 生成Highcharts对象
            var chart = new Highcharts.Chart(chartOptions);
        },

        refresh: function(opts){
            var chartData = opts && opts.chartData;
            var clickHandler = opts && opts.clickHandler;
            var afterUpdate = opts && opts.afterUpdate;

            // ajax更新
            if(typeof chartData === 'undefined' && this._url){
                var params = this._getAjaxParams();
                // 变量的snapshot（ajax返回后this会指向xmlhttpresponse）
                var that = this;

                $.post(
                    this._url,
                    params,
                    function(json){
                        var result;
                        if(typeof json === 'string'){
                            result = $.parseJSON(json);
                        }
                        if(typeof json === 'object'){
                            result = json;
                        }
                        if(typeof result !== 'undefined'){
                            that._updateChart(result, clickHandler);
                            afterUpdate && afterUpdate();
                        }
                    }
                );
            }
            // 非ajax更新
            else{
                this._updateChart(chartData, clickHandler);
                afterUpdate && afterUpdate();
            }
        },

        getId: function(){
            return this._id;
        }
    };


    // ================================
    // export
    // ================================
    window.AjaxChart = AjaxChart;

})(jQuery);