! function() {
    var j = document.createElement("ins");
    j.id = "newBridge";
    if (document.getElementById(j.id)) {
        return
    }
    var b = [{
            "inviteBox": {
                "skinIndex": 0,
                "startPage": 1,
                "customerStyle": {
                    "acceptFontColor": "#d7d7d7",
                    "backImg": "//sgoutong.baidu.com/static/style/images/af33abaa203f416b857fcf9b10edd6d0.jpg",
                    "acceptBgColor": "#353627"
                },
                "skinName": "自定义皮肤",
                "autoHide": 1,
                "reInvite": 1,
                "sendButton": {
                    "bgColor": "#bfecff",
                    "fontColor": "#1980df"
                },
                "isShowText": 0,
                "skinType": 1,
                "buttonType": 1,
                "autoInvite": 1,
                "stayTime": 5,
                "width": 350,
                "closeTime": 45,
                "isCustomerStyle": 1,
                "position": "middle",
                "inviteInterval": 25,
                "welcome": "<p style=\"color: #fff\">欢迎来到本网站，响应式网站优惠活动中！！！</p>",
                "height": 170
            },
            "webimConfig": {
                "skinIndex": 0,
                "skinType": 1
            },
            "noteBoard": {
                "skinIndex": 0,
                "skinType": 1,
                "displayCompany": 1,
                "cpyInfo": "北京天晴创艺科技有限公司",
                "skinName": "默认皮肤",
                "displayLxb": 1,
                "position": "left-bottom",
                "itemsExt": [],
                "items": [{
                    "name": "visitorName",
                    "required": 0,
                    "isShow": 1
                }, {
                    "name": "visitorPhone",
                    "required": 1,
                    "isShow": 1
                }, {
                    "name": "visitorEmail",
                    "required": 0,
                    "isShow": 0
                }, {
                    "name": "visitorAddress",
                    "required": 0,
                    "isShow": 1
                }],
                "cpyTel": "联系电话：13681552278",
                "isAlwaysDisplay": 0
            },
            "isWebim": 1,
            "pageId": 0,
            "seekIcon": {
                "customerStyle": {
                    "backImg": ""
                },
                "skinIndex": 9,
                "skinName": "商务客服",
                "groups": [{
                    "groupName": "在线客服001",
                    "groupId": 120862
                }, {
                    "groupName": "在线客服002",
                    "groupId": 120870
                }],
                "displayLxb": 1,
                "marginLeft": 0,
                "skinType": 1,
                "isFixedPosition": 1,
                "iconType": 0,
                "isCustomerStyle": 0,
                "width": 0,
                "groupStyle": {
                    "bgColor": "#ffffff",
                    "buttonColor": "#d6f3ff",
                    "fontColor": "#008edf"
                },
                "position": "right-center",
                "marginTop": 0,
                "height": 0
            }
        }],
        l = {
            "eid": "22902157",
            "queuing": "<p>欢迎光临！您已经进入服务队列，请您稍候，马上为您转接您的在线咨询顾问。</p>",
            "session": {
                "displayName": "1**5",
                "headUrl": "https://ss0.bdstatic.com/7Ls0a8Sm1A5BphGlnYG/sys/portraitn/item/153d31303231353637323035f608.jpg",
                "status": 0,
                "uid": 0,
                "uname": ""
            },
            "authToken": "bridge",
            "isWebim": 1,
            "userId": "22902157",
            "platform": 0,
            "route": "1",
            "webimConfig": {
                "skinIndex": 0,
                "skinType": 1
            },
            "siteId": "10590401",
            "online": "true",
            "authType": 4,
            "bid": "1503552210010590401",
            "webRoot": "//p.qiao.baidu.com/cps/",
            "timestamp": 1533020372906
        },
        m = [];

    function h() {
        var p = window.location.href,
            o = 0,
            n = null;
        if (m) {
            for (var i = 0, g = m.length; i < g; i++) {
                if (m[i].url === p) {
                    o = m[i].pageId;
                    break
                }
            }
            if (o === 0) {
                for (var i = 0, g = m.length; i < g; i++) {
                    if (-1 < p.indexOf(m[i].url)) {
                        o = m[i].pageId;
                        break
                    }
                }
            }
        }
        l.pageId = o;
        i = 0;
        for (g = b.length; i < g; i++) {
            if (b[i].pageId === o) {
                f = b[i].webimConfig;
                l.webimConfig = {
                    skinIndex: f.skinIndex,
                    skinType: f.skinType
                };
                return b[i]
            }
            0 === b[i].pageId && (n = b[i])
        }
        return n
    }
    j.config = h(), j.siteConfig = l, j.startTime = +new Date;
    j.deferOnce = {
        on: function(e) {
            this.done ? e() : this.callback = e
        },
        emit: function() {
            this.callback ? this.callback() : (this.done = true)
        }
    };
    var k = document.getElementsByTagName("script")[0];
    k.parentNode.insertBefore(j, k);

    function c() {
        if (document.body) {
            document.body.insertBefore(j, document.body.firstElement || null);
            j.deferOnce.emit()
        } else {
            setTimeout(c, 0)
        }
    }
    c();
    var d = document.createElement("script");
    d.src = "//sgoutong.baidu.com/embed/1532575172/asset/embed/pc_nb.js", d.setAttribute("charset", "UTF-8");
    var a = document.getElementsByTagName("head")[0] || document.body;
    a.insertBefore(d, a.firstElement || null)
}(this);