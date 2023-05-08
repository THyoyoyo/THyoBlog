
/**
 * 合并相同key的数据
 *@target 目标对象
 *@source 数据源对象
 *
*/
export let merge = (target, source) => {
    for (const key in source) {
        if (target.hasOwnProperty(key)) {
            target[key] = source[key];
        }
    }
}

// 时间格式化
export let transitionTime = (value) => {
    if (value.indexOf("T") != -1) {
        let timeArr = value.split("T");
        return `${timeArr[0]} ${timeArr[1]}`
    } else {
        return value
    }
}

// 剔除所有标签 取出内容

export let getAllCotnent = (str = "") => {
    let div = document.createElement("div");
    div.innerHTML = str;
    let ctx = div.textContent.trim();
    return ctx || ""
}


// 取url参数 
export function getQueryString(searchUrl, name) {
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    var r = searchUrl.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}


// 时间戳 => 2023-05-05 19:15:47
export function timestampToDatetime(timestamp) {
    const date = new Date(timestamp * 1000); // 转换为毫秒
    const year = date.getFullYear();
    const month = ('0' + (date.getMonth() + 1)).slice(-2);
    const day = ('0' + date.getDate()).slice(-2);
    const hour = ('0' + date.getHours()).slice(-2);
    const minute = ('0' + date.getMinutes()).slice(-2);
    const second = ('0' + date.getSeconds()).slice(-2);
    return `${year}-${month}-${day} ${hour}:${minute}:${second}`;
}