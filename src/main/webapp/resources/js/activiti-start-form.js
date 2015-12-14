/**
 * 根据流程定义的key值打开动态表单对话框
 * 
 * @param processDefinitionKey
 * @param redirectAddress
 */
function act_form_startbykey(processDefinitionKey,redirectAddress) {
	// 对话框打开时执行
	$('#dynamicForm').on('shown.bs.modal', function() {
		// 获取json格式的表单数据，就是流程定义中的所有field
		act_form_getfields(processDefinitionKey,redirectAddress);
	});
	// 打开动态表单对话框
	$("#dynamicForm").modal();
}
/**
 * 根据流程定义的Key值获取表单字段信息
 * 
 * @param processDefinitionKey
 * @param redirectAddress
 */
function act_form_getfields(processDefinitionKey,redirectAddress) {

	// 清空对话框内容
	$('.dynamic-form-dialog').html("<form class='dynamic-form' method='post'><div id='dynamic-form-table' class='modal-body modal-body-np'></div></form>");
	var $form = $('.dynamic-form');
	// 设置表单提交id
	$form.attr('action', ctx + '/workflow/dynamicform/startprocessbykey/' + processDefinitionKey);
	// 读取启动时的表单
	$.getJSON(ctx + '/workflow/dynamicform/getformbykey/start/' + processDefinitionKey + '?t=' + g_get_random(),
			function(data) {
				var divs = "";
				$.each(data.form.formProperties,function() {
					var className = this.required === true ? "required" : "";
					divs += "<div class='row'><div class='block-fluid'><div class='row-form clearfix'>" + act_form_createfield(data, this,className)
					if (this.required === true) {
						divs += "<div class='col-md-1' style='color:red;'>*</div>";
					}
					divs += "</div></div></div>";
				});
				divs += "<div class='modal-footer'>"
						+ "<input type=hidden name='redirectAddress' value='" + redirectAddress + "' />"
						+ "<button class='btn btn-primary' aria-hidden='true'>启 动</button>"
						+ "<button class='btn btn-default' data-dismiss='modal' aria-hidden='true'>关 闭</button>"
						+ "</div>";
				// 添加表单内容
				$('#dynamic-form-table').html(divs);
				// 初始化日期组件
				$form.find('.dateISO').datepicker();
				// 表单验证
				//$form.validate($.extend({}, $.common.plugin.validator));
	});
}

/**
 * form对应的string/date/long/enum/boolean类型表单组件生成器 
 * 字段名均以fp_为前缀，fp_的意思是form paremeter
 */
var act_form_fieldcreator = {

	string : function(formData, prop, className) {
		var result = "<div class='col-md-3'>" + prop.name
				+ "：</div><div class='col-md-8'><input type='text' id='"
				+ prop.id + "' name='fp_" + prop.id + "' class='" + className
				+ "' /></div>";
		return result;
	},
	long : function(formData, prop, className) {
		var result = "<div class='col-md-3'>" + prop.name
				+ "：</div><div class='col-md-8'><input type='text' id='"
				+ prop.id + "' name='fp_" + prop.id + "' class='" + className
				+ "' /></div>";
		return result;
	},
	date : function(formData, prop, className) {
		var result = "<div class='col-md-3'>" + prop.name
				+ "：</div><div class='col-md-8'><input type='text' id='"
				+ prop.id + "' name='fp_" + prop.id + "' class='dateISO "
				+ className + "' /></div>";
		return result;
	},
	'enum' : function(formData, prop, className) {
		console.log(prop);
		var result = "<div class='col-md-3'>" + prop.name + "：</div>";
		if (prop.writable === true) {
			result += "<div class='col-md-8'><select id='" + prop.id
					+ "' name='fp_" + prop.id + "' class='" + className + "'>";
			// result += "<option>" + datas + "</option>";

			$.each(formData['enum_' + prop.id], function(k, v) {
				result += "<option value='" + k + "'>" + v + "</option>";
			});

			result += "</select></div>";
		} else {
			result += "<div class='col-md-8'>" + prop.value + "</div>";
		}
		return result;
	},
	'users' : function(formData, prop, className) {
		var result = "<div class='col-md-3'>" + prop.name
				+ "：</div><div class='col-md-8'><input type='text' id='"
				+ prop.id + "' name='fp_" + prop.id + "' class='" + className
				+ "' /></div>";
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
function act_form_createfield(formData, prop, className) {
	return act_form_fieldcreator[prop.type.name](formData, prop, className);
}