/**
 * 返回上一页
 * @returns {Boolean}
 */
function g_history_back() {
	// 使用back,不刷新页面(chrome和firefox会刷新)
	window.history.back(-1);
	return false;
}