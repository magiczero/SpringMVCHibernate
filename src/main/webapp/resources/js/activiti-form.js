/**
 * 根据流程定义的key值打开流程启动对话框
 * 
 * @param processDefinitionKey
 * @param redirectAddress
 */
function act_form_openStartDialog(processName,processDefinitionKey,redirectAddress) {
	// 对话框打开时执行
	$('#dynamicForm').html(
			"<div class='modal-dialog'>"
			+"<div class='modal-content'>"
			+"<div class='modal-header'>"
			+"<button type='button' class='close' data-dismiss='modal'>"
			+"<span aria-hidden='true'>&times;</span><span class='sr-only'>Close</span></button>"                        
            +"<h4>"+processName+"</h4>"
            +"</div>"
            +"<div class='dynamic-form-dialog'></div>"
			+"</div>"
			+"</div>"
	);
	$('#dynamicForm').unbind('shown.bs.modal');
	$('#dynamicForm').on('shown.bs.modal', function() {
		// 获取json格式的表单数据，就是流程定义中的所有field
		act_form_getStartDialog(processDefinitionKey,redirectAddress);
	});
	
	// 打开动态表单对话框
	$("#dynamicForm").modal();
}
/**
 * 打开任务处理对话框
 * 
 * @param taskName
 * @param taskId
 * @param redirectAddress
 */
function act_form_openTaskDialog(taskName,taskId,redirectAddress) {
	// 对话框打开时执行
	$('#dynamicForm').html(
			"<div class='modal-dialog'>"
			+"<div class='modal-content'>"
			+"<div class='modal-header'>"
			+"<button type='button' class='close' data-dismiss='modal'>"
			+"<span aria-hidden='true'>&times;</span><span class='sr-only'>Close</span></button>"                        
            +"<h4>"+taskName+"</h4>"
            +"</div>"
            +"<div class='dynamic-form-dialog'></div>"
			+"</div>"
			+"</div>"
	);
	$('#dynamicForm').unbind('shown.bs.modal');
	$('#dynamicForm').on('shown.bs.modal', function() {
		// 获取json格式的表单数据，就是流程定义中的所有field
		act_form_getTaskDialog(taskId,redirectAddress);
	});
	
	// 打开动态表单对话框
	$("#dynamicForm").modal();
}
/**
 * 显示任务处理表单
 * 
 * @param taskId
 * @param redirectAddress
 */
function act_form_task(taskId,redirectAddress)
{
	// 获取json格式的表单数据，就是流程定义中的所有field
	act_form_getTaskFields(taskId,redirectAddress);
}
/**
 * 获取任务处理表单信息
 * 
 * @param taskId
 * @param redirectAddress
 */
function act_form_getTaskFields(taskId,redirectAddress) {

	// 清空表单内容
	$('#workflow_task_form').html("<form id='dynamic_form' method='post'><div id='dynamic_form_table'></div></form>");
	var $form = $('#dynamic_form');
	// 设置表单提交地址
	$form.attr('action', ctx + '/workflow/task/complete/' + taskId);
    
	// 读取表单信息
	$.getJSON(ctx + '/workflow/dynamicform/get-form/task/' + taskId + '?t=' + pm_random(), function(datas) {
		var divs = "";
		$.each(datas.form.formProperties, function() {
			var className = this.required === true ? "required" : "";
			this.value = this.value ? this.value : "";
			divs += "<div class='row-form clearfix'>" + act_form_createfield(this, datas, className)
			if (this.required === true) {
				divs += "<div class='col-md-1' style='color:red;'>*</div>";
			}
			divs += "</div>";
		});
		divs += "<div class='footer'>"
				+ "<input type=hidden name='redirectAddress' value='" + redirectAddress + "' />"
                +"<button class='btn btn-primary center-block'> 提 交 </button>"
                +"</div>";

		// 添加表单内容
		$('#dynamic_form_table').html(divs);
		// 初始化工程师选择下拉框
		if($form.find('.user').length>0)
		{
			$.getJSON(ctx + '/user/getengineer?t=' + pm_random(),function(result){
				$.each(result.users,function(key,value){
					$form.find('.user').append("<option value='"+key+"'>"+value+"</option>");
				});
				for(i=0;i<$form.find('.user').length;i++)
				{
					if( $($form.find('.user')[i]).attr('data')!=null )
						$($form.find('.user')[i]).find("option[value='"+$($form.find('.user')[i]).attr('data')+"']").attr("selected",true);
				}
				$form.find('.user').select2();
			});
		}
		// 初始化领导选择下拉框
		if($form.find('.leader').length>0)
		{
			$.getJSON(ctx + '/user/getleader?t=' + pm_random(),function(result){
				$.each(result.users,function(key,value){
					$form.find('.leader').append("<option value='"+key+"'>"+value+"</option>");
				});
				for(i=0;i<$form.find('.leader').length;i++)
				{
					if( $($form.find('.leader')[i]).attr('data')!=null )
						$($form.find('.leader')[i]).find("option[value='"+$($form.find('.leader')[i]).attr('data')+"']").attr("selected",true);
				}
				$form.find('.leader').select2();
			});
		}
		// 初始化日期组件
		$form.find('.dateISO').datepicker();
		// 表单验证
		//$form.validate($.extend({}, $.common.plugin.validator));
		
		//初始化事件模板
		if($form.find("#template").length>0)
		{
			var template_name = $("#template").children('option:selected').val(); 
			
			if( $("#template").children('option:selected').val()!="NULL" )
				act_template_display(template_name, get_templatedata_address,true );
	
			$("#template").bind("change",function(){
				act_template_display($("#template").children('option:selected').val(), get_templatedata_address,true );
			});
		}
	});
}
/**
 * 根据流程定义的Key值获取表单字段信息
 * 
 * @param processDefinitionKey
 * @param redirectAddress
 */
function act_form_getStartDialog(processDefinitionKey,redirectAddress) {
	// 清空对话框内容
	$('.dynamic-form-dialog').html("<form class='dynamic-form' method='post'><div id='dynamic-form-table' class='modal-body modal-body-np'></div></form>");
	var $form = $('.dynamic-form');
	// 设置表单提交id
	$form.attr('action', ctx + '/workflow/dynamicform/startprocessbykey/' + processDefinitionKey);

	// 读取启动时的表单
	$.getJSON(ctx + '/workflow/dynamicform/getformbykey/start/' + processDefinitionKey + '?t=' + pm_random(),function(data) {
		act_form_getDialogFields(data,redirectAddress);
	});
}
function act_form_getTaskDialog(taskId,redirectAddress) {
	// 清空对话框内容
	$('.dynamic-form-dialog').html("<form class='dynamic-form' method='post'><div id='dynamic-form-table' class='modal-body modal-body-np'></div></form>");
	var $form = $('.dynamic-form');

	// 设置表单提交id
	$form.attr('action', ctx + '/workflow/task/complete/' + taskId);
    
	// 读取启动时的表单
	$.getJSON(ctx + '/workflow/dynamicform/get-form/task/' + taskId + '?t=' + pm_random(), function(data) {
		act_form_getDialogFields(data,redirectAddress);
	});
}
/**
 * 获取用于对话框的表单信息
 * 
 * @param data
 * @param redirectAddress
 */
function act_form_getDialogFields(data,redirectAddress) {
	var divs = "";
	var $form = $('.dynamic-form');
	$.each(data.form.formProperties,function() {
		var className = this.required === true ? "required" : "";
		divs += "<div class='row'><div class='block-fluid'><div class='row-form clearfix'>" + act_form_createfield(this, data,className)
		if (this.required === true) {
			divs += "<div class='col-md-1' style='color:red;'>*</div>";
		}
		divs += "</div></div></div>";
	});
	divs += "<div class='modal-footer'>"
			+ "<input type=hidden name='redirectAddress' value='" + redirectAddress + "' />"
			+ "<button class='btn btn-primary' aria-hidden='true'>提 交</button>"
			+ "<button class='btn btn-default' data-dismiss='modal' aria-hidden='true'>关 闭</button>"
			+ "</div>";
	// 添加表单内容
	$('#dynamic-form-table').html(divs);
	// 初始化工程师选择下拉框
	if($form.find('.user').length>0)
	{
		$.getJSON(ctx + '/user/getengineer?t=' + pm_random(),function(result){
			$.each(result.users,function(key,value){
				$form.find('.user').append("<option value='"+key+"'>"+value+"</option>");
			});
			for(i=0;i<$form.find('.user').length;i++)
			{
				if( $($form.find('.user')[i]).attr('data')!=null )
					$($form.find('.user')[i]).find("option[value='"+$($form.find('.user')[i]).attr('data')+"']").attr("selected",true);
			}
			$form.find('.user').select2();
		});
	}
	// 初始化领导选择下拉框
	if($form.find('.leader').length>0)
	{
		$.getJSON(ctx + '/user/getleader?t=' + pm_random(),function(result){
			$.each(result.users,function(key,value){
				$form.find('.leader').append("<option value='"+key+"'>"+value+"</option>");
			});
			for(i=0;i<$form.find('.leader').length;i++)
			{
				if( $($form.find('.leader')[i]).attr('data')!=null )
					$($form.find('.leader')[i]).find("option[value='"+$($form.find('.leader')[i]).attr('data')+"']").attr("selected",true);
			}
			$form.find('.leader').select2();
		});
	}
	// 初始化日期组件
	$form.find('.dateISO').datepicker();
	
	if($form.find('.actFileType').length>0)
	{
		$('.actFileType').uploadify({
			'formData' : { 'type' : 5 },
	        'swf'      : ctx + '/resources/flash/uploadify.swf',
	      //按钮显示的文字
	        'buttonText': '选择文件……',
	        'uploader' : ctx + '/attachment/upload',
	        'removeCompleted' : false,
	        // Put your options here
	        'onUploadSuccess': function (file, data, response) {
	        	//console.log(data);
	            $('#' + file.id).find('.data').html(' 上传完毕');
	            var fileids = document.getElementById("fileids").value + '';
	            document.getElementById("fileids").value = fileids + data;
	        }
	    });
	}
	// 表单验证
	//$form.validate($.extend({}, $.common.plugin.validator));
}
/**
 * form对应的string/date/long/enum/boolean类型表单组件生成器 
 * 字段名均以fp_为前缀，fp_的意思是form paremeter
 */
var act_form_fieldcreator = {
	'string': function(prop, datas, className) {
		var result = "<div class='col-md-3'>" + prop.name + "：</div>";
		if (prop.writable === true) {
			result += "<div class='col-md-8'>"
				  	+"<input type='text' id='" + prop.id + "' name='fp_" + prop.id 
				  	+ "' class='" + className + "' value='" + (prop.value==null?"":prop.value) 
				  	+ "' /></div>";
		} else {
			result += "<div class='col-md-8'>" + prop.value + "</div>";
		}
		return result;
	},
	'file': function(prop, datas, className) {
		var result = "<div class='col-md-3'>" + prop.name + "：</div>";
		if (prop.writable === true) {
			result += "<div class='col-md-8'>"
					+ "<input type='hidden' id='" + prop.id + "' name='fp_" + prop.id 
				  	+ "' class='" + className + "' value='" + (prop.value==null?"":prop.value) 
				  	+ "' />"
					+ "<input type='file' class='actFileType' name='attach' id='attach' /> "
				  	+ "</div>";
		} else {
			result += "<div class='col-md-8'>" + prop.value + "</div>";
		}
		return result;
	},
	'bigtext': function(prop, datas, className) {
		var result = "<div class='col-md-3'>" + prop.name + "：</div>";
		if (prop.writable === true) {
			result += "<div class='col-md-8'><textarea id='" + prop.id + "' name='fp_" + prop.id + "' class='" + className + "'>"
					+ (prop.value==null?"":prop.value) + "</textarea></div>";
		} else {
			result += "<div class='col-md-8'>" + prop.value + "</div>";
		}
		return result;
	},
	'date': function(prop, datas, className) {
		var result = "<div class='col-md-3'>" + prop.name + "：</div>";
		if (prop.writable === true) {
			result += "<div class='col-md-8'><input type='text' id='" + prop.id + "' name='fp_" + prop.id + "' class='dateISO " + className 
			+ "' value='"+(prop.value==null?"":prop.value)+"' /></div>";
		} else {
			result += "<div class='col-md-3'>" + prop.value + "</div>";
		}
		return result;
	},
	'enum': function(prop, datas, className) {
		var result = "<div class='col-md-3'>" + prop.name + "：</div>";
		if (prop.writable === true) {
			result += "<div class='col-md-8'><select id='" + prop.id + "' name='fp_" + prop.id + "' class='" + className + "'>";
				$.each(datas["enum_"+prop.id], function(k, v) {
					result += "<option value='" + k + "' "
					if(v==datas["enum_"+prop.id][prop.value])
						result += "selected='selected'";
					result += ">" + v + "</option>";
				});

			result += "</select></div>";
		} else {
			//result += "<div class='col-md-8'>" + datas[prop.id][prop.value] + "</div>";
			result += "<div class='col-md-8'><select id='" + prop.id + "' name='fp_" + prop.id + "' class='" + className + "' disabled='disabled'>";
				$.each(datas["enum_"+prop.id], function(k, v) {
					result += "<option value='" + k + "' "
					if(v==datas["enum_"+prop.id][prop.value])
						result += "selected='selected'";
					result += ">" + v + "</option>";
				});
			result += "</select></div>";
		}
		return result;
	},
	'user': function(prop, datas, className) {
		var result = "<div class='col-md-3'>" + prop.name + "：</div>";
		if (prop.writable === true) {
			result += "<div class='col-md-8'><select id='" + prop.id + "' name='fp_" + prop.id + "' class='user' style='width:100%' data='" 
				+ (prop.value==null?"":prop.value) + "'></select></div>";
		} else {
			result += "<div class='col-md-8'>" + prop.value + "</div>";
		}
		return result;
	},
	'leader': function(prop, datas, className) {
		var result = "<div class='col-md-3'>" + prop.name + "：</div>";
		if (prop.writable === true) {
			result += "<div class='col-md-8'><select id='" + prop.id + "' name='fp_" + prop.id + "' class='leader' style='width:100%' data='"
				+ (prop.value==null?"":prop.value) + "'></select></div>";
		} else {
			result += "<div class='col-md-8'>" + prop.value + "</div>";
		}
		return result;
	}
};
/**
 * 生成一个field的html代码
 * 
 * @param formData
 * @param prop
 * @param className
 * @returns
 */
function act_form_createfield(prop, formData, className) {
	return act_form_fieldcreator[prop.type.name](prop, formData, className);
}
/**
 * 显示模板
 */
function act_template_display(template_name,address,bsave){
	
	if(template_name=='NULL')
	{
		$("#form_template").html("");
	}
	else
	{
        $("#form_template").load(ctx+"/resources/template/"+template_name+".template?t="+pm_random(),function(){
        	if(bsave)
        	{
	        	$("#form_template").append("<div class='toolbar nopadding-toolbar clear clearfix'>"
	        				+"<div class='right btn-group'>"
	        				+"<button class='btn btn-warning' id='lnk_save_template'> 保存数据 </button>"   
	        				+"</div>"
	        				+"</div>");
	       		$("#lnk_save_template").bind("click",act_template_saveData);
        	}
        	//添加处理器
        	$("#form_template input[type='radio']").bind("click",function(){
        		if($(this).attr("value")=="true")
        		{
        			$($(this).parent().nextAll()[1]).hide();
        			$($(this).parent().nextAll()[2]).hide();
        		}
        		else
        		{
        			$($(this).parent().nextAll()[0]).show();
        			$($(this).parent().nextAll()[1]).show();
        		}
        	});
        	
            act_template_getData(address);
            
        });
	}
}
/**
 * 提交模板数据
 * 
 * @returns {Boolean}
 */
function act_template_saveData()
{
	$("#form_template").ajaxSubmit(function(){
		alert("保存成功!");
	});
	return false;
}

/**
 * 获取模板数据
 * @param eventid
 */
function act_template_getData(address)
{
	$.getJSON(address, function(data) {
		$.each(data.result, function(k,v){
			$("input[name='"+k+"']").val(v);
		});
        $.each($("#form_template input[type='radio']:checked"),function(i,v){
        	if($(v).attr("value")=="true")
    		{
    			$($(v).parent().nextAll()[1]).hide();
    			$($(v).parent().nextAll()[2]).hide();
    		}
    		else
    		{
    			$($(v).parent().nextAll()[0]).show();
    			$($(v).parent().nextAll()[1]).show();
    		}
        });
	});
}