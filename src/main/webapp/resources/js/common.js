/**
 * 返回上一页
 * 
 * @returns {Boolean}
 */
function g_history_back() {
	// 使用back,不刷新页面(chrome和firefox会刷新)
	window.history.back(-1);
	return false;
}
/**
 * 获取随机数
 * 
 * @returns
 */
function g_get_random() {
	var obj = new Date();
	return obj.getDay() + obj.getHours() + obj.getMinutes()
			+ obj.getMilliseconds();
}
/**
 * 添加date的format方法
 * 
 * @returns
 */
Date.prototype.format = function (format) {   
    var o = {   
        "M+": this.getMonth() + 1,   
        "d+": this.getDate(),   
        "H+": this.getHours(),   
        "m+": this.getMinutes(),   
        "s+": this.getSeconds(),   
        "q+": Math.floor((this.getMonth() + 3) / 3),   
        "S": this.getMilliseconds()   
    }   
    if (/(y+)/.test(format)) {   
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));   
    }   
    for (var k in o) {   
        if (new RegExp("(" + k + ")").test(format)) {   
         format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));   
        }   
    }   
    return format;   
}  