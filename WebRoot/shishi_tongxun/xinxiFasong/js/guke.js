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
//获取系统类型
function getSystem(){
	var userAgentInfo = navigator.userAgent;
	if (userAgentInfo.indexOf("iPad") > 0) {
		return "iPad";
    }
	return userAgentInfo;
}

//获取Ip
function huoquIp(){
	var datad = "";
	$.ajax({
        url:"getIpByTaobao.action",
        type:"post",
        async:false,//true异步，false同步，默认异步
        dataType:"text",
        success:function (data){
        	console.log("wancheng");
        	datad = data;
        },
        error:function (){
        	console.log("error");
            return "error";
        }
    })
	console.log("完成:"+datad);
	return datad;
}
//点击查看更多，查询历史消息分页方法查询
	function chakanGengduo(id){
		layer.msg('↑...');
		$("#"+id).html("<div class=\"shijian\">"
							+"<div class=\"shijiannei\">"
								+"<a>正在加载……</a>"
							+"</div>"
						+"</div>");
		
		xinxiModel=new Object();
		xinxiModel.type="lishixiaoxi";
		xinxiModel.kefuId=kefuid;
		//获取固定的sessionid，客户端唯一标示
		xinxiModel.gukeId = bendiSessionId;
		xinxiModel.yema = xiayiyeYema;
		
		var xinxiModelString = JSON.stringify(xinxiModel);
		console.log(xinxiModelString);
		webSocket.send(xinxiModelString);
	}

	//当前历史记录页码
	var xiayiyeYema = 1;
	//显示历史记录
	function xianshiLishiJilu(messages){
		layer.msg('ok');
		//隐藏上一页提示
		$("#"+xiayiyeYema).html("<div class=\"shijian\">"
											+"<div class=\"yemafengexian\">"
											+"</div>"
								+"</div>");
		console.log("循环输出到信息区域顶端");
		if(messages.zongtiaoshu>0){
			console.log(">0");
			//为了防止在内容顶部加入内容导致页面向下滑动，看不到原来的信息，
			//所以在加载完成历史消息后在将滚动条滑动到原来的高度
			//获取加载前内容高度
			var jiaZaiQianNeiRongGaodu = document.getElementById("xinxiqu").scrollHeight;
			
			for(i in messages.list){
				var xinxi = messages.list[i];
				console.log(xinxi.neirong);
				if(xinxi.fasongzhe==1){//客服发送显示在左边
					//增加内容;
					$("#xinxiqu").prepend("<div class=\"hang\">"
							+"<div class=\"neirong\">"
							+xinxi.neirong
							+"</div>"
							+"</div>");
					//增加时间
					$("#xinxiqu").prepend("<div class=\"hang\">"
							+"<div class=\"shijian\">"
								+"<div class=\"shijiannei\">"
									+xinxi.datetime
								+"</div>"
							+"</div>"
						+"</div>");
				}else{//顾客发送显示在右边
					//增加内容;
					$("#xinxiqu").prepend("<div class=\"hang\">"
													+"<div class=\"myneirong\">"
														+xinxi.neirong
													+"</div>"
												+"</div>");
					//增加时间
					$("#xinxiqu").prepend("<div class=\"hang\">"
							+"<div class=\"shijian\">"
								+"<div class=\"shijiannei\">"
									+xinxi.datetime
								+"</div>"
							+"</div>"
						+"</div>");
					
				}
			}
			//页码加1
			xiayiyeYema++;
			
			//判断是否是10条以下如果是则表示没有下一页了
			if(messages.zongtiaoshu<10){
				console.log("总条数小于10没有更多内容");
				$("#xinxiqu").prepend("<div class=\"hang\" id=\""+xiayiyeYema+"\">"
						+"<div class=\"shijian\">"
						+"<div class=\"shijiannei\">"
						+"<a>没有更多内容</a>"
						+"</div>"
						+"</div>"
						+"</div>");
			}else{
				console.log("点击查看更多");
				$("#xinxiqu").prepend("<div class=\"hang\" id=\""+xiayiyeYema+"\">"
						+"<div class=\"shijian\">"
						+"<div class=\"shijiannei\">"
						+"<a href='javascript:chakanGengduo("+xiayiyeYema+");'>点击查看更多</a>"
						+"</div>"
						+"</div>"
						+"</div>");
			}
			
			
			//滚动条加载完成内容后复位的动画时长
			var gundongs = 300;
			
			//绑定图像加载事件
			$("#xinxiqu img").load(function(){
				console.log("加载图片信息区！");
				setTimeout(function(){
				   	console.log('获取加载后内容高度');
					var jiaZaiHouNeiNongGaodu = document.getElementById("xinxiqu").scrollHeight;
					console.log(jiaZaiHouNeiNongGaodu);
					console.log('将滚动条滑动到原来的位置=加载后的高度-加载前高度，就是滚动条距离顶端的高度');
					$("#xinxiqu").stop();
					$("#xinxiqu").animate({scrollTop: (jiaZaiHouNeiNongGaodu-jiaZaiQianNeiRongGaodu)+'px'}, gundongs);
				},100)
			});
			
			
			//延时
			setTimeout(function(){
				console.log('获取加载后内容高度');
				var jiaZaiHouNeiNongGaodu = document.getElementById("xinxiqu").scrollHeight;
				console.log('将滚动条滑动到原来的位置=加载后的高度-加载前高度，就是滚动条距离顶端的高度');
				$("#xinxiqu").stop();
				$("#xinxiqu").animate({scrollTop: (jiaZaiHouNeiNongGaodu-jiaZaiQianNeiRongGaodu)+'px'}, gundongs);
			},100)
			
		}else{
			console.log("没有更多内容");
			$("#"+xiayiyeYema).html("<div class=\"shijian\">"
										+"<div class=\"shijiannei\">"
											+"<a>没有更多内容</a>"
										+"</div>"
									+"</div>");
		}
	}
	//记录发消息次数
	var faxiaoxicishu = 0;
	//发消息方法调用
	function faxiaoxi() {
		layer.msg('开始发消息...');
		
		console.log('发消息');
		//判断发布框是否为空
		var neirong = tijiao();
		if(neirong==""){
			layer.msg('请输入内容', {icon: 0});
			return;
		}
		console.log(11.101);
		if(neirong.length >(20*10000)){
			layer.msg('内容太长或者粘贴的截图太大', {icon: 0});
			return;
		}
		console.log(11.8);
		//播放消息声音
		sedMessageAudio.play();
		console.log(1109);
		//将声音从0开始播放否则重复发布时不会播放
		if(getSystem()!='iPad'){
			sedMessageAudio.currentTime = 0;
		}
		
		console.log('3215');
		xinxiModel=new Object();
		xinxiModel.type="faxiaoxi";
		xinxiModel.kefuId=kefuid;
		console.log('获取固定的sessionid，客户端唯一标示');
		xinxiModel.gukeId = bendiSessionId;
		xinxiModel.neirong = neirong;
		console.log(11.9);
		
		if (faxiaoxicishu==0) {
			//增加ip信息
			xinxiModel.ip=localStorage.getItem("ip");
		}
		
		var xinxiModelString = JSON.stringify(xinxiModel);
		console.log(xinxiModelString);
		
		
		if(xinxiModelString.length>6200){
			console.log('超长内容发送模式');
			chaochang(xinxiModelString);//超长消息发送
		}else{
			
			console.log('快速发送模式');
			webSocket.send(xinxiModelString);
		}
		
		//累加发消息次数
		faxiaoxicishu++;
		//清空编辑器
		console.log("清空编辑器");
		editor.html("");
	}
	//发消息回调函数
	function faxiaoxihuidiao(xinxi){
		console.log(xinxi.date);
		if(xinxi.status==0){
			addXinxiZhongjian("客服不在线已留言");
			layer.msg('客服不在线已留言', {icon: 6});
		}else{
			layer.msg('发送成功', {icon: 6});
		}
		
		//增加时间
		$("#xinxiqu").append("<div class=\"hang\">"
				+"<div class=\"shijian\">"
					+"<div class=\"shijiannei\">"
						+xinxi.date
					+"</div>"
				+"</div>"
			+"</div>");
		//增加内容;
		$("#xinxiqu").append("<div class=\"hang\">"
										+"<div class=\"myneirong\">"
											+xinxi.neirong
										+"</div>"
									+"</div>");
		//滑动动画
		var neironggaodu = document.getElementById("xinxiqu").scrollHeight;
		$("#xinxiqu").stop();
		$("#xinxiqu").animate({scrollTop: neironggaodu+'px'}, 1000);
		

	}

	//收消息
	function shouxiaoxi(date, neirong, gukeid) {
		audio.play();
		audio.currentTime = 0;
		console.log(date);
		console.log(neirong);
		console.log(gukeid);
		//增加时间
		$("#xinxiqu").append("<div class=\"hang\">"
				+"<div class=\"shijian\">"
					+"<div class=\"shijiannei\">"
						+date
					+"</div>"
				+"</div>"
			+"</div>");
		//增加内容;
		$("#xinxiqu").append("<div class=\"hang\">"
										+"<div class=\"neirong\">"
											+neirong
										+"</div>"
									+"</div>");
		//滑动动画
		var neironggaodu = document.getElementById("xinxiqu").scrollHeight;
		$("#xinxiqu").stop();
		$("#xinxiqu").animate({scrollTop: neironggaodu+'px'}, 1000);
		//收到消息弹窗提示
		layer.msg('有新消息', {icon: 6});
	}

	//信息中间区域增加信息时间区域
	function addXinxiZhongjian(date){
		//增加时间
		$("#xinxiqu").append("<div class=\"hang\">"
				+"<div class=\"shijian\">"
					+"<div class=\"shijiannei\">"
						+date
					+"</div>"
				+"</div>"
			+"</div>");
	}
	
	
	
	//websocket链接部分-------------------------------------------------------------------------------
	//信息传递对象
	var webSocket = null;
	var bendiSessionId = null;
	//链接完成
	function onOpen(event) {
		console.log("系统链接完成");
		//注册
		console.log("注册");
		addXinxiZhongjian('连接客服');
		//传递要连接的客服id
		//这个参数是客服的id即要咨询的客服的用户id，这个可以明码，只要客服端登陆就可以安全了
		xinxiModel=new Object();
		xinxiModel.type="zhuce";
		xinxiModel.kefuId=kefuid;
		console.log("注册前判断是否已经存储了cookieid如果没有存储则存储如果已经存储则替换当前id，以防止浏览器关闭后再次打开身份变化");
		//获取埋藏的sessionid
		bendiSessionId = localStorage.getItem("httpSessionid"+kefuid);
		console.log("bendiSessionId : "+bendiSessionId);
		if(bendiSessionId == null){
			//获取该顾客的ip和地区
			console.log("获取IP地址");
			var ip = huoquIp();
			
			console.log(ip);
			console.log("存储IP地址到本地方便发送信息 时传递");
			localStorage.setItem("ip",ip);
			
			xinxiModel.ip = ip;
			localStorage.setItem("httpSessionid"+kefuid,newHttpSessionId);
			bendiSessionId = localStorage.getItem("httpSessionid"+kefuid);
		}
		//存储固定的sessionid
		xinxiModel.gukeId = bendiSessionId;
		//传递顾客访问网站记录id
		xinxiModel.accessSessionId = accessSessionId;
		
		var xinxiModelString = JSON.stringify(xinxiModel);
		console.log(xinxiModelString);
		webSocket.send(xinxiModelString);
	}
	//异常
	function onError(event) {
		console.log("onerror");
		alert('网络断开或链接断开,点击确认后刷新页面,如果刷新未解决请选择或升级浏览器至最新版本，如果是360浏览器请选择极速模式。');
		
//		setTimeout(function(){
		//	location.reload(true);
//		},5000)
		
		addXinxiZhongjian('连接失败或者网络断开,请刷新页面，如果刷新未解决请选择或升级浏览器至最新版本，如果是360浏览器请选择极速模式。');
	}
	//收到消息
	function onMessage(event) {
//		layer.msg('启动系统完成', {icon: 1});
		console.log("收到消息"+event.data);
		var message = JSON.parse(event.data);
		//注册
		if(message.type=='zhuce'){
			if(message.jieguo =='ok'){
				console.log("注册完成");
				addXinxiZhongjian('已连接到客服');
				layer.msg('链接完成');
			}else{
				console.log("注册失败");
				addXinxiZhongjian('连接失败');
			}
		}
		//发消息回调
		if(message.type=='faxiaoxihuidiao'){
			faxiaoxihuidiao(message);
		}
		//消息
		if(message.type=='faxiaoxi'){
			shouxiaoxi(message.date, message.neirong, message.gukeid);
		}
		//历史记录
		if(message.type=='lishijilu'){
			console.log(message);
//			console.log("放入缓存判断是否有图片如果有则等待图片加载完成后在放入列表");
			//如果没有图片则直接放入列表
//			$("#jiluHuancun").html(event.data);
//			console.log("判断是否有图片");
//			if($("#jiluHuancun img").length > 0){
//				console.log("有图片");
//				console.log("延时1秒后显示");
//				setTimeout(function(){
//					console.log("显示到页面");
//					xianshiLishiJilu(message);
//				},3000);
//			}else{
//				console.log("无图片");
				console.log("显示到页面");
				xianshiLishiJilu(message);
//			}
		}
		
	}
	//链接关闭
	function onClose(event){
		console.log("onclose");
		console.log(event);
		alert('网络断开或链接断开,点击确认后刷新页面,如果刷新未解决请选择或升级浏览器至最新版本，如果是360浏览器请选择极速模式。');
		
		//setTimeout(function(){
		//	location.reload(true);
		//},5000)
		
		addXinxiZhongjian('连接失败或者网络断开,请刷新页面，如果刷新未解决请选择或升级浏览器至最新版本，如果是360浏览器请选择极速模式。');
	}
	
	
	//获取铃声播放元素
	var sedMessageAudio = document.getElementById("sedMessageAudio");
	var audio = document.getElementById("tishiyin");

	//编辑器
	var editor;	
	$(document).ready(function(){
		
		console.log('判断浏览器是否支持websocket');
		if(window.WebSocket){
			console.log("当前浏览器支持websocket");
		}else{
			console.log("当前浏览器\"不\"支持websocket");
		}
		
		console.log("链接域名"+wspath);
		var wsurl = 'ws:'+wspath+'websocketXinxi';
		console.log('链接url：'+wsurl);
		console.log('websocket链接');
		webSocket = new WebSocket(wsurl);
		
//		console.log('sockjs链接');
//		var transports = [];
//		webSocket = new SockJS(wsurl, undefined, {protocols_whitelist: transports});
		
		console.log('链接完成');
		//webSocket = new WebSocket('ws:'+wspath+'websocketXinxi');
//		webSocket = new WebSocket('ws://192.168.1.102:8080/shishitongxun2014/websocketXinxi');
//		webSocket = new WebSocket('ws://121.43.108.229:80/websocketXinxi');

		webSocket.onerror = function(event) {
			onError(event);
		};
		
		webSocket.onopen = function(event) {
			onOpen(event);
		};
		
		webSocket.onmessage = function(event) {
			onMessage(event);
		};
		
		webSocket.onclose = function (event) {
			   console.log('链接关闭');
			   onClose(event);
		};
		
		
		console.log("判断手机电脑"+IsPC());
		
		
		
		//加载编辑器
		var gongjulan;
		if(IsPC()){
			
		gongjulan = [
					        'emoticons','source', '|', 'undo', 'redo', '|', 'clearhtml', 'selectall', '|',
					        'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
					        'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', 
					        '|','baidumap'
					];
		}else{
			gongjulan = [
			             'emoticons', '|', 'undo', 'redo', '|', 'clearhtml', 'selectall', '|','fontsize', '|', 'forecolor', 'hilitecolor'
			             ];
		}
		
		KindEditor.ready(function(K) {
				editor = K.create('#xiaoxishurukuang', {
				cssPath : 'public/kindeditor/plugins/code/prettify.css',
				themeType : 'example1',
				uploadJson : basepath+'public/kindeditor/jsp/upload_json.jsp',
				fileManagerJson : basepath+'public/kindeditor/jsp/file_manager_json.jsp',//指定浏览远程图片的服务器端程序。
				allowFileManager : true,//true时显示浏览远程服务器按钮。
				allowImageUpload : true,//true时显示图片上传按钮
				//height:"10",//高度
				minHeight:10,
				afterCreate : function() {//设置编辑器创建后执行的回调函数。
					var self = this;
					K.ctrl(document, 13, function() {//按键ctrl加回车提交
						self.sync();
						tijiao();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						tijiao();
					});
				},

				//工具栏
				items:gongjulan,
				afterChange : function(){
					//时时调整编辑框内图像宽度关闭
					onchange();
				},
			});
		});
		
			
		//上传图片插件
		KindEditor.ready(function(K) {
			var uploadbutton = K.uploadbutton({
				button : K('#uploadButton')[0],
				fieldName : 'imgFile',
				url : basepath+'public/kindeditor/jsp/upload_json.jsp',
				afterUpload : function(data) {
					if (data.error === 0) {
						var imgsrc = "<img src='"+data.url+"'/>";
						editor.insertHtml(imgsrc);
						layer.msg('发送完成');
					} else {
						layer.msg('发送失败'+data.message);
					}
				},
				afterError : function(str) {
					console.log('上传失败: ' + str);
				}
			});
			//选择后马上上传
			uploadbutton.fileBox.change(function(e) {
				console.log(e);
				layer.msg('正在上传...',{time:1000*100});
				uploadbutton.submit();
			});
		});
		
		
		

	});
	
	//编辑器提交方法
	function tijiao(){
		console.log(1);
		$("#yulan").html(editor.html());
		console.log(2);
		$("#yulan img").attr("onclick","fangda(this);");
		console.log(3);
		console.log($("#yulan").html());

		//判断是否有图片
		if($("#yulan img").length ==0){
//			如果没有图片则给链接加超链接
			console.log("判断没有图片");
			$("#yulan").html(replaceReg($("#yulan").html()));
		}
		
		return $("#yulan").html();
	}
	//编辑器修改方法,如果粘贴了图片或者上传了图片则马上发送
	function onchange(){
		console.log("修改");
		if(editor == null){
			return;
		}
		
		var html = editor.html();
		$("#yulan").html(html);
		
		//判断是否有图片
		if($("#yulan img").length ==0){
			return;
		}
		//发送消息
		faxiaoxi();
	}
	//放大图片
	function fangda(img){
		console.log(img);
		console.log(img.src);
		if(IsPC()==false){//如果是手机端
			if(img.src.substring(0,4)=="http"){
				window.location.href=img.src;
				return;
			}
		}
		
		$("#fangdaqiSrc").val(img.src);
		$("#fangdaqiForm").submit();
	}
	//超长消息发送方法
	function chaochang(jsonobject){
		$.ajax({
	        url:"chaochangMessageSend.action",
	        type:"post",
	        data:{
	            "jsonobject":jsonobject
	        },
	        success:function (data){
	        	console.log(data);
	        },
	        error:function (){
	        	console.log('发送失败');
	        	
	        }
	    })
	}
	
	//点击上传图片按钮
	function shangchuantupian(){
		console.log("上传点击");
		//1表示使用kindeditor编辑器上传图片工具上传到服务器
		//2使用七牛上传到云存储cdn加速
		var falg = 2;
		if(falg==1){
			console.log("kindeditor上传图片");
			console.log("设置为只选择图片");
			$("[name='imgFile']").attr("accept","image/*");
			$("[name='imgFile']")[0].click();
		}else if(falg == 2){
			console.log("七牛云存储上传图片cdn加速");
			qiniuImgUpload();
		}
	}
	
	
	//七牛上传图片方法
	function qiniuImgUpload(){
		$("#qiniuFileImageUpload").click();
	}
	//七牛上传图片按钮被选择文件方法
	function qiniuOnchange(){
		layer.msg('正在上传CDN加速中...',{time:1000*100});
		var qiniuGonggongYuming = "http://7xwsne.com1.z0.glb.clouddn.com";
		console.log('获取token');
		$.ajax({
			url:"getQiniuToken.action",
			type:"post",
			dataType:"text",
			async:false,//true异步，false同步，默认异步
			success:function (data){
				console.log(data);
				$("#qiniuToken").val(data);
			},
			error:function(e){
				layer.alert('获取凭证失败。');
			}
		})
		
		console.log('设置文件名');
		bendiSessionId = localStorage.getItem("httpSessionid"+kefuid);
		console.log('增加顾客id以防止不同用户的文件名重复导致上传被覆盖');
		imgName = "upload/img/"+getDateTime()+"/"+bendiSessionId+"/"+getFileName("qiniuFileImageUpload");
		console.log(imgName);
		$("#qiniukey").val(imgName);
		console.log('提交图片');
		var options={
				url:"http://upload.qiniu.com/",
				type:"POST",
				dataType:"json",
				success:function(msg){
					console.log("success");
					console.log(msg);
					addKindeditorImg(qiniuGonggongYuming+"/"+msg.key);
				},
				error:function(e){
					console.log("error");
					console.log(e.responseText);
					var ems = e.responseText;
					console.log(ems);
					var emsg = JSON.parse(ems);
					console.log(emsg.error);
					if(emsg.error=='token not specified'){
						layer.alert('上传失败，token凭证错误。');
					}else if(emsg.error=='file exists'){
						layer.alert('已上传直接读取');
						addKindeditorImg(qiniuGonggongYuming+"/"+imgName);
					}else{
						layer.alert('上传失败，'+emsg.error+"。");
					}
					console.log("失败");
				}
		}
		$('#qiniuImgUploadForm').ajaxSubmit(options);
	}
	//根据file元素id获取文件名
	function getFileName(id){
		var o = $("#"+id).val();
	    var pos=o.lastIndexOf("\\");
	    return o.substring(pos+1); 
//		var file = $("#"+id).val();
//		var strFileName=file.replace(/^.+?\([^\]+?)(.[^.\]*?)?$/gi,"$1");  //正则表达式获取文件名，不带后缀
//		var FileExt=file.replace(/.+./,"");   //正则表达式获取后缀
//		return strFileName+FileExt;
	}
	//产生随机数
	function getDateTime(){
		var oDate = new Date(); //实例一个时间对象；
		var y = oDate.getFullYear();   //获取系统的年；
		var m = oDate.getMonth()+1;   //获取系统月份，由于月份是从0开始计算，所以要加1
		var d = oDate.getDate(); // 获取系统日，
		var h = oDate.getHours(); //获取系统时，
		var n = oDate.getMinutes(); //分
		var s = oDate.getSeconds(); //秒
//		return y+"_"+m+"_"+d+"_"+h+"_"+n+"_"+s;
		return y+"-"+m+"-"+d+" "+h+":"+n+":"+s;
	}
	//根据fileID获取文件后缀
	function getFileExt(id){
		var file = $("#"+id).val();
		var FileExt=file.replace("/.+./","");   //正则表达式获取后缀
		return FileExt;
	}
	//根据图片src将图片添加到编辑器
	function addKindeditorImg(src){
		console.log("添加图片："+src);
		var imgsrc = "<img src='"+src+"'/>";
		editor.insertHtml(imgsrc);
	}
	
	//将文本中的url链接加超链接
	function replaceReg(str) {
		//因为平板不支持链接转换所以关闭
		console.log(getSystem());
		if(getSystem()=='iPad'){
			return str;
		}
		
		console.log(1);
		var reg = /(http:\/\/|https:\/\/)((\w|=|\?|\.|\/|&|-)+)/g;
		console.log(2);
		return str.replace(reg, function(m) {
			return '<a href="'+m+'">' + m + '</a>';
		})
		console.log(3);
	}
	
	//链接断开提示
	function lianjeiDuankaiTishi(){
		alert('');
	}