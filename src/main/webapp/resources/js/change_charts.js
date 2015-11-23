$(document).ready(function(){

    if ($("#chart-3").length > 0) {

        var data = [];
        data[0] = { label: "正常", data: Math.floor(Math.random() * 100) + 1 };
        data[1] = { label: "紧急", data: Math.floor(Math.random() * 100) + 1 };
        data[2] = { label: "已加速", data: Math.floor(Math.random() * 100) + 1 };
        data[3] = { label: "潜在的", data: Math.floor(Math.random() * 100) + 1 };
        data[4] = { label: "无影响", data: Math.floor(Math.random() * 100) + 1 };
        data[5] = { label: "标准", data: Math.floor(Math.random() * 100) + 1 };

        $.plot($("#chart-3"), data,
	    {
	        series: {
	            pie: { show: true }
	        },
	        legend: { show: false }
	    });

	}


    if ($("#barChart").length > 0) {

        var bctx = $("#barChart").get(0).getContext("2d");
        $("#barChart").attr('width', $("#barChart").parent('div').width()).attr('height', 300);

        barChart = new Chart(bctx).Bar({
            labels: ["新建", "待处理", "构建中", "审批中", "计划中", "实施中", "已实施","已关闭"],
            datasets: [
                {
                    fillColor: "rgba(151,187,205,0.5)",
                    strokeColor: "rgba(151,187,205,1)",
                    data: [28, 48, 40, 19, 96, 27, 60,70]
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
    