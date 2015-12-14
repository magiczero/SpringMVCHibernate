var isRead = false;

function pm_message_gettips()
{
	$("#popup_messages_box").html('');
	$.getJSON(ctx+"/message/getnotreadlist?t"+pm_random(),function(datas){
		var divs = "";
		if(datas.messages.length==0)
		{
			$("#popup_messages_count").html('');
			$("#popup_messages_box").append("暂时没有未阅读的消息");
			return;
		}
		$.each(datas.messages, function(i, v) {
			divs += "<div class='item clearfix'>"
				+"<a href='#' class='name'>"+v.fromUser+"</a>"
				+"<p>"+v.content+"</p>"
				+"<span class='date'>"+new Date(v.createdTime).toLocaleString()+"</span>"
				+"<div class='controls'>"                                    
                +"<a href='#' class='glyphicon glyphicon-ok tip' title='已阅' onclick='pm_message_read("+v.id+")'></a>"
                +"</div>"
				+"</div>";
		});
		//$("#popup_messages_count").text("("+datas.messages.length+")");
		$("#popup_messages_box").append(divs);
	});
}
function pm_message_read(id)
{
	$.getJSON(ctx+"/message/read/"+id+"?t"+pm_random(),function(datas){
		isRead = true;
		if(datas.result=="ok")
		{
			pm_message_gettips();
		}
	});
}
function pm_message_gettipscount()
{
	if(isRead)
		return;
	$.getJSON(ctx+"/message/getnotreadcount?t"+pm_random(),function(datas){
		isRead = true;
		if(datas.count=="0")
			return;
		$("#popup_messages_count").text("("+datas.count+")");
		$.pnotify({title: "消息提醒", text: "您有"+datas.count+"条信息需要查阅", opacity: .9, type:'info'});
	});
}
