function myhighchart(chartNames) {
//    $.ajaxSetup({
//        async: false
//    });
//
//    var secidCount = 0;
//    var marketNameCount = 0;
//    $.getJSON('http://localhost:8080/get-secids', function (data) {
//        secidCount = data.length
//
//        $.getJSON('http://localhost:8080/get-marketname-by-secid?secid=' + secIdValue, function (marketNameData) {
//            secidCount = data.length
//        });
//    });
//
//    $.ajaxSetup({
//        async: true
//    });

    $("#chartnames");
    /*$.getJSON('http://localhost:8080/get-secids', function (data) {
        $.each(data, function(index, value) {
            $('#checkboxes').append('<input type="checkbox" name="' + value + '" id="' + index + '"/> ' + value + '<br />');
        });
    });

    $(document).on("click", "#checkboxes input", function(evt) {
            var txSeleccionado = $(this).text();
            console.log($(this));
    });*/

    /*$.getJSON('http://localhost:8080/get-json', function (series1) {
        var options = {
            chart: {
                renderTo: 'myChart',
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
           legend: {
               layout: 'vertical',
               align: 'right',
               verticalAlign: 'middle',
               enabled: true
           },
           series: [
                {   name: 'SBER',
                    data: [[1357603200000,25413208],[1357689600000,29540962],[1357776000000,22741598],[1357862400000,19799867],[1358121600000,28709276],[1358208000000,26013001],[1358294400000,31599346]]},
                {
                    name: 'SBER',
                    data: [[1357603200000,15413208],[1357689600000,19540962],[1357776000000,12741598],[1357862400000,29799867],[1358121600000,18709276],[1358208000000,16013001],[1358294400000,11599346]]}
                ]
        }

        var chart = new Highcharts.Chart(options);
    });*/



            var seriesOptions = [];

            var counter = 0;
            $.getJSON('http://localhost:8080/get-secids', function (secIdData) {
               $.each(secIdData, function(secIdIndex, secIdValue) {
                   $.getJSON('http://localhost:8080/get-marketname-by-secid?secid=' + secIdValue, function (marketNameData) {
                       $.each(marketNameData, function(marketNameIndex, marketNameValue) {
                           $.getJSON('http://localhost:8080/get-json?marketname=' + marketNameValue + '&secid=' + secIdValue, function (chartData) {
                               seriesOptions[counter++] = {
                                   name: secIdValue + ' ' + marketNameValue,
                                   date: chartData
                               }
                           });
                        });
                   });
               });
            });

            seriesOptions = [[1357603200000,15413208],[1357689600000,19540962],[1357776000000,12741598],[1357862400000,29799867],[1358121600000,18709276],[1358208000000,16013001],[1358294400000,11599346]];

            var options = {
                chart: {
                    renderTo: 'myChart',
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
               legend: {
                   layout: 'vertical',
                   align: 'right',
                   verticalAlign: 'middle',
                   enabled: true
               },
               series: seriesOptions
            }

            var chart = new Highcharts.Chart(options);
}