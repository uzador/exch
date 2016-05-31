function myhighchart() {
    $.getJSON('http://localhost:8080/get-json', function (series1) {

        $('#myChart').highcharts({
           chart: {
              type: 'line',
              zoomType: 'x'
           },
           title: {
               text: 'Volume to Date rate over time'
           },
           xAxis: {
               type: 'datetime',
           },
           yAxis:
               {title: {
                   text: 'Volume rate'
               }
           },
//           legend: {
//               enabled: false
//           },
           series: [{data: series1}]
        });

    });
}