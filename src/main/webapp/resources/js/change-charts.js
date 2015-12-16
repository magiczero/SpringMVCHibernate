$(document).ready(function(){

	pm_stats_change(data);
    
});   
function pm_stats_change(data)
{
    $.plot($("#chart_change"), data,
    	{series: {pie: { show: true }},legend: { show: false }
	});
}
    