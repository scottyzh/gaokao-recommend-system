window.Utils = {};
Utils.debounce = function (fn, wait, immediate) {
    var timer;
    return function () {
        var that = this;
        var _arguments = arguments;
        if (timer) clearTimeout(timer);
        if (immediate) {
            var callNow = !timer;
            timer = setTimeout(function () {
                timer = null;
            }, wait);
            if (callNow) {
                fn.apply(that, _arguments);
            }
        } else {
            timer = setTimeout(function () {
                fn.apply(that, _arguments);
            }, wait);
        }
    }
};
Utils.changeUrlArg = function (url, param, param_val) {
    var pattern = param + '=([^&]*)';
    var replaceText = param + '=' + param_val;
    if (url.match(pattern)) {
        var tmp = '/(' + param + '=)([^&]*)/gi';
        tmp = url.replace(eval(tmp), replaceText);
        return tmp;
    } else {
        if (url.match('[\?]')) {
            return url + '&' + replaceText;
        } else {
            return url + '?' + replaceText;
        }
    }
};
Utils.replaceUrl = function (newUrl) {
    console.log('window.history.pushState', typeof (window.history.pushState));
    if (typeof (window.history.pushState) === 'function') {
        window.history.pushState(null, null, newUrl);
    } else {
        window.location.replace(newUrl);
    }
};
Utils.drag = function (config) {

};
var modelOpen='<div class="tishinone" style="margin-top:20px;display:flex;align-items:center"><p style="width:10%"><i style="color:#ff5722;font-size:24px" class="layui-icon layui-icon-tips"></i></p><span style="font-size:18px;">提示</span></div>';

