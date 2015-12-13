function pm_account_setProperty(code){
	$.getJSON(ctx + '/cms/ci/getpropertybycode/' + code +'?t=' + pm_random(),function(data){
		$("#sel_properties option").remove();
		// 参数
		$.each(data.properties,function(key,value){
			$.each(value,function(i,v){
				$("#sel_properties").append("<option value='"+v.propertyId+"'>"+v.propertyName+"</option");
			});
		});
		$("#sel_properties").multiSelect("refresh");
	});	
}