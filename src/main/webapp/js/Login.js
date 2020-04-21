/**
 * 登陆
 * @constructor
 */
function Login(parentNode){
    this.parentNode = parentNode.baseDom;
    this.window = parentNode.window;
    this._init();
}

/**
 * 销毁
 */
Login.prototype.dispose = function(){
    this._unbindEvent();
    this.window = null;
    this.parentNode = null;
    if(this.baseContainer)
        this.baseContainer = null;
    if(this.account)
        this.account = null;
    if(this.pwd)
        this.pwd = null;
    if(this.login)
        this.login = null;
    if(this.register)
        this.register = null;
    if(this.dlg){
        this.dlg.dispose();
        this.dlg = null;
    }
}

/**
 *初始化
 * @private
 */
Login.prototype._init = function () {
    doc = this.window.document;
    var container = this.parentNode.appendChild(doc.createElement("div"));
    container.className = "login_container";


    var baseContainerStr = "<div>" +
        "<div><span>账号</span><input name='account'></div> " +
        "<div><span>密码</span><input name='passwd' type='password'></div> " +
        "<button>登陆</button><button>注册</button><div>";

    container.innerHTML = baseContainerStr;
    const baseContainer =  container.firstElementChild;
    baseContainer.removeChild(baseContainer.lastElementChild)

    this.baseContainer = baseContainer;
    this.account = baseContainer.firstElementChild.lastElementChild;
    this.pwd = baseContainer.firstElementChild.nextElementSibling.lastElementChild;

    this.login = baseContainer.lastElementChild.previousSibling;
    this.register = baseContainer.lastElementChild;

    this._bindEvent();
}

/**
 *
 * @private
 */
Login.prototype._bindEvent = function(){
    const self = this;
    LibraryUtils.BINDEVENT(this.baseContainer,"click",function(evt){
        if(evt.target != self.login && evt.target != self.register) return;
        const acc = self.account.value;
        if(!acc){
            alert("请输入账号");
            return;
        }
        const pwd = self.pwd.value;
        if(!pwd){
            alert("请输入密码");
            return;
        }
        if(evt.target == self.login){
            self._login(acc,pwd);
        }else if(evt.target == self.register){
            self._register();
        }
        //阻止冒泡
        window.event? window.event.cancelBubble = true : evt.stopPropagation();
    });
}

/**
 * 事件解绑
 * @private
 */
Login.prototype._unbindEvent = function(){
    LibraryUtils.UNBINDEVENT(this.baseContainer);
}

/**
 * 用户登陆
 */
Login.prototype._login = function(acc,pwd){
    const self = this;
    LibraryUtils.AJAX("/Library/login",
        JSON.stringify({account:acc,password:pwd}),
        self,function(data){
            let a = 1;
        });
}

/**
 *
 * @private
 */
Login.prototype._register = function(){
    var self = this;
    if(!this.dlg){
        // let dlg = require("js.Dialog.Dialog.js");
        this.dlg = new Dialog({window:window,height:"300px",width:"400px"});

        this.dlg.setCancelEvt(function () {
            self.dlg.clear();
            self.dlg.show(false);
        });
        this.dlg.setConfirmEvt(function () {
            let acc = self.dlg.getAccount();
            let pwd = self.dlg.getPassword();
            LibraryUtils.AJAX("/Library/registeruser",
                JSON.stringify({account:acc,password:pwd}),
                self,function(data){
                    let a = 1;
                });
        });
    }
    this.dlg.show(true);
}