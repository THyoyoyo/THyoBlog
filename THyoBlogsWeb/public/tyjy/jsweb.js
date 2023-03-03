;
! function (win) {
    "use strict";
    if (!win.localStorage) { //localStorage 兼容
        win.localStorage = {
            getItem: function (sKey) {
                if (!sKey || !this.hasOwnProperty(sKey)) {
                    return null;
                }
                return unescape(document.cookie.replace(new RegExp("(?:^|.*;\\s*)" + escape(sKey).replace(/[\-\.\+\*]/g, "\\$&") + "\\s*\\=\\s*((?:[^;](?!;))*[^;]?).*"), "$1"));
            },
            key: function (nKeyId) {
                return unescape(document.cookie.replace(/\s*\=(?:.(?!;))*$/, "").split(/\s*\=(?:[^;](?!;))*[^;]?;\s*/)[nKeyId]);
            },
            setItem: function (sKey, sValue) {
                if (!sKey) {
                    return;
                }
                document.cookie = escape(sKey) + "=" + escape(sValue) + "; expires=Tue, 19 Jan 2038 03:14:07 GMT; path=/";
                this.length = document.cookie.match(/\=/g).length;
            },
            length: 0,
            removeItem: function (sKey) {
                if (!sKey || !this.hasOwnProperty(sKey)) {
                    return;
                }
                document.cookie = escape(sKey) + "=; expires=Thu, 01 Jan 1970 00:00:00 GMT; path=/";
                this.length--;
            },
            hasOwnProperty: function (sKey) {
                return (new RegExp("(?:^|;\\s*)" + escape(sKey).replace(/[\-\.\+\*]/g, "\\$&") + "\\s*\\=")).test(document.cookie);
            }
        };
        win.localStorage.length = (document.cookie.match(/\=/g) || win.localStorage).length;
    }

    var JsWeb = function () {
        this.moduleUrl = null;
        this.url = null;
    };
    //根据wap,pad,pc跳转页面
    JsWeb.prototype.jumpWhickPage = function (inviter, type, page) {
        if (/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)) {
            // var act_clientWidth = document.documentElement.clientWidth //适配大于750小于1200的屏的
            // if (act_clientWidth > 750 && act_clientWidth < 1200 && type != "pad") {
            //     window.location = "./" + page + "_pad.html" + inviter;
            // } else act_clientWidth <= 750 &&
            if (type != 'wap') {
                window.location = "./" + page + "_wap.html" + inviter;
            }
        } else if (type != "pc") {
            window.location = "./" + page + ".html" + inviter;
        }
    }
    //九宫格领奖
    JsWeb.prototype.actMove = function (selector, turns, getgrid, callback) { //元素节点（中间按钮的命名规则和动元素的命名一致），圈数，中奖号码（大于0，小于9）
        var index = 1; //获取格子的索引
        var count = 0; //当前轮数
        var time = 100;
        var fuc = function () {
            setTimeout(function () {
                $("." + selector + index).addClass("act_moving").siblings().removeClass("act_moving");
                index++
                if (index > 8) {
                    index = 1;
                    count++; //轮数++
                }
                //开始减速
                if (count >= turns) {
                    time += 50;
                }
                //递归继续调用此函数，让灯继续转起来
                if (count == turns && (index == 1 ? 8 : index - 1) === getgrid) {
                    //已经中奖，停止灯（不继续递归）
                    $("." + selector + "_btn").prop("disabled", false);
                    if (callback) callback.call(window);
                } else {
                    fuc();
                }
            }, time);
        }
        fuc()
    }
    // 页面缩放适配

    JsWeb.prototype.adaption = function (idArr, basicInnerWidth, basicOuterWidth) {
        try {
            if (!isSupportAdaption() || this.getTerminal().mobile) {
                document.body.style.display = "block";
                return false;
            }

            yebaoWebSelfAdaption(idArr);

            window.onresize = function () {
                yebaoWebSelfAdaption(idArr);
            };
        } catch (error) {
            document.body.style.display = "block";
        }

        function yebaoWebSelfAdaption(idArr) {
            var type = isSupportAdaption();
            var w = document.documentElement.offsetWidth || document.body.offsetWidth;
            var bow = parseInt(basicOuterWidth ? basicOuterWidth : 1920);
            var biw = parseInt(basicInnerWidth ? basicInnerWidth : 1200);
            var limit = biw / bow;
            var z = function () {
                if (w === bow - 17) {
                    return 1;
                } else {
                    return w / bow >= limit ? w / bow : limit;
                }
            }();

            var isRecharge = document.getElementById("recharge_gw");
            var personalIp = document.getElementById("recharge_personal_ip");

            if (type === "zoom") {
                for (var i = 0; i < idArr.length; i++) {
                    var element = idArr[i];
                    document.getElementById(element).style.zoom = z;
                    isRecharge && (isRecharge.contentWindow.document.body.style.zoom = z);
                    personalIp && (personalIp.contentWindow.document.body.style.zoom = z);
                }
            } else if (type === "scale ie" || type === "scale firefox") {
                if (z < 1) {
                    document.body.style.display = "block";
                    return false;
                }
                for (var j = 0; j < idArr.length; j++) {
                    var element = idArr[j];
                    document.getElementById(element).style.transform = "scale(" + z + ", " + z + ")";
                    document.getElementById(element).style.transformOrigin = "center top";

                    isRecharge && (isRecharge.contentWindow.document.body.style.transform = "scale(" + z + ", " + z + ")") && (isRecharge.contentWindow.document.body.style.transformOrigin = "center top");
                    personalIp && (personalIp.contentWindow.document.body.style.transform = "scale(" + z + ", " + z + ")") && (personalIp.contentWindow.document.body.style.transformOrigin = "center top");
                }
            }

            document.body.style.display = "block";

            // type === "scale firefox" && (window.location.pathname === "/" || window.location.indexOf("/index.shtml") > -1) && (document.getElementById("header").style.top = "-100px");
        }


        function isSupportAdaption() {
            var userAgent = navigator.userAgent;
            var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1;
            var isIE11 = userAgent.indexOf('Trident') > -1 && userAgent.indexOf("rv:11.0") > -1;
            var isFireFox = userAgent.indexOf('Firefox') > -1;

            if (isIE || isIE11 || isFireFox) {
                if (isIE) {
                    var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
                    reIE.test(userAgent);
                    var fIEVersion = parseFloat(RegExp["$1"]);
                    if (fIEVersion > 9) {
                        return "scale ie";
                    } else {
                        return false;
                    }
                } else if (isIE11) {
                    return "scale ie";
                } else {
                    return "scale firefox";
                }
            } else {
                return "zoom";
            }
        }
    }


    // ajax请求封装

    JsWeb.prototype.req = function (url, opthion, callback, errorCallback, endCallback) {
        // _jsweb.req(Yb_Login + "/api/v1/yb_login/gw/login_send?a=1", {}, function (res) {    //get

        // }, function (err) {

        // });

        // _jsweb.req(Yb_Login + "/api/v1/yb_login/gw/login_send?a=1", {method: "post", data: {a:1}}, function (res) {    //post

        // }, function (err) {

        // });

        if (!$) {
            console.warn("方法需要先加载Jquery");
            return false;
        }
        // opthion:
        // method
        // data
        // contentType
        // dataType
        // withCookie

        document.all && ($.support.cors = true); //IE 跨域设置
        var setting = {
            url: url,
            type: opthion.method ? opthion.method : "get",
            dataType: opthion.dataType ? opthion.dataType : "json",
            async: opthion.async === false ? false : true,
            contentType: (opthion.method ? opthion.method : "get") === "get" ? "application/x-www-form-urlencoded" : (opthion.contentType ? opthion.contentType : "application/json"),
            xhr: function () {
                if (win.ActiveXObject) {
                    return new win.ActiveXObject("Microsoft.XMLHTTP");
                } else {
                    return new win.XMLHttpRequest();
                }
            },
            data: (opthion.method ? opthion.method : "get") === "get" ? (opthion.data ? opthion.data : {}) : (opthion.data ? JSON.stringify(opthion.data) : {}),
            beforeSend: function (request) {
                if (opthion.headers) {
                    if(!opthion.headers.clear) {
                        var isToken = false;
                        for (var key in opthion.headers) {
                            key === "token" && (isToken = true);
                            var value = opthion.headers[key];
                            request.setRequestHeader(key, value);
                        }
                        !isToken && request.setRequestHeader("token", localStorage.getItem("tengyou_token"));
                    }
                } else {
                    request.setRequestHeader("token", localStorage.getItem("tengyou_token"));
                }
            },
            success: function (res) {
                callback && callback.call(win, res);
            },
            error: function (err) {
                errorCallback && errorCallback.call(win, err);
            },
            complete: function (res) {
                endCallback && endCallback.call(win, res);
            }
        };

        opthion.timeout && (setting.timeout = opthion.timeout);

        opthion.dataType == "jsonp" && (setting.jsonp = "callback");

        opthion.withCookie && (setting.crossDomain = true) && (setting.xhrFields = {
            withCredentials: true
        }); //跨域带cookies

        $.ajax(setting);
    }
    //手机号码加密
    JsWeb.prototype.hidePhone = function (phone) {
        var isnum = /^\d+$/.test(phone)
        if (isnum) {
            var tel = phone;
            tel = "" + tel;
            var ary = tel.split("");
            ary.splice(3, 4, "****");
            var tel1 = ary.join("");
            return tel1 //153****1208;
        } else {
            return phone;
        }
    }
    //活动列表滚动PCpx
    JsWeb.prototype.listScroll = function (selector, data, col, row, interval, type) {
        // _jsweb.listScroll(el, res.data, 2, 3);    //纵向
        // _jsweb.listScroll(el, res.data, 3, 99999, 99999, "row");   //横向

        if (!$) {
            console.warn("方法需要先加载Jquery");
            return false;
        }

        //list render 
        var box = $(selector);
        var boxWidth = box.width();
        var boxHeight = box.height();
        var boxListClass = "awardListScroll" + parseInt(Math.random() * 1000);

        if (type === "row") {
            row = 1;
            interval = 50;

            var html = "<ul style='width: " + ((boxWidth / col) - 10) * data.length + "px;height: " + (Math.ceil(Math.ceil((data.length / col)) / row) * boxHeight) + "px; float: left'>";
            for (var i = 0; i < data.length; i++) {
                html += "<li style='width:" + ((boxWidth / col) - 10) + "px;height: " + (boxHeight / row) + "px;line-height: " + (boxHeight / row) + "px;list-style: none;float: left;white-space: nowrap;text-overflow: ellipsis;overflow: hidden;text-align: center;padding: 0 5px;" + (i == 0 ? "clear: both;" : "") + "'>" + data[i] + "</li>";
            }
        } else if (type === "col") {
            interval = 50;

            var html = "<ul style='height: " + (Math.ceil(boxHeight / row  * data.length)) + "px'>";
            for (var i = 0; i < data.length; i++) {
                html += "<li style='width:" + ((boxWidth / col) - 10) + "px;height: " + (boxHeight / row) + "px;line-height: " + (boxHeight / row) + "px;list-style: none;float: left;white-space: nowrap;text-overflow: ellipsis;overflow: hidden;text-align: center;padding: 0 5px;" + (i == 0 ? "clear: both;" : "") + "'>" + data[i] + "</li>";
            }
        } else {
            var html = "<ul style='height: " + (Math.ceil(Math.ceil((data.length / col)) / row) * boxHeight) + "px'>";
            for (var i = 0; i < data.length; i++) {
                html += "<li title='" + data[i].match(/(.*?)<span>/)[1] + data[i].match(/<span>(.*?)<\/span>/)[1] + "' style='width:" + ((boxWidth / col) - 10) + "px;height: " + (boxHeight / row) + "px;line-height: " + (boxHeight / row) + "px;list-style: none;float: left;white-space: nowrap;text-overflow: ellipsis;overflow: hidden;text-align: center;padding: 0 5px;" + (i == 0 ? "clear: both;" : "") + "'>" + data[i] + "</li>";
            }
        }

        html += "</ul>";

        if (type === "row") {
            box.html("<div class='" + boxListClass + "' style='width: " + ((boxWidth / col) - 10) * data.length * 2 + "px;position: absolute; top: 0;left: 0'>" + html + html + "</div>");
        } else {
            box.html("<div class='" + boxListClass + "' style='position: absolute; top: 0;left: 0'>" + html + html + "</div>");
        }

        //scroll
        var boxList = $("." + boxListClass);

        var timer;
        clearInterval(timer);
        var times = 0;

        if (type === "row") {
            var move = function () {
                if (boxList.position().left <= -((boxWidth / col) - 10) * data.length) {
                    boxList.css("left", 0);
                } else {
                    boxList.css("left", boxList.position().left - 1);
                }
            }
        } else if (type === "col") {
            var move = function () {
                if (boxList.position().top + (boxList.height() / 2) < 1) {     //缩放兼容
                    boxList.css("top", 0);
                } else {
                    boxList.css("top", boxList.position().top - 1);
                }
            }
        } else {
            var move = function () {
                if (boxList.position().top + (boxList.height() / 2) < 1) {     //缩放兼容
                    times = 0;
                    boxList.css("top", 0);
                }
                times++;
                boxList.animate({
                    top: -(boxHeight * times) + "px"
                });
            }
        }

        timer = setInterval(move, interval ? interval : 2000);
    }
    //活动列表滚动手机rem
    JsWeb.prototype.listScrollWap = function (selector, data, col, row, interval) {
        if (!$) {
            console.warn("方法需要先加载Jquery");
            return false;
        }

        //list render 
        var box = $(selector);
        var boxWidth = box.width();
        var boxHeight = box.height();
        var boxListClass = "awardListScroll" + parseInt(Math.random() * 1000);
        var html = "<ul style='height: " + (Math.ceil(Math.ceil((data.length / col)) / row) * boxHeight) + "px'>";
        for (var i = 0; i < data.length; i++) {
            html += "<li style='width:" + ((boxWidth / col) - 10) + "px;height: " + (boxHeight / row) + "px;line-height: " + (boxHeight / row) + "px;list-style: none;float: left;white-space: nowrap;text-overflow: ellipsis;overflow: hidden;text-align: center;padding: 0 5px;" + (i == 0 ? "clear: both;" : "") + "'>" + data[i] + "</li>";
        }
        html += "</ul>";
        box.html("<div class='" + boxListClass + "' style='position: absolute; top: 0;left: 0'>" + html + html + "</div>");

        //scroll
        var boxList = $("." + boxListClass);

        var timer;
        clearInterval(timer);
        var times = 0;
        var move = function () {
            if (boxList.position().top === -(boxList.height() / 2)) {
                times = 0;
                boxList.css("top", 0);
            }
            times++;
            boxList.animate({
                top: -(boxHeight * times) + "px"
            });
        }
        timer = setInterval(move, interval ? interval : 2000);
    }

    //获取链接参数
    JsWeb.prototype.getParam = function () {
        var Url = win.location.href;
        var u, g, StrBack = '';
        if (arguments[arguments.length - 1] == "#")
            u = Url.split("#");
        else
            u = Url.split("?");
        if (u.length == 1) g = '';
        else g = u[1];

        if (g != '') {
            var gg = g.split("&");
            var MaxI = gg.length;
            var str = arguments[0] + "=";
            for (var xm = 0; xm < MaxI; xm++) {
                if (gg[xm].indexOf(str) == 0) {
                    StrBack = gg[xm].replace(str, "");
                    break;
                }
            }
        }
        return StrBack;
    }

    //base64 加密
    JsWeb.prototype.Base64Encode = function (input) {
        var _keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
        var output = "";
        var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
        var i = 0;
        input = utf8Encode(input);
        while (i < input.length) {
            chr1 = input.charCodeAt(i++);
            chr2 = input.charCodeAt(i++);
            chr3 = input.charCodeAt(i++);
            enc1 = chr1 >> 2;
            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
            enc4 = chr3 & 63;
            if (isNaN(chr2)) {
                enc3 = enc4 = 64;
            } else if (isNaN(chr3)) {
                enc4 = 64;
            }
            output = output +
                _keyStr.charAt(enc1) + _keyStr.charAt(enc2) +
                _keyStr.charAt(enc3) + _keyStr.charAt(enc4);
        }

        function utf8Encode(string) {
            string = string.replace(/\r\n/g, "\n");
            var utftext = "";
            for (var n = 0; n < string.length; n++) {
                var c = string.charCodeAt(n);
                if (c < 128) {
                    utftext += String.fromCharCode(c);
                } else if ((c > 127) && (c < 2048)) {
                    utftext += String.fromCharCode((c >> 6) | 192);
                    utftext += String.fromCharCode((c & 63) | 128);
                } else {
                    utftext += String.fromCharCode((c >> 12) | 224);
                    utftext += String.fromCharCode(((c >> 6) & 63) | 128);
                    utftext += String.fromCharCode((c & 63) | 128);
                }
            }
            return utftext;
        }
        return output;
    }

    //base64解密
    JsWeb.prototype.Base64Decode = function (input) {
        var _keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
        var output = "";
        var chr1, chr2, chr3;
        var enc1, enc2, enc3, enc4;
        var i = 0;
        input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
        while (i < input.length) {
            enc1 = _keyStr.indexOf(input.charAt(i++));
            enc2 = _keyStr.indexOf(input.charAt(i++));
            enc3 = _keyStr.indexOf(input.charAt(i++));
            enc4 = _keyStr.indexOf(input.charAt(i++));
            chr1 = (enc1 << 2) | (enc2 >> 4);
            chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
            chr3 = ((enc3 & 3) << 6) | enc4;
            output = output + String.fromCharCode(chr1);
            if (enc3 != 64) {
                output = output + String.fromCharCode(chr2);
            }
            if (enc4 != 64) {
                output = output + String.fromCharCode(chr3);
            }
        }
        output = utf8Decode(output);

        function utf8Decode(utftext) {
            var string = "";
            var i = 0;
            var c = 0,
                c1 = 0,
                c2 = 0;
            c = c1 = c2;
            while (i < utftext.length) {
                c = utftext.charCodeAt(i);
                if (c < 128) {
                    string += String.fromCharCode(c);
                    i++;
                } else if ((c > 191) && (c < 224)) {
                    c2 = utftext.charCodeAt(i + 1);
                    string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
                    i += 2;
                } else {
                    c2 = utftext.charCodeAt(i + 1);
                    c3 = utftext.charCodeAt(i + 2);
                    string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
                    i += 3;
                }
            }
            return string;
        }

        return output;
    }

    //日期格式化
    JsWeb.prototype.dateFormate = function (fmt, date) {
        date = date || new Date();
        var o = {
            "M+": date.getMonth() + 1,
            "d+": date.getDate(),
            "h+": date.getHours(),
            "m+": date.getMinutes(),
            "s+": date.getSeconds(),
            "q+": Math.floor((date.getMonth() + 3) / 3),
            "S": date.getMilliseconds()
        };
        if (/(y+)/.test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
        }
        for (var k in o) {
            if (new RegExp("(" + k + ")").test(fmt)) {
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            }
        }
        return fmt;
    }

    //倒计时
    JsWeb.prototype.getEndTime = function (endDate, startTime) {
        var startTime = startTime || +new Date;
        var endTime = new Date(endDate).getTime();
        var t = endTime - startTime;
        var d = 0,
            h = 0,
            m = 0,
            s = 0;
        var data = {};
        if (t >= 0) {
            d = Math.floor(t / 1000 / 3600 / 24);
            h = Math.floor(t / 1000 / 60 / 60 % 24);
            m = Math.floor(t / 1000 / 60 % 60);
            s = Math.floor(t / 1000 % 60);
        }
        data = {
            str: d + "天" + h + "时" + m + "分" + s + "秒",
            day: d,
            hour: h,
            min: m,
            sec: s
        };
        return data;
    }

    //获取终端信息
    JsWeb.prototype.getTerminal = function () {
        var u = navigator.userAgent;
        var terminal = {
            mobile: !!u.match(/AppleWebKit.*Mobile.*/) || u.indexOf("tengyou-app") > -1,
            ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/),
            android: u.indexOf("Android") > -1 || u.indexOf("Linux") > -1,
            iPhone: u.indexOf("iPhone") > -1,
            iPad: u.indexOf("iPad") > -1,
            weixin: u.indexOf("MicroMessenger") > -1,
            qq: u.match(/\sQQ/i) == " qq"
        };

        return terminal;
    }


    JsWeb.prototype.throttle = function(fn, wait) {
        var timer = null;
        return function () {
            var context = this;
            var args = arguments;
            if (!timer) {
                timer = setTimeout(function () {
                    fn.apply(context, args);
                    timer = null;
                }, wait)
            }
        }
    }
    
    JsWeb.prototype.debounce = function(func, wait, immediate) {
        var timer;
    
        return function () {
            var context = this;
            var args = arguments;
    
            if (timer) clearTimeout(timer);
            if (immediate) {
                var callNow = !timer;
                timer = setTimeout(function (){
                    timer = null;
                }, wait);
    
                if (callNow) func.apply(context, args);
            } else {
                timer = setTimeout(function(){
                    func.apply(context, args)
                }, wait);
            }
        }
    }

    JsWeb.prototype.hash = function () {
        var code = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'];
        var hash = '';

        for(var i = 0; i < 6; i++) {
            if(i % 2 === 0) {
                hash += code[Math.floor(Math.random() * 26)];
            } else {
                hash += Math.floor(Math.random() * 10);
            }
        }

        return hash;
    }

    //腾讯图形验证
    JsWeb.prototype.TCaptcha = function (opt, cb) {
        var createTicket = function() {
            var appid = ''
            // 生成容灾票据或自行做其它处理
            var ticket = 'terror_1001_' + appid + Math.floor(new Date().getTime() / 1000);

            return {
                ticket: ticket,
                randstr: '@'+ Math.random().toString(36).substr(2)
            }
        }

        var callback = function(res) {
            typeof sensors !== "undefined" && sensors.track('captcha_process', {
                status: "pass" 
            });

            var obj = {
                ticket: res.ticket,
                randstr: res.randstr
            }
            
            if(res.errorCode) {
                obj = createTicket();
            }

            if(obj.ticket) {
                obj.ret = 0;
            } else {
                obj.ret = 2;
            }

            cb(obj);
        }

        try {
            typeof sensors !== "undefined" && sensors.track('captcha_process', {
                status: "load" 
            });

            var ready = null;
            opt.ready && (ready = opt.ready);
            opt.ready = function() {
                typeof sensors !== "undefined" && sensors.track('captcha_process', {
                    status: "show" 
                });

                ready && ready();
            }

            var captcha = new TencentCaptcha('191913024', callback, opt);
            captcha.show();
        } catch (error) {
            typeof sensors !== "undefined" && sensors.track('captcha_process', {
                status: "load_fail" 
            });

            callback({
                errorCode: 1001,
                errorMessage: '图形验证加载错误，请联系客服'
            });
        }
    }

    //神策上报
    JsWeb.prototype.scReport = function (pagetype, other, callback) {
        var sensors = window['sensorsDataAnalytic201505'];
        sensors.init({
            server_url: 'https://sensorsdata.web.bigdata.fulu.com:8106/sa?project=yunshi_pro',
            is_track_single_page: true,
            use_client_time: true, 
            send_type: 'beacon',
            heatmap: {
                clickmap: 'not_collect',
                scroll_notice_map: 'default'
            }
        });

        var obj = {
            create_time: this.dateFormate('yyyy-MM-dd hh:mm:ss'),
            platform_type: 'tengyou',
            pagetype: pagetype
        }

        var label = "";
        if(_jsweb.getTerminal().mobile || _jsweb.getTerminal().ios || _jsweb.getTerminal().iPad) {
            if(window.location.href.indexOf("mobile_token") > -1) {
                label = "M_Client";
            } else {
                label = "M_H5";
            }
        } else {
            if(window.location.href.indexOf("token") > -1 && window.location.href.indexOf("mac") > -1) {
                label = "PC_Client";
            } else {
                label = "PC_Web";
            }
        }
        obj.label = label;

        var arr = ["user_id", "user_account", "guid", "cv", "ct", "cn", "ip", "mac"];
        for (var index = 0; index < arr.length; index++) {
            var element = arr[index];
            if(element === "user_account") {
                obj["phone"] = this.getParam(element) || localStorage.getItem("tengyou_account") || "";
            } else if(element === "user_id") {
                obj["uid"] = this.getParam(element) || localStorage.getItem("tengyou_userid") || "";
            } else {
                obj[element] = this.getParam(element) || localStorage.getItem(element) || "";
            }
        }

        sensors.registerPage(obj);
        other !== false && sensors.quick('autoTrack', Object.prototype.toString.call(other) === "[object Object]" ? other : {});
        callback && callback(sensors);
    }    

    //模块加载
    JsWeb.prototype.use = function (mod, callback) {
        var that = this,
            head = document.getElementsByTagName('head')[0],
            node = document.createElement('script'),
            mod = typeof mod === 'string' ? [mod] : mod,
            modName = mod[0];

        if (!modName) {
            callback.apply(_jsweb);
            return;
        }

        this.moduleUrl = "https://www.tengyoujiasu.com/lib/javascript/module/";

        if(modName.indexOf(".") > -1) {      //业务js
            this.moduleUrl = "";
        }

        if(modName === "TCaptcha") {
            this.moduleUrl = "https://ssl.captcha.qq.com/";
        }

        this.url = this.moduleUrl + modName + ".js?v=" + this.hash();

        node.async = true;
        node.charset = 'utf-8';
        node.src = this.url;

        head.appendChild(node);

        if (node.attachEvent && !(node.attachEvent.toString && node.attachEvent.toString().indexOf('[native code') < 0) && !(typeof opera !== 'undefined' && opera.toString() === '[object Opera]')) {
            node.attachEvent('onreadystatechange', function (e) {
                loaded(e);
            });
        } else {
            node.addEventListener('load', function (e) {
                loaded(e);
            }, false);
        }

        function loaded(e) {
            var readyRegExp = navigator.platform === 'PLaySTATION 3' ? /^complete$/ : /^(complete|loaded)$/;
            if (e.type === 'load' || (readyRegExp.test((e.currentTarget || e.srcElement).readyState))) {
                mod.length > 1 ? that.use(mod.slice(1), callback) : (typeof callback === 'function' && callback.apply(_jsweb));
            }
        }
    };

    win._jsweb = new JsWeb();
}(window);