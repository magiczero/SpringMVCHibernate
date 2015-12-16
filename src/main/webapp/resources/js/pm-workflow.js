$(document).ready(function(){
	// 流程跟踪
	$("#b_popup_trace").html("<div class='block dialog_block  uploads' id='trace_content'></div>");
    $("#b_popup_trace").dialog({
    	autoOpen: false,
    	width: 800,
    	height: 400,
    	buttons: { "关闭": function () { $(this).dialog("close") } }
   });
   $(".lnk_trace").click(function () {
	   var src= ctx+ '/diagram-viewer/index.html?processDefinitionId=' 
	   	+ $(this).attr('pdid') + '&processInstanceId=' + $(this).attr('pid');
	   $("#trace_content").html("<iframe src='"+ src +"' width='100%' height='265'/>");
	   $("#b_popup_trace").dialog('open');
   });
});
function pm_workflow_inittracedialog()
{
	// 流程跟踪
	$(".wrapper").append("<div class='dialog' id='b_popup_trace' style='display: none;' title='流程跟踪'>"
	    				+"<div class='block dialog_block  uploads' id='trace_content'>"                           
	    				+"</div></div>");
    $("#b_popup_trace").dialog({
    	autoOpen: false,
    	width: 800,
    	height: 400,
    	buttons: { "关闭": function () { $(this).dialog("close") } }
   });
   $(".lnk_trace").click(function () {
	   var src= ctx+ '/diagram-viewer/index.html?processDefinitionId=' 
	   	+ $(this).attr('pdid') + '&processInstanceId=' + $(this).attr('pid');
	   $("#trace_content").html("<iframe src='"+ src +"' width='100%' height='265'/>");
	   $("#b_popup_trace").dialog('open');
   });
}
