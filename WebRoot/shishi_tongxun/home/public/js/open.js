//打开客服端
function jinrukefuduan() {
	window.open(base+"SysUser/login.jsp", "客服端",
			"left=200,top=15,width=955,height=605");
}
//打开顾客端传递客服id
function jinrugukeduan(id) {
	
	//获取并传递访问记录id
	var accessSessionId = localStorage.getItem("accessSessionId"); //获取本地accessSessionId的值
	var asidStr = "&accessSessionId="+accessSessionId;
	var urlLujing = "shishi_tongxun/xinxiFasong/guke.jsp?userid=" + id + asidStr;
	
	if(IsPC()){
		console.log('这是电脑');
	}else{
		console.log('这是手机');
		var yuming = base.indexOf("1010keke.com");
		if(yuming > -1){
			base = "http://121.43.108.229/";
		}
	}
	window.open(base+urlLujing, "咨询客服",
	"left=200,top=15,width=555,height=605");
}

//判断如果是手机则提示跳转到移动端页面
//判断是否是电脑
function IsPC() {
    var userAgentInfo = navigator.userAgent;
    console.log(userAgentInfo);
    var Agents = ["Android", "iPhone",
                "SymbianOS", "Windows Phone",
                "iPad", "iPod"];
    var flag = true;
    for (var v = 0; v < Agents.length; v++) {
        if (userAgentInfo.indexOf(Agents[v]) > 0) {
            flag = false;
            break;
        }
    }
    return flag;
}
