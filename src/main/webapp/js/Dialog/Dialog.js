// define(function(){
//     return {Dialog:Dialog};
// });
function Dialog(dialogInfo){
    this._init(dialogInfo);
}

Dialog.prototype.dispose = function(){
    this.win = null;
    this.doc = null;
    this.cancel = null;
    this.confirm = null;
    this.container = null;
    this.account = null;
    this.password = null;
}

Dialog.prototype._init = function(dialogInfo){
    this.win = dialogInfo.window;
    this.doc = this.win.document;
    this._initFrame(dialogInfo);
}

Dialog.prototype._initFrame = function(dialogInfo){
    let dialog = this.doc.body.appendChild(this.doc.createElement("div"));
    this.container = dialog;
    let containerStr = "<div style='display: inline-block'></div>" +
        "<div style='display: inline-block'></div>" +
        "<div style='display: inline-block'></div>";
    dialog.innerHTML = containerStr;

    this._initDefaultStyle(dialogInfo);

    let head = dialog.firstElementChild;
    let body = dialog.firstElementChild.nextElementSibling;
    let foot = dialog.lastElementChild;

    this._initHead(head);
    this._initBody(body);
    this._initFoot(foot);
}

Dialog.prototype._initDefaultStyle = function(dialogInfo){
    let h = dialogInfo.height;
    let w = dialogInfo.width;
    let dlg = this.container;
    dlg.style.height = h;
    dlg.style.width = w;
    dlg.style.zIndex = "9999";
    dlg.style.border = "solid 1px";
    dlg.style.position = "relative";

    let body = this.doc.body;
    let left = (body.offsetWidth - parseInt(w))/2;
    let bottom = (body.offsetHeight - parseInt(h))/2;
    dlg.style.cssText += ";display:inline-block;bottom:" + bottom + "px;left:" + left + "px;";
}

Dialog.prototype._initHead = function(container){
    container.style.cssText += ";height:15%;width:100%";
}

Dialog.prototype._initBody = function(container){
    container.style.cssText += ";height:70%;width:100%";
    let table = container.appendChild(this.doc.createElement("table"));
    table.style.cssText += ";width:100%;height:100%;";
    let tr1 = table.appendChild(this.doc.createElement("tr"));
    let tr1_td1 = tr1.appendChild(this.doc.createElement("td"));
    tr1_td1.innerText = "账号";
    let tr1_td2 = tr1.appendChild(this.doc.createElement("td"));
    let account = tr1_td2.appendChild(this.doc.createElement("input"));
    this.account = account;

    let tr2 = table.appendChild(this.doc.createElement("tr"));
    let tr2_td1 = tr2.appendChild(this.doc.createElement("td"));
    tr2_td1.innerText = "密码";
    let tr2_td2 = tr2.appendChild(this.doc.createElement("td"));
    let password = tr2_td2.appendChild(this.doc.createElement("input"));
    this.password = password;
}

Dialog.prototype._initFoot = function(container){
    container.style.cssText += ";height:15%;width:100%";
    let cancel = container.appendChild(this.doc.createElement("button"));
    cancel.innerText = "取消";
    let confirm = container.appendChild(this.doc.createElement("button"));
    confirm.innerText = "确定";
    this.cancel = cancel;
    this.confirm = confirm;
}

Dialog.prototype.getAccount = function(){
    return this.account.value;
}

Dialog.prototype.getPassword = function(){
    return this.password.value;
}

Dialog.prototype.clear = function(){
    this.account.value = "";
    this.password.value = "";
}

/**
 * 设置取消事件
 * @param callback
 */
Dialog.prototype.setCancelEvt = function(callback){
    LibraryUtils.BINDEVENT(this.cancel,"click",callback);
}

/**
 * 设置确定事件
 * @param callback
 */
Dialog.prototype.setConfirmEvt = function(callback){
    LibraryUtils.BINDEVENT(this.confirm,"click",callback);
}

Dialog.prototype.show = function(status){
    if(status){
        this.container.style.cssText += ";display:inline-block";
    }else{
        this.container.style.cssText += ";display:none";
    }
}