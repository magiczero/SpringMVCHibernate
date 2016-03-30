var relationId = "";
var dialogtype="";

function pm_cms_initselectdialog(dialogtype)
{
	dialogtype = dialogtype;
	$(".wrapper").append("<div class='dialog' id='b_popup_ci' style='display: none;' title='关联对象'>"
    		+"<div class='block-fluid dialog_block table-sorting clearfix'>"
    		+"	<div class='col-md-3'>"
    		+" 		<div id='treeview' ></div>"
    		+"  </div>"
    		+"  <div class='col-md-9'>" 
    		+"  	<div class='block-fluid'>"                   
    		+"      	<table class='table' id='ciTable'>"
    		+"          <thead>"
    		+"          <tr>"
    		+"             	<th width='60px'><input type='checkbox' name='checkall'/></th>"
    		+"				<th>名称</th>"
    		+"				<th width='120px'>使用部门</th>"
    		+"				<th width='60px'>状态</th>"
    		+"				<th width='80px'>审核状态</th>"
    		+"				<th width='80px'>删除状态</th>"
    		+"			</tr>"
    		+"          </thead>"
    		+"          <tbody>"
    		+"          </tbody>"
    		+"          </table>"
    		+"    </div>"
    		+"  </div> "
    		+"</div>"
    		+"</div>");
 	// 创建关联对象
    $("#b_popup_ci").dialog({
        autoOpen: false,
        width: 1000,
        height:500,
        buttons: { "选择": function () { 
        	pm_cms_ciselected(dialogtype);
        	$(this).dialog("close") 
        }, "关闭": function () { $(this).dialog("close") } }
    });
    $(".lnk_asset").click(function () {
            $("#b_popup_ci").dialog('open');
            return false;
    });
    $('#treeview').treeview({
    	url : ctx + '/cms/category/getjson?t=' + pm_random()
    });
    /*
	$.getJSON(ctx + '/cms/category/getjson?t=' + pm_random(), function(data){
		tree_json = data.json;
		$('#treeview').treeview({
            levels: 2, 
            data: tree_json,
            color: "#666666",
            nodeIcon: 'glyphicon glyphicon-list',
            onNodeSelected: function(event, node) {
            	pm_cms_initdialogtable(node.text.substring(0,node.text.indexOf(" ")));
            	return false;
            }
        });
	});	*/
}
function pm_cms_addRelations(relationid)
{
	relationId = relationid;
	$("#b_popup_ci").dialog('open');
	return false;
}
function pm_cms_ciselected(dialogtype)
{
	var checks = $("#ciTable input[name='checkbox']:checked");
	var ids = "";
	$.each(checks,function(i,v){
		if(ids!="")
			ids += ",";
		ids += $(v).attr("value");
	});
	//保存
	switch(dialogtype)
	{
	case 'ci':
		$.getJSON(ctx+'/cms/ci/saverelations?t='+pm_random(),{primary_id:ciId,secondary_ids:ids,relation_id:relationId},function(data){
			pm_cms_inittable(relationId);
		});
		break;
	case 'incident':
		//关联事件
		$.getJSON(ctx+'/itilrelation/save-incident-ci?t='+pm_random(),{primary_id:incidentId,secondary_ids:ids},function(data){
			pm_incident_getci();
		});
		break;
	case 'change':
		// 变更项
		$.getJSON(ctx+'/change/saveitems?t=' + pm_random(),{change_id:changeid,ci_ids:ids},function(data){
			pm_change_getci();
		});
		break;
	}

}
function pm_cms_initdialogtable(code)
{
	$.getJSON(ctx + '/cms/ci/list/'+code+'/?t=' + pm_random() , function(data) {
		$("#ciTable tbody tr").remove();
		if(data.list==null)
			return;
		var trs = "";
		for(i=0;i<data.list.length;i++)
		{
			trs += "<tr>"
			+"<td><input type='checkbox' name='checkbox' value='"+data.list[i]["id"]+"'/></td>"
			+"<td><a href='"+ctx+"/cms/ci/detail/"+data.list[i]["id"]+"'>"+data.list[i]["name"]+"</a></td>"
			+"<td>"+data.list[i]["departmentInUse"]+"</td>"
			+"<td>"+data.list[i]["statusName"]+"</td>"
			+"<td>"+data.list[i]["reviewStatusName"]+"</td>"
			+"<td>"+data.list[i]["deleteStatusName"]+"</td>"
			+"</tr>";
			
		}
		$("#ciTable tbody").append(trs); 
	});
}
function pm_cms_inittable(relationId)
{
	$.getJSON(ctx + '/cms/ci/getrelation/'+ciId+'/'+relationId+'?t=' + Math.random() , function(data) {
		if(data.cis==null)
			return;
		var trs = "";
		for(i=0;i<data.cis.length;i++)
		{
			$("#table_"+relationId+" tbody tr").remove();
			trs += "<tr>"
				+ "<td><a href='"+ctx+"/cms/ci/detail/"+data.cis[i]["id"]+"' target=_blank>"+data.cis[i]["name"]+"</a></td>"
				+ "<td>"+data.cis[i]["model"]+"</td>"
				+ "<td>"+data.cis[i]["userInMaintenance"]+"</td>"
				+ "<td>"+data.cis[i]["statusName"]+"</td>"
				+ "<td><a class='confirm' href='"+ctx+"/cms/ci/deleterelation?primary_id="+ciId+"&secondary_id="+data.cis[i]["id"]+"&relation_id="+relationId+"'><span class='glyphicon glyphicon-remove'></span></a></td>"
				+ "</tr>";           			
		}
		$("#table_"+relationId+" tbody").append(trs);
        $(".confirm").bind("click",function(){
        	if(!confirm("确定要执行该操作?"))
        		return false;
        });
	});
}
function pm_cms_getIncident()
{
	$.getJSON(ctx+'/itilrelation/get-ci-incident/'+ciId+'/?t='+pm_random(),function(data){
		var ids = data.incidents;
		$("#incidentTable tbody tr").remove();
		$.getJSON(ctx+'/incident/getjson/'+ids+'/?t='+pm_random(),function(data){
			
			var trs = "";
			$.each(data.incidents,function(i,v){
				trs += "<tr><td><a href='"+ctx+"/incident/view/"+v.id+"' target=_blank>"+v.abs+"</a></td>"
					+"<td>"+new Date(v.applyTime).format("yyyy-MM-dd HH:mm")+"</td>"
					+"<td>"+v.statusName+"</td></tr>";
			});
			$("#incidentTable tbody").append(trs);
		});
	});
}
function pm_cms_getChange()
{
		$("#changeTable tbody tr").remove();
		$.getJSON(ctx+'/change/getbyci/'+ciId+'/?t='+pm_random(),function(data){
			var trs = "";
			$.each(data.changes,function(i,v){
				trs += "<tr><td><a href='"+ctx+"/change/view/"+v.id+"' target=_blank>"+v.description+"</a></td>"
					+"<td>"+new Date(v.applyTime).format("yyyy-MM-dd HH:mm")+"</td>"
					+"<td>"+v.statusName+"</td></tr>";
			});
			$("#changeTable tbody").append(trs);
		});
}