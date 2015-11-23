/**
 * 动态Form办理功能
 */

/**
 * 读取表单字段
 */
function get_deal_form(taskId) {

	// 清空对话框内容
	$('#event_deal_form').html("<form class='dynamic-form' method='post'><div id='dynamic-form-table'></div></form>");
	
	var $form = $('.dynamic-form');

	// 设置表单提交id
	$form.attr('action', ctx + '/workflow/task/complete/' + taskId);
    
	// 读取启动时的表单
	$.getJSON(ctx + '/workflow/dynamicform/get-form/task/' + taskId, function(datas) {
		var divs = "";
		$.each(datas.taskFormData.formProperties, function() {
			var className = this.required === true ? "required" : "";
			this.value = this.value ? this.value : "";
			divs += "<div class='row-form clearfix'>" + createFieldHtml(this, datas, className)
			if (this.required === true) {
				divs += "<div class='col-md-1' style='color:red;'>*</div>";
			}
			divs += "</div>";
		});
		divs += "<div class='footer tal'>"
                +"<button class='btn btn-primary'> 提 交 </button>"
                +"</div>";

		// 添加表单内容
		$('#dynamic-form-table').html(divs);

		// 初始化日期组件
		$form.find('.date').datepicker();

		// 表单验证
		//$form.validate($.extend({}, $.common.plugin.validator));
		
	});
}

/**
 * form对应的string/date/long/enum/boolean类型表单组件生成器
 * fp_的意思是form paremeter
 */
var formFieldCreator = {
	'string': function(prop, datas, className) {
		var result = "<div class='col-md-3'>" + prop.name + "：</div>";
		if (prop.writable === true) {
			result += "<div class='col-md-8'><input type='text' id='" + prop.id + "' name='fp_" + prop.id + "' class='" + className + "' value='" + prop.value + "' /></div>";
		} else {
			result += "<div class='col-md-8'>" + prop.value + "</div>";
		}
		return result;
	},
	'bigtext': function(prop, datas, className) {
		var result = "<div class='col-md-3'>" + prop.name + "：</div>";
		if (prop.writable === true) {
			result += "<div class='col-md-8'><textarea id='" + prop.id + "' name='fp_" + prop.id + "' class='" + className + "'>"+prop.value+"</textarea></div>";
		} else {
			result += "<div class='col-md-8'>" + prop.value + "</div>";
		}
		return result;
	},
	'date': function(prop, datas, className) {
		var result = "<div class='col-md-3'>" + prop.name + "：</div>";
		if (prop.writable === true) {
			result += "<div class='col-md-8'><input type='text' id='" + prop.id + "' name='fp_" + prop.id + "' class='date " + className + "' value='" + prop.value + "'/></div>";
		} else {
			result += "<div class='col-md-3'>" + prop.value + "</div>";
		}
		return result;
	},
	'enum': function(prop, datas, className) {
		var result = "<div class='col-md-3'>" + prop.name + "：</div>";
		if (prop.writable === true) {
			result += "<div class='col-md-8'><select id='" + prop.id + "' name='fp_" + prop.id + "' class='" + className + "'>";
			$.each(datas[prop.id], function(k, v) {
				result += "<option value='" + k + "'>" + v + "</option>";
			});
			result += "</select></div>";
		} else {
			//result += "<div class='col-md-8'>" + datas[prop.id][prop.value] + "</div>";
			result += "<div class='col-md-8'><select id='" + prop.id + "' name='fp_" + prop.id + "' class='" + className + "' disabled='disabled'>";
			$.each(datas[prop.id], function(k, v) {
				result += "<option value='" + k + "' "
				if(v==datas[prop.id][prop.value])
					result += "selected='selected'";
				result += ">" + v + "</option>";
			});
			result += "</select></div>";
		}
		return result;
	},
	'users': function(prop, datas, className) {
		var result = "<div class='col-md-3'>" + prop.name + "：</div>";
		if (prop.writable === true) {
			result += "<div class='col-md-8'><input type='text' id='" + prop.id + "' name='fp_" + prop.id + "' class='" + className + "' value='" + prop.value + "' /></div>";
		} else {
			result += "<div class='col-md-8'>" + prop.value + "</div>";
		}
		return result;
	}
};

/**
 * 生成一个field的html代码
 */
function createFieldHtml(prop, className) {
	return formFieldCreator[prop.type.name](prop, className);
}