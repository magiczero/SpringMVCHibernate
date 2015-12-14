var tree_json = "";

$(document).ready(function(){
    //  
	$("#b_popup_select").html("<div class='block dialog_block messages '>"
			+"<div>"
			+"<div id='treeview'></div>"
			+"</div></div>");
	
    $("#b_popup_select").dialog({
        autoOpen: false,
        width: 400,
        height:500,
        buttons: { "确定": function () { $(this).dialog("close") } }
    });
});  
function act_dialog_select(type,name)
{
	$.getJSON(ctx + '/system/syscode/getjson/' + type + '?t=' + pm_random(), function(data){
		tree_json = data.json;
		$('#treeview').treeview({
            levels: 2, 
            data: tree_json,
            color: "#666666",
            nodeIcon: 'glyphicon glyphicon-list',
            onNodeSelected: function(event, node) {
            	if(!node.nodes)
            		$("input[name='"+name+"']").attr("value",node.text.substring(0,node.text.indexOf(" ")));
            	else
            		return false;
            }
        });
		$("#b_popup_select").dialog('open');
	});
	
	return false;
}
function act_dialog_ciselect(name,isNode)
{
	$.getJSON(ctx + '/cms/category/getjson?t=' + pm_random(), function(data){
		tree_json = data.json;
		$('#treeview').treeview({
            levels: 2, 
            data: tree_json,
            color: "#666666",
            nodeIcon: 'glyphicon glyphicon-list',
            onNodeSelected: function(event, node) {
            	if(isNode)
            	{
	            	if(!node.nodes)
	            		$("input[name='"+name+"']").attr("value",node.text.substring(0,node.text.indexOf(" ")));
	            	else
	            		return false;
            	}
            	else
            	{
            		$("input[name='"+name+"']").attr("value",node.text.substring(0,node.text.indexOf(" ")));
            		pm_account_setProperty(node.text.substring(0,node.text.indexOf(" ")));
            	}
            }
        });
		$("#b_popup_select").dialog('open');
	});
	
	return false;
}