function pm_change_getci()
{
	$.getJSON(ctx+'/change/getitems/'+changeid+'/?t='+pm_random(),function(data){
		$("#itemTable tbody tr").remove();
		var trs = "";
		var items = data.items;
		var ids="";
		$.each(items,function(i,v){
			if(ids!="")
				ids += ",";
			ids += v.ciId; 
		});
		$.getJSON(ctx+'/cms/ci/getjson/'+ids+'/?t='+pm_random(),function(data){
			var map = new Object();
			$.each(data.cis,function(i,v){
				map[v.id] = v;
			});
			$.each(items,function(i,v){
				
				trs += "<tr>"
					+"<td>"+(i+1)+"</td>"
					+"<td><a href='"+ctx+"/cms/ci/detail/"+v.ciId+"' target=_blank>"+map[v.ciId].name+"</a></td>";
				if( v.propertiesName==null )
					trs += "<td></td>";
				else
					trs += "<td>"+v.propertiesName+"</td>";
				trs += "<td><a href='#' onclick='pm_change_setProperty("+v.id+","+v.ciId+",\""+v.properties+"\")'><span class='glyphicon glyphicon-pencil'></span></a> <a href='#' onclick='pm_change_removeci("+v.id+")'><span class='glyphicon glyphicon-remove'></span></a></td>"
					+"</tr>";
			});
			$("#itemTable tbody").append(trs);
		});
	});
}
function pm_change_removeci(id)
{
	$.getJSON(ctx+'/change/deleteitem/'+id+'?t='+pm_random(),function(data){
		pm_change_getci();
	});
	return false;
}
function pm_change_setProperty(id,ciid,properties){
	
	$.getJSON(ctx + '/cms/ci/getproperty/' + ciid +'?t=' + pm_random(),function(data){
		$("#sel_properties option").remove();
		$("#sel_field option").remove();
		// 参数
		$.each(data.properties,function(key,value){
			$.each(value,function(i,v){
				$("#sel_properties").append("<option value='"+v.propertyId+"'>"+v.propertyName+"</option");
			});
		});
		$("#sel_properties").multiSelect("refresh");
		// 字段
		$.each(data.fields,function(i,v){
			$("#sel_field").append("<option value='"+v.propertyId+"'>"+v.propertyName+"</option");
		});
		$("#sel_field").multiSelect("refresh");
		if(properties!="null")
		{
			ids= properties.split(",");
			for(i=0;i<ids.length;i++)
			{
				if(ids[i].indexOf("CMS_FIELD_")==0)
					$("#sel_field").multiSelect("select",ids[i]);
				else
					$("#sel_properties").multiSelect("select",ids[i]);
			}
		}
			
		$("#fm_id").attr('value',id);
		$("#selPropertyDialog").modal('show');
	});	
}
