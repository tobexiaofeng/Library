function LibraryUtils(){

}

/**
 *Ajax请求
 * url:请求路径
 * data:请求的数据
 * context:让回调函数内 this 指向这个对象
 * success:回调函数
 * timeout:20 超时时间
 * contextType:默认值: "application/x-www-form-urlencoded"。发送信息至服务器时内容编码类型。
 * dataType
 * 类型：String
 * 预期服务器返回的数据类型。如果不指定，jQuery 将自动根据 HTTP 包 MIME 信息来智能判断，比如 XML MIME 类型就被识别为 XML。在 1.4 中，JSON 就会生成一个 JavaScript 对象，而 script 则会执行这个脚本。随后服务器端返回的数据会根据这个值解析后，传递给回调函数。可用值:
 * "xml": 返回 XML 文档，可用 jQuery 处理。
 * "html": 返回纯文本 HTML 信息；包含的 script 标签会在插入 dom 时执行。
 * "script": 返回纯文本 JavaScript 代码。不会自动缓存结果。除非设置了 "cache" 参数。注意：在远程请求时(不在同一个域下)，所有 POST 请求都将转为 GET 请求。（因为将使用 DOM 的 script标签来加载）
 * "json": 返回 JSON 数据 。
 * "jsonp": JSONP 格式。使用 JSONP 形式调用函数时，如 "myurl?callback=?" jQuery 将自动替换 ? 为正确的函数名，以执行回调函数。
 * "text": 返回纯文本字符串
 */
LibraryUtils.AJAX = function(url,data,cxt,callback){

    $.ajax({ url:url,
        data:data,
        context: cxt,
        async : true,
        contentType:"application/json;charset=utf-8",
        type:"POST",success: callback});
}

/**
 * 事件绑定
 * @param dom
 * @param evtType
 * @param callback
 * @constructor
 */
LibraryUtils.BINDEVENT = function(dom,evtType,callback){
    $(dom).bind(evtType,callback)
}

/**
 * 事件解绑
 * @param dom
 * @constructor
 */
LibraryUtils.UNBINDEVENT = function(dom){
    $(dom).unbind();
}

/**
 * 继承实现
 * @param child
 * @param father
 * @constructor
 */
LibraryUtils.EXTENDS = function(child,father){
    child.prototype = Object.create(father.prototype);
    child.prototype.constructor = child;
}