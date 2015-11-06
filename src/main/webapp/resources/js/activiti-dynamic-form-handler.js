/**
 * 动态Form办理功能
 */
$(function() {

	$('.handle').click(handle);

});

/**
 * 打开办理对话框
 */
function handle() {
	
	var $ele = $(this);

	// 当前节点的英文名称
	var tkey = $(this).attr('tkey');

	// 当前节点的中文名称
	var tname = $(this).attr('tname');

	// 任务ID
	var taskId = $(this).attr('tid');
	
	var $ele = $(this);
	// 对话框打开时执行
	$('#dynamicForm').on('shown.bs.modal', function () {
		// 获取json格式的表单数据，就是流程定义中的所有field
		readFormFields(taskId);
	});
	// 打开动态表单对话框
	$("#dynamicForm").modal();

}


/**
 * 读取表单字段
 */
function readFormFields(taskId) {
	var dialog = this;

	// 清空对话框内容
	$('.dynamic-form-dialog').html("<form class='dynamic-form' method='post'><div id='dynamic-form-table' class='modal-body modal-body-np'></div></form>");
	var $form = $('.dynamic-form');

	// 设置表单提交id
	$form.attr('action', ctx + '/workflow/task/complete/' + taskId);

    // 添加隐藏域
    if ($('#processType').length == 0) {
        $('<input/>', {
            'id': 'processType',
            'name': 'processType',
            'type': 'hidden'
        }).val(processType).appendTo($form);
    }
    
	// 读取启动时的表单
	$.getJSON(ctx + '/workflow/dynamicform/get-form/task/' + taskId, function(datas) {
		var divs = "";
		$.each(datas.taskFormData.formProperties, function() {
			var className = this.required === true ? "required" : "";
			this.value = this.value ? this.value : "";
			divs += "<div class='row'><div class='block-fluid'><div class='row-form clearfix'>" + createFieldHtml(this, datas, className)
			if (this.required === true) {
				divs += "<div class='col-md-1' style='color:red;'>*</div>";
			}
			divs += "</div></div></div>";
		});
		//alert(divs);
		divs += "<div class='modal-footer'>"
    		+"<button class='btn btn-primary' aria-hidden='true'>提 交</button>"
    		+"<button class='btn btn-default' data-dismiss='modal' aria-hidden='true'>关 闭</button>"         
    		+"</div>";

		// 添加表单内容
		$('#dynamic-form-table').html(divs);

		// 初始化日期组件
		$form.find('.date').datepicker();

		// 表单验证
		$form.validate($.extend({}, $.common.plugin.validator));
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
			result += "<div class='col-md-8'>" + prop.value + "</div>";
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