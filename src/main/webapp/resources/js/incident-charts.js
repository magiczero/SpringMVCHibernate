$(document).ready(function(){

	pm_stats_incident(data);
    
});   
function pm_stats_incident(data)
{
    $.plot($("#chart_incident"), data,
    	{series: {pie: { show: true }},legend: { show: false }
	});
}
    