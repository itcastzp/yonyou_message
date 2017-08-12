
function YYIMChatInit(opt) {
    YYIMChat.init({
        onOpened: function(arg) {
            opt.onOpened && opt.onOpened(arg);
            // 登录成功
        },
        onExpiration: function(token, expiration) {
            //自动更新token
            //callback(token, expiration);
            opt.onExpiration && opt.onExpiration(token, expiration);
        },
        onClosed: function(arg) {
            opt.onClosed && opt.onClosed(arg);
            //连接关闭
        },
        onConflicted: function(arg) {
            //登陆冲突
            opt.onConflicted && opt.onConflicted(arg);
        },
        onClientKickout: function(arg) {
            //被他端踢掉
            opt.onClientKickout && opt.onClientKickout(arg);
        },
        onUpdatePassword: function(arg) {
            //更改密码，被踢掉
            opt.onUpdatePassword && opt.onUpdatePassword(arg);
        },
        onAuthError: function(arg) {
            //登陆认证失败
            opt.onAuthError && opt.onAuthError(arg);
        },
        onConnectError: function(arg) {
            //连接失败
            opt.onConnectError && opt.onConnectError(arg);
        },
        onReceipts: function(arg) {
            //消息回执
            opt.onReceipts && opt.onReceipts(arg);
        },
        onSubscribe: function(arg) {
            //发生订阅
            opt.onSubscribe && opt.onSubscribe(arg);
        },
        onRosterFavorited: function(arg) {
            //被收藏
            opt.onRosterFavorited && opt.onRosterFavorited(arg);
        },
        onRosterUpdateded: function(arg) {
            //好友信息更改
            opt.onRosterUpdateded && opt.onRosterUpdateded(arg);
        },
        onMessage: function(arg) {
            //收到消息
            opt.onMessage && opt.onMessage(arg);
        },
        onGroupUpdate: function(arg) {
            //群组更新
            opt.onGroupUpdate && opt.onGroupUpdate(arg);
        },
        onKickedOutGroup: function(arg) {
            //群成员被群主提出
            opt.onKickedOutGroup && opt.onKickedOutGroup(arg);
        },
        onTransferGroupOwner: function(arg){
            //群主转让
            opt.onTransferGroupOwner && opt.onTransferGroupOwner(arg);
        },
        onPresence: function(arg) {
            //好友presence改变
            opt.onPresence && opt.onPresence(arg);
        },
        onRosterDeleted: function(arg) {
            //好友被删除
            opt.onRosterDeleted && opt.onRosterDeleted(arg);
        },
        onPubaccountUpdate: function(arg) {
            //公共号信息更新
            opt.onPubaccountUpdate && opt.onPubaccountUpdate(arg);
        },
        onTransparentMessage: function(arg) {
            //透传业务消息 撤销、置顶等消息都在这里监听
            opt.onTransparentMessage && opt.onTransparentMessage(arg);
        }
    })
}


window.IMClient = {
    init: function(opt) {
        YYIMChat.initSDK({
            app: opt.appId,
            etp: opt.etpId
        });

        YYIMChatInit(opt);
    },
    login: function(opt) {
        YYIMChat.login(
            opt.userId, // 用户名
            opt.userToken // 密码或token  为空或null，控制台没有任何提示
        );
    },
    getRecentDigest: function(arg) {
        YYIMChat.getRecentDigset(arg)
    },
    getHistoryMessage: function(arg) {
        YYIMChat.getHistoryMessage(arg)
    },
    sendTextMessage: function(arg) {
        YYIMChat.sendTextMessage(arg);
    },
    revokeMessage: function(arg) {
        YYIMChat.revokeMessage(arg);
    }
}
