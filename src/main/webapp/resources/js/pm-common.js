$(document).ready(function() {
	//主题设置 
	/*
	$(".link_themeSettings").click(function(){
	        
	        if($("#themeSettings").is(':visible')){
	            $("#themeSettings").fadeOut(200);
	            $("#themeSettings").find(".checker").hide();
	        }else{
	            $("#themeSettings").fadeIn(300);        
	            $("#themeSettings").find(".checker").show();
	        }
	        
	       return false;
	       
	    });
	    
	    $(".settings input[name=settings_fixed]").change(function(){
	        if($(this).is(':checked')){
	            $(".wrapper").addClass('fixed');
	             $.cookies.set('themeSettings_fixed','1');
	        }else{
	            $(".wrapper").removeClass('fixed');
	            $.cookies.set('themeSettings_fixed',null);
	        }
	    });
	    
	    $(".settings input[name=settings_menu]").change(function(){
	        
	        if($(this).is(':checked')){
	            $(".menu").addClass('hidden').hide();
	            $(".header_menu li.list_icon").show();
	            $(".content").addClass('wide');
	            $.cookies.set('themeSettings_menu','1');
	        }else{
	            $(".menu").removeClass('hidden').removeAttr('style');
	            $(".header_menu li.list_icon").hide();
	            $(".content").removeClass('wide');
	            $("body > .modal-backdrop").remove();
	            $.cookies.set('themeSettings_menu',null);
	        }
	        
	    });    
	    
	    $(".settings .bgExample").click(function(){
	        var cls = $(this).attr('data-style');        
	        
	        $('body').removeAttr('class');
	        $('.settings .bgExample').removeClass('active');
	        
	        if(cls != ''){
	            $('body').addClass(cls);
	            $(this).addClass('active');
	            $.cookies.set('themeSettings_bg',cls);
	        }else{
	            $(this).addClass('active');
	            $.cookies.set('themeSettings_bg',null);
	        }
	        return false;
	    });

	    $(".settings .styleExample").click(function(){
	        var cls = $(this).attr('data-style');        
	        
	        if($('.wrapper').hasClass('fixed'))
	            $(".wrapper").attr('class','').addClass('wrapper fixed');
	        else
	            $(".wrapper").attr('class','').addClass('wrapper');
	            
	                        
	        $('.settings .styleExample').removeClass('active');
	        
	        if(cls != ''){
	            $(".wrapper").addClass(cls);
	            $(this).addClass('active');
	            $.cookies.set('themeSettings_style',cls);
	        }else
	            $.cookies.set('themeSettings_style',null);
	    
	        return false;
	    });
	    */
	
	// 打开/隐藏
	$(".toggle a").click(function() {
		var box = $(this).parents('[class^=head]').next();
		if (box.children('[class^=scroll]').length == 1)
			box = box.children('[class^=scroll]');
		if (box.length == 1) {
			if (box.is(':visible')) {
				$(this).parent('li').addClass('active');
				box.slideUp(100);
			} else {
				$(this).parent('li').removeClass('active');
				box.slideDown(200);
			}
		}
		return false;
	});
	// 消息提醒
	$.pnotify.defaults.styling = "jqueryui";
    $.pnotify.defaults.history = false;
    $.pnotify.defaults.delay = 2000;   
    $.pnotify.defaults.stack = {"dir1": "down", "dir2": "left", "push": "bottom"};
    // Bootstrap tooltip
    $(".tip").tooltip({placement: 'top', trigger: 'hover'});
    $(".tipb").tooltip({placement: 'bottom', trigger: 'hover'});
    $(".tipl").tooltip({placement: 'left', trigger: 'hover'});
    $(".tipr").tooltip({placement: 'right', trigger: 'hover'});
    //widge
    $(".buttons li > a").click(function () {

        var parent = $(this).parent();

        if (parent.find(".dd-list").length > 0) {

            var dropdown = parent.find(".dd-list");

            if (dropdown.is(":visible")) {
                dropdown.hide();
                parent.removeClass('active');
            } else {
                dropdown.show();
                parent.addClass('active');
            }

            return false;

        }

    });
    //plug in
    //uniform
    if($(".row-form,.row,.dialog,.loginBox,.block,.block-fluid").find("input:checkbox, input:radio").length>0) 
    	$(".row-form,.row,.dialog,.loginBox,.block,.block-fluid").find("input:checkbox, input:radio").not(".skip, input.ibtn").uniform();
    // CUSTOM SCROLLING
    //if($(".scroll").length>0)
    	//$(".scroll").mCustomScrollbar();
    // ACCORDION 
    //if($(".accordion").length>0)
    //	$(".accordion").accordion();
    // TABS
    //if($(".tabs").length>0)
    //	$( ".tabs" ).tabs();
    //date
    $.datepicker.regional['zh-CN'] = {  
	        closeText: '关闭',  
	        prevText: '<上月',  
	        nextText: '下月>',  
	        currentText: '今天',  
	        monthNames: ['一月','二月','三月','四月','五月','六月',  
	        '七月','八月','九月','十月','十一月','十二月'],  
	        monthNamesShort: ['一','二','三','四','五','六',  
	        '七','八','九','十','十一','十二'],  
	        dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],  
	        dayNamesShort: ['周日','周一','周二','周三','周四','周五','周六'],  
	        dayNamesMin: ['日','一','二','三','四','五','六'],  
	        weekHeader: '周',  
	        dateFormat: 'yy-mm-dd',  
	        firstDay: 1,  
	        isRTL: false,  
	        showMonthAfterYear: true,  
	        yearSuffix: '年'};  
	    $.datepicker.setDefaults($.datepicker.regional['zh-CN']);  
	//tagsinput
	//if($(".tags").length > 0)
	//	$(".tags").tagsInput({'width':'100%','height':'auto'});
});
$(document).ready(function() {
	if ($.cookies.test()) {
		
		/*fixed*/
        var tFixed = $.cookies.get('themeSettings_fixed');
        if(null != tFixed){
            $(".wrapper").addClass('fixed');
            $(".settings input[name=settings_fixed]").attr('checked',true).parent('span').addClass('checked');
        }
        
        /*menu*/
        var tMenu = $.cookies.get('themeSettings_menu');
        if(null != tMenu){
            if(null != tMenu){                            
                $(".menu").addClass('hidden').hide();
                $(".header_menu li.list_icon").show();
                $(".content").addClass('wide');      
                $(".settings input[name=settings_menu]").attr('checked',true).parent('span').addClass('checked');
            }
        }
        /*bg*/
        var tBg = $.cookies.get('themeSettings_bg');
        if(null != tBg){
            $('body').removeAttr('class').addClass(tBg);
            $('.settings .bgExample').removeClass('active');
            $('.settings .bgExample[data-style="'+tBg+'"]').addClass('active');
        }
        /*theme style*/
        var tStyle = $.cookies.get('themeSettings_style');
        if(null != tStyle){
            if($('.wrapper').hasClass('fixed'))
                $(".wrapper").attr('class','').addClass('wrapper fixed');
            else
                $(".wrapper").attr('class','').addClass('wrapper');            
            
            $('.settings .styleExample').removeClass('active');
            $(".wrapper").addClass(tStyle);        
            $('.settings .styleExample[data-style="'+tStyle+'"]').addClass('active');
        }        
    
		// ADMIN BLOCK
		var bAdmin_v = $.cookies.get('b_Admin_visibility');
	
		if (null == bAdmin_v) {
			if ($('.adminControl').hasClass('active'))
				$.cookies.set('b_Admin_visibility', 'visible');
			else
				$.cookies.set('b_Admin_visibility', 'hidden');
		} else {
	
			if (bAdmin_v == 'visible')
				$('.adminControl').addClass('active');
			else
				$('.adminControl').removeClass('active');
	
		}
		// EOF ADMIN BLOCK

		// Collapsible widgets
		$("div[class^=block]").each(function() {
			if ($(this).attr('data-cookie')) {
				var c_val = $.cookies.get($(this).attr('data-cookie'));
				if (null != c_val) {
					if (c_val == 'visible') {
						$(this).parent('div[class^=span]').find('.head > .buttons li.toogle').removeClass('active');
						$(this).show();
					} else {
							$(this).parent('div[class^=span]').find('.head > .buttons li.toogle').addClass('active');
							$(this).hide();
					}
				}
			}
	
		});
		// eof Collapsible widgets
	}
});
$(window).resize(function() {
	headInfo();

	if ($("body").width() > 980) {
		$("body .wrapper .menu").show();
		$("body > .modal-backdrop").remove();
	} else {
		$("body .wrapper .menu").hide();
		$("body > .modal-backdrop").remove();
	}
});
$('.wrapper').resize(function() {

	if ($("body > .content").css('margin-left') == '220px') {
		if ($("body > .menu").is(':hidden'))
			$("body > .menu").show();
	}
});
/**
 * 返回上一页
 * 
 * @returns {Boolean}
 */
function pm_history_back() {
	// 使用back,不刷新页面(chrome和firefox会刷新)
	history.go(-1);
	return false;
}
/**
 * 获取随机数
 * 
 * @returns
 */
function pm_random() {
	var obj = new Date();
	return obj.getDay() + obj.getHours() + obj.getMinutes()
			+ obj.getMilliseconds();
}
function pm_refresh() {
	window.location.href = window.location.href;
}
/**
 * 添加date的format方法
 * 
 * @returns
 */
Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1,
		"d+" : this.getDate(),
		"H+" : this.getHours(),
		"m+" : this.getMinutes(),
		"s+" : this.getSeconds(),
		"q+" : Math.floor((this.getMonth() + 3) / 3),
		"S" : this.getMilliseconds()
	}
	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}
	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
}
function pm_getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = decodeURIComponent(window.location.search).substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}
function pm_refresh()
{
	location.reload(true);
}

function headInfo(){
    var block = $(".headInfo .input-append");
    var input = block.find("input[type=text]");
    var button = block.find("button");
    
    input.width(block.width()-button.width()-44);    
}