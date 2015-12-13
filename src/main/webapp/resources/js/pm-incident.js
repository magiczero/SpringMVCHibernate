function pm_incident_getci()
{
	$.getJSON(ctx+'/itilrelation/get-incident-ci/'+incidentId+'/?t='+pm_random(),function(data){
		var ids = data.cis;
		$("#objectTable tbody tr").remove();
		$.getJSON(ctx+'/cms/ci/getjson/'+ids+'/?t='+pm_random(),function(data){
			
			var trs = "";
			$.each(data.cis,function(i,v){
				trs += "<tr><td><a href='"+ctx+"/cms/ci/detail/"+v.id+"' target=_blank>"+v.name+"</a></td>"
					+"<td>"+v.categoryName+"</td>"
					+"<td>"+v.location+"</td>"
					+"<td>"+v.statusName+"</td>"
					+"<td><a href='#' onclick='pm_incident_removeci("+v.id+")'><span class='glyphicon glyphicon-remove'></span></a></td></tr>";
			});
			$("#objectTable tbody").append(trs);
		});
	});
}
function pm_incident_getKnowledge()
{
	$.getJSON(ctx+'/itilrelation/get-incident-knowledge/'+incidentId+'/?t='+pm_random(),function(data){
		var ids = data.knowledges;
		$("#knowledgeTable tbody tr").remove();
		$.getJSON(ctx+'/knowledge/getjson/'+ids+'/?t='+pm_random(),function(data){
			
			var trs = "";
			$.each(data.knowledges,function(i,v){
				trs += "<tr><td><a href='"+ctx+"/knowledge/detail/"+v.id+"' target=_blank>"+v.title+"</a></td>"
					+"<td>"+v.hits+"</td></tr>";
			});
			$("#knowledgeTable tbody").append(trs);
		});
	});
}
function pm_incident_removeci(id)
{
	$.getJSON(ctx+'/itilrelation/remove-incident-ci?t='+pm_random(),{primary_id:incidentId,secondary_id:id},function(data){
		pm_incident_getci();
	});
	return false;
}