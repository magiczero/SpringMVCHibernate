$(document).ready(function(){

	pm_stats_knowledge(data);
    
});   
function pm_stats_knowledge(data)
{
    $.plot($("#chart_knowledge"), data,
    	{series: {pie: { show: true }},legend: { show: false }
	});
}
    