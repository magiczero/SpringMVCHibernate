
$(document).ready(function(){
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
    /* LEFT SIDE DATEPICKER */
    $("#menuDatepicker").datepicker();
    /* UI elements datepicker */        
    $("#Datepicker").datepicker();    
    
    /* CALENDAR */
    var date = new Date();
    var d = date.getDate();
    var m = date.getMonth();
    var y = date.getFullYear();

    var calendar = $('#calendar').fullCalendar({
            header: {		
                    left: 'prev,next',
                    center: 'title',
                    right: ''
                    //right: 'month,agendaWeek,agendaDay'
            },
            selectable: true,
            selectHelper: true,
            select: function(start, end, allDay) {
                    var title = prompt('Event Title:');
                    if (title) {
                            calendar.fullCalendar('renderEvent',
                                    {
                                            title: title,
                                            start: start,
                                            end: end,
                                            allDay: allDay
                                    },
                                    true // make the event "stick"
                            );
                    }
                    calendar.fullCalendar('unselect');
            },
            editable: true,
            events: [
                    {
                            title: 'All Day Event',
                            start: new Date(y, m, 20),
                            end: new Date(y, m, 21)
                    },
                    {
                            title: 'Long Event',
                            start: new Date(y, m, 1),
                            end: new Date(y, m, 10)
                    }
            ]
    });
        
    // CHECKBOXES AND RADIO
        $(".row-form,.row,.dialog,.loginBox,.block,.block-fluid").find("input:checkbox, input:radio, input:file").not(".skip, input.ibtn").uniform();
        
    //FORM VALIDATION

        $("#validation").validationEngine({promptPosition : "topLeft", scroll: true});        
        
    // CUSTOM SCROLLING
    
        $(".scroll").mCustomScrollbar();
    
    // ACCORDION 
    
        $(".accordion").accordion();
        
    // TABS
    
        $( ".tabs" ).tabs();

   // TOOLTIPS

        $('.tt').qtip({ style: {name: 'aquarius' },
                        position: {
                            corner: {
                                target: 'topRight',
                                tooltip: 'bottomLeft'
                            }
                        } 
                    });
        
        $('.ttRC').qtip({ style: { name: 'aquarius' },
                        position: {
                            corner: {
                                target: 'rightMiddle',
                                tooltip: 'leftMiddle'
                            }
                        } 
                    });        

        $('.ttRB').qtip({ style: { name: 'aquarius' },
                        position: {
                            corner: {
                                target: 'bottomRight',
                                tooltip: 'topLeft'
                            }
                        } 
                    });
                    
        $('.ttLT').qtip({ style: { name: 'aquarius' },
                        position: {
                            corner: {
                                target: 'topLeft',
                                tooltip: 'bottomRight'
                            }
                        } 
                    });
        
        $('.ttLC').qtip({ style: { name: 'aquarius' },
                        position: {
                            corner: {
                                target: 'leftMiddle',
                                tooltip: 'rightMiddle'
                            }
                        } 
                    });        

        $('.ttLB').qtip({ style: { name: 'aquarius' },
                        position: {
                            corner: {
                                target: 'bottomLeft',
                                tooltip: 'topRight'
                            }
                        } 
                    });
         
         // Bootstrap tooltip
         $(".tip").tooltip({placement: 'top', trigger: 'hover'});
         $(".tipb").tooltip({placement: 'bottom', trigger: 'hover'});
         $(".tipl").tooltip({placement: 'left', trigger: 'hover'});
         $(".tipr").tooltip({placement: 'right', trigger: 'hover'});

             
         if($(".tags").length > 0)
            $(".tags").tagsInput({'width':'100%',
                                'height':'auto'});         
   
   // new selector case insensivity        
        $.expr[':'].containsi = function(a, i, m) {
            return jQuery(a).text().toUpperCase().indexOf(m[3].toUpperCase()) >= 0;
        };        
   
    // Pnotify notifier
        $.pnotify.defaults.styling = "jqueryui";
        $.pnotify.defaults.history = false;
        $.pnotify.defaults.delay = 2000;   
        $.pnotify.defaults.stack = {"dir1": "down", "dir2": "left", "push": "bottom"};
        
        
    // Scroll up plugin
     $.scrollUp({scrollText: '^'});
    // eof scroll up plugin    
});


function notify(title, text){
    $.pnotify({title: title, text: text, opacity: .8, addclass: 'palert'});
}
function notify_s(title,text){
    $.pnotify({title: title, text: text, opacity: .8, type: 'success'});            
}
function notify_i(title,text){
    $.pnotify({title: title, text: text, opacity: .8, type: 'info'});            
}
function notify_e(title,text){
    $.pnotify({title: title, text: text, opacity: .8, type: 'error'});            
}