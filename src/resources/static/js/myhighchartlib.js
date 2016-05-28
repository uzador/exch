function myhighchart() {
    var series1 = [[1,1], [2,2], [3,3], [4,4], [5,5]];
//    var series2 = [3, 4, 5, 6, 5, 7];
//    var series3 = [5, 6, 8, 9, 7, 9];
//    var series4 = [7, 8, 9, 11, 10, 11];

    $('#myChart').highcharts({
       chart: {
          type: 'line'
       },
       series: [
       { data: series1 }
//          { data: series2 },
//          { data: series3 },
//          { data: series4 }
          ]
    });
}