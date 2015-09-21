$(document).ready(function(){

    if ($("#chart-3").length > 0) {

        var data = [];
        data[0] = { label: "主机", data: Math.floor(Math.random() * 100) + 1 };
        data[1] = { label: "备份", data: Math.floor(Math.random() * 100) + 1 };
        data[2] = { label: "网络", data: Math.floor(Math.random() * 100) + 1 };
        data[3] = { label: "安全", data: Math.floor(Math.random() * 100) + 1 };
        data[4] = { label: "应用", data: Math.floor(Math.random() * 100) + 1 };
        data[5] = { label: "机房", data: Math.floor(Math.random() * 100) + 1 };
        data[6] = { label: "终端", data: Math.floor(Math.random() * 100) + 1 };

        $.plot($("#chart-3"), data,
	    {
	        series: {
	            pie: { show: true }
	        },
	        legend: { show: false }
	    });

	}

	if ($("#lineChart").length > 0) {

	    var lctx = $("#lineChart").get(0).getContext("2d");
	    $("#lineChart").attr('width', $("#lineChart").parent('div').width()).attr('height', 300);

	    lineChart = new Chart(lctx).Line({
	        labels: ["一月", "二月", "三月", "四月", "五月", "六月", "七月"],
	        datasets: [
                    {
                        fillColor: "rgba(220,220,220,0.5)",
                        strokeColor: "rgba(220,220,220,1)",
                        pointColor: "rgba(220,220,220,1)",
                        pointStrokeColor: "#fff",
                        data: [65, 59, 90, 81, 56, 55, 40]
                    },
                    {
                        fillColor: "rgba(151,187,205,0.5)",
                        strokeColor: "rgba(151,187,205,1)",
                        pointColor: "rgba(151,187,205,1)",
                        pointStrokeColor: "#fff",
                        data: [28, 48, 40, 19, 96, 27, 100]
                    }
            ]
	    });
	}
    if ($("#barChart").length > 0) {

        var bctx = $("#barChart").get(0).getContext("2d");
        $("#barChart").attr('width', $("#barChart").parent('div').width()).attr('height', 300);

        barChart = new Chart(bctx).Bar({
            labels: ["一月", "二月", "三月", "四月", "五月", "六月", "七月"],
            datasets: [
                {
                    fillColor: "rgba(220,220,220,0.5)",
                    strokeColor: "rgba(220,220,220,1)",
                    data: [65, 59, 90, 81, 56, 55, 40]
                },
                {
                    fillColor: "rgba(151,187,205,0.5)",
                    strokeColor: "rgba(151,187,205,1)",
                    data: [28, 48, 40, 19, 96, 27, 100]
                },
                {
                    fillColor: "rgba(151,187,205,0.8)",
                    strokeColor: "rgba(151,187,205,1)",
                    data: [20, 18, 20, 25, 10, 40, 50]
                }
            ]
        });

    }
});

function update() {
    plot.setData([getRandomData()]);
    // since the axes don't change, we don't need to call plot.setupGrid()
    plot.draw();

    setTimeout(update, updateInterval);
}

function getRandomData() {
    if (data.length > 0)
        data = data.slice(1);

    // do a random walk
    while (data.length < totalPoints) {
        var prev = data.length > 0 ? data[data.length - 1] : 50;
        var y = prev + Math.random() * 10 - 5;
        if (y < 0)
            y = 0;
        if (y > 100)
            y = 100;
        data.push(y);
    }

    // zip the generated y values with the x values
    var res = [];
    for (var i = 0; i < data.length; ++i)
        res.push([i, data[i]])
    return res;
}

function showTooltip(x, y, contents) {
    $('<div class="ct">' + contents + '</div>').css({
        position: 'absolute',
        display: 'none',
        top: y,
        left: x + 10,
        border: '2px solid #333',
        padding: '2px',
        'background-color': '#ffffff',
        'border-radius': '2px',
        color: '#333'
    }).appendTo("body").fadeIn(200);
}    
    