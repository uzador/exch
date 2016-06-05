function myhighchart(chartNames) {
    var seriesOptions = [],
        seriesCounter = 0,
        names = chartNames;

    $.each(names, function (i, name) {
        var splitedName = name.split('-');

        $.getJSON('http://localhost:8080/get-json?marketname=' + splitedName[1] + '&secid=' + splitedName[0], function (data) {

            seriesOptions[i] = {
                name: name,
                data: data
            };

            seriesCounter += 1;

            if (seriesCounter === names.length) {
                createChart();
            }
        });
    });

    function createChart() {

        $('#myChart').highcharts('StockChart', {

            rangeSelector: {
                selected: 4
            },

            yAxis: {
                labels: {
                    formatter: function () {
                        return (this.value > 0 ? ' + ' : '') + this.value + '%';
                    }
                },
                plotLines: [{
                    value: 0,
                    width: 2,
                    color: 'silver'
                }]
            },

            plotOptions: {
                series: {
                    compare: 'percent'
                }
            },

            tooltip: {
                pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.change}%)<br/>',
                valueDecimals: 2
            },

            series: seriesOptions
        });
    }
}