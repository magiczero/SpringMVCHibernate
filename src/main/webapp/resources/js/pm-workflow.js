
/**
 * 初始化流程步骤查看对话框，并绑定事件到 ".lnk_trace"
 */
function pm_workflow_inittracedialog(nwidth,nheight)
{
	if(nwidth==null)
		nwidth = 800;
	if(nheight==null)
		nheight = 400;
	// 流程跟踪
	$(".wrapper").append("<div class='dialog' id='b_popup_trace' style='display: none;' title='流程跟踪'>"
	    				+"<div class='block dialog_block  uploads' id='trace_content'>"                           
	    				+"</div></div>");
    $("#b_popup_trace").dialog({
    	autoOpen: false,
    	width: nwidth,
    	height: nheight,
    	buttons: { "关闭": function () { $(this).dialog("close") } }
   });
   $(".lnk_trace").click(function () {
	   var src= ctx+ '/diagram-viewer/index.html?processDefinitionId=' 
	   	+ $(this).attr('pdid') + '&processInstanceId=' + $(this).attr('pid');
	   $("#trace_content").html("<iframe src='"+ src +"' width='100%' height='265'/>");
	   $("#b_popup_trace").dialog('open');
   });
}
