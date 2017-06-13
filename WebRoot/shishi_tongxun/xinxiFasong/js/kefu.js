//判断是否是pc
function IsPC() {
    var userAgentInfo = navigator.userAgent;
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


//展开顾客详情
var zhankaiXiangqingFlag = 0;
function zhankaiGukeXiangqing(){
	if(zhankaiXiangqingFlag==0){
		$(".zuocegukeliebiao").animate({width:'50%'});
		$(".liaotianqu").animate({width:'50%'});
		zhankaiXiangqingFlag=1;
	}else{
		$(".zuocegukeliebiao").animate({width:'16%'});
		$(".liaotianqu").animate({width:'84%'});
		zhankaiXiangqingFlag=0
	}
}
//左侧头像点击效果，点击后被点击按钮颜色与右侧信息区背景颜色相同
function touxiangDianjiXiaoguo(gukeId){
	$(".guke").css("background-color","#d3ddf7");
	$("#touxiang"+gukeId).css("background-color","rgb(238,238,238)");
}

//顾客列表查看更多-----------------------------------------------------------------------------------
function gukeLiebiaoChakanGengduo(yema){
	$(".zuocegukeliebiao .xiayiyeanniukuang").html('<div class=\'chakangengduo\'>正在加载</div>');

	xinxiModel=new Object();
	xinxiModel.type="gukeliebiao";
	//获取固定的sessionid，客户端唯一标示
	xinxiModel.yema = gukeJiluYema;
	
	var xinxiModelString = JSON.stringify(xinxiModel);
	console.log(xinxiModelString);
	webSocket.send(xinxiModelString);
}



//区分编辑器工具栏pc端和移动端
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



//存储编辑器列表
var bianjiqiMap = new Map();
//显示顾客列表
//当前顾客记录页码
var gukeJiluYema = 1;
function addGukeLiebiao(messages){
		//删除正在加载
		$(".zuocegukeliebiao .xiayiyeanniukuang").remove();
		
		console.log("循环输出到信息区域底部");
		if(messages.zongtiaoshu>0){
			console.log(">0");
			
			for(var i = 0 ; i < messages.list.length ; i++){
				var guke = messages.list[i];
				console.log(guke.id);
				
				//判断是否需要添加提示新信息图标
				var tishitubiao = "";
				if(guke.newXinxi==0){
					tishitubiao = "<div style=\"display: none;\" class=\"tishitubiao\"></div>";
				}else{
					tishitubiao = "<div class=\"tishitubiao\"></div>";
				}
				//编辑地区
				var diqu = "";
				diqu = " ip:"+guke.ip;
				diqu+=guke.country+guke.region+guke.city+guke.isp;
				//增加左侧顾客头像
				$(".zuocegukeliebiao").append("<div class=\"guke\" id=\"touxiang"+guke.httpSessionId+"\" onclick=\"zhankai_liaotian_qu('"+guke.httpSessionId+"');\">"
											+tishitubiao
											+"ID:"+guke.id
											+diqu
											+"</div>");
				//增加顾客头像 的id
				
				//记录增加该顾客id的
				gukeids+=""+guke.httpSessionId+",";
				//增加输入框
				$("#liaotianqu").append("<div class=\"gukemokuai\" id=\"gukemokuai"+guke.httpSessionId+"\" style=\"display: none;\">"
												+"<div class=\"xinxiqu\" id=\"xinxiqu"+guke.httpSessionId+"\">"
													+"<div class=\"hang\">"
													+"</div>"
												+"</div>"
												+"<div class=\"fabuqu\">"
													//+"<input type=\"text\" id=\"xiaoxishurukuang"+guke.httpSessionId+"\">"
													+"<div class=\"bianjiqikuang\"><textarea style=\"width: 100%;height: 80%;\" id=\"xiaoxishurukuang"+guke.httpSessionId+"\"></textarea></div>"
													+"<input type=\"button\" class=\"shangchuantupian\" onclick=\"shangchuantupian('xiaoxishurukuang"+guke.httpSessionId+"');\" value=\"上传图片\"/>"
													+"<input type=\"button\" class=\"fabuanniu\" onclick=\"faxiaoxi('xiaoxishurukuang"+guke.httpSessionId+"');\" value=\"发送\">"
												+"</div>"
											+"</div>"
										+"</div>");
				
				console.log("初始加载编辑器：");
				
				
				var K = KindEditor;
				var editor = K.create('#xiaoxishurukuang'+guke.httpSessionId, {
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
									tijiao('xiaoxishurukuang'+guke.httpSessionId);
								});
								K.ctrl(self.edit.doc, 13, function() {
									self.sync();
									tijiao('xiaoxishurukuang'+guke.httpSessionId);
								});
							},
		
							//工具栏
							items:gongjulan,
							afterChange : function(){
//								时时调整编辑框内图像宽度关闭
								onchange(this.srcElement[0].id,this);
							},
						});
						bianjiqiMap.put('xiaoxishurukuang'+guke.httpSessionId, editor);
				
				console.log("初始编辑器完成");
				
				}
			//页码加1
			gukeJiluYema++;
			
			//判断是否是10条以下如果是则表示没有下一页了
			if(messages.zongtiaoshu<10){
				console.log("总条数小于10没有更多内容");
				$(".zuocegukeliebiao").append("<div class='xiayiyeanniukuang'>" +
						'<div class=\'chakangengduo\'>没有更多</div>'+
				  "</div>");
			}else{
				console.log("点击查看更多");
				
				$(".zuocegukeliebiao").append("<div class='xiayiyeanniukuang'>" +
													"<a class='chakangengduo' href='javascript:gukeLiebiaoChakanGengduo("+gukeJiluYema+");'>查看更多</a>" +
											  "</div>");
			}
	}else{
		$(".zuocegukeliebiao").append("<div class='xiayiyeanniukuang'>" +
				'<div class=\'chakangengduo\'>没有顾客喽！</div>'+
		  "</div>");
	}
}

//编辑器提交方法
function tijiao(editorid){
	console.log("提交");
	console.log(editorid);
	console.log(editorid);
	var editor = bianjiqiMap.get(editorid);
	console.log(1);
	$("#yulan").html(editor.html());
	console.log(2);
	$("#yulan img").attr("onclick","fangda(this);");
	console.log(3);
	console.log($("#yulan").html());
	//判断是否有图片
	if($("#yulan img").length ==0){
//		如果没有图片则给链接加超链接
		console.log("判断没有图片");
		$("#yulan").html(replaceReg($("#yulan").html()));
	}
	return $("#yulan").html();
}
//编辑器修改方法,如果粘贴了图片或者上传了图片则马上发送
function onchange(editorid,editor){
	console.log("修改");
	if(editor == null){
		return;
	}
	console.log(editorid);
	console.log(editor.html());
	
	var html = editor.html();
	$("#yulan").html(html);
	
	//判断是否有图片
	if($("#yulan img").length ==0){
		return;
	}
	//发送消息
	faxiaoxi(editorid);
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
//---------------------------------------------------------------------------------------------------------
//发消息方法调用
	function faxiaoxi(gukeid) {
		console.log('发消息');
		layer.msg('开始发送', {icon: 1});
		var neirong = tijiao(gukeid);
		console.log(1358);
		console.log(neirong);
		if(neirong==""){
			layer.msg('请输入内容', {icon: 0});
			return;
		}else{
			
			if(neirong.length >(20*10000)){
				layer.msg('内容太长或者粘贴的截图太大', {icon: 0});
				return;
			}
			//清空编辑器
			console.log("清空编辑器");
			bianjiqiMap.get(gukeid).html("");
			
			//播放消息声音
			sedMessageAudio.play();
			//将声音从0开始播放否则重复发布时不会播放
			sedMessageAudio.currentTime = 0;
			//wxTclBlessService.kefufaxiaoxi(gukeid,neirong,);
			
			xinxiModel=new Object();
			xinxiModel.type="faxiaoxi";
			//去掉id前缀
			gukeid = gukeid.substring(16);
			xinxiModel.gukeId = gukeid;
			xinxiModel.neirong = neirong;
			xinxiModel.fasongzhe = 1;
			
			var xinxiModelString = JSON.stringify(xinxiModel);
			console.log(xinxiModelString);
			
			if(xinxiModelString.length>6200){
				console.log('超长内容发送模式');
				chaochang(xinxiModelString);//超长消息发送
			}else{
				console.log('快速发送模式');
				webSocket.send(xinxiModelString);
			}

		}
	}
	//发消息回调函数
	function faxiaoxihuidiao(xinxi){
		
		if(xinxi.status==0){
			
			$("#xinxiqu"+xinxi.gukeId).append("<div class=\"hang\">"
					+"<div class=\"shijian\">"
						+"<div class=\"shijiannei\">"
							+'对方不在线已留言'
						+"</div>"
					+"</div>"
				+"</div>");
			
			layer.msg('对方不在线已留言', {icon: 6});
		}else{
			layer.msg('发送成功', {icon: 6});
		}
		
		
		//增加时间
		$("#xinxiqu"+xinxi.gukeId).append("<div class=\"hang\">"
				+"<div class=\"shijian\">"
					+"<div class=\"shijiannei\">"
						+xinxi.date
					+"</div>"
				+"</div>"
			+"</div>");
		//增加内容;
		$("#xinxiqu"+xinxi.gukeId).append("<div class=\"hang\">"
										+"<div class=\"myneirong\">"
											+xinxi.neirong
										+"</div>"
									+"</div>");
		//滑动动画
		var neironggaodu = document.getElementById("xinxiqu"+xinxi.gukeId).scrollHeight;
		$("#xinxiqu"+xinxi.gukeId).stop();
		$("#xinxiqu"+xinxi.gukeId).animate({scrollTop: neironggaodu+'px'}, 1000);
		//清空输入框
		document.getElementById("xiaoxishurukuang"+xinxi.gukeId).value="";
	}
	
	//存储当前客服页面中的顾客列表id
	var gukeids = "";
	//收消息
	function shouxiaoxi(date, neirong, gukeid,ip) {
		//播放消息声音
		audio.play();
		//将声音从0开始播放否则重复发布时不会播放
		audio.currentTime = 0;
		console.log(date);
		console.log(neirong);
		console.log(gukeid);
		//查找该页面是否已经有该顾客的聊天记录
		if(gukeids.indexOf(""+gukeid+",") > -1){
			console.log('已经存在该聊天窗口');
			console.log("未读提示id"+"#touxiang"+gukeid+" .tishitubiao");
			//增加未读信息提示
			$("#touxiang"+gukeid+" .tishitubiao").show();
			//增加时间
			$("#xinxiqu"+gukeid).append("<div class=\"hang\">"
					+"<div class=\"shijian\">"
						+"<div class=\"shijiannei\">"
							+date
						+"</div>"
					+"</div>"
				+"</div>");
			//增加内容;
			$("#xinxiqu"+gukeid).append("<div class=\"hang\">"
											+"<div class=\"neirong\">"
												+neirong
											+"</div>"
										+"</div>");
			//滑动动画
			var neironggaodu = document.getElementById("xinxiqu"+gukeid).scrollHeight;
			$("#xinxiqu"+gukeid).stop();
			$("#xinxiqu"+gukeid).animate({scrollTop: neironggaodu+'px'}, 1000);
		}else{
			//编辑地区
			console.log("解析出的ip地址为："+ip);
			ip = JSON.parse(ip);
			var diqu = "";
			ip = ip.data;
			console.log(ip);
			diqu = " ip:"+ip.ip;
			diqu+=ip.country+ip.region+ip.city+ip.isp;
			//增加左侧顾客头像
			$(".zuocegukeliebiao").append("<div class=\"guke\" id=\"touxiang"+gukeid+"\" onclick=\"zhankai_liaotian_qu('"+gukeid+"');\">"
										+"<div class=\"tishitubiao\"></div>"
										+diqu
										+"</div>");
			//增加顾客头像 的id
			
			//记录增加该顾客id的
			gukeids+=""+gukeid+",";
			//增加输入框
			$("#liaotianqu").append("<div class=\"gukemokuai\" id=\"gukemokuai"+gukeid+"\" style=\"display: none;\">"
											+"<div class=\"xinxiqu\" id=\"xinxiqu"+gukeid+"\">"
												+"<div class=\"hang\">"
													+"<div class=\"neirong\">"
														+neirong
													+"</div>"
												+"</div>"
											+"</div>"
											+"<div class=\"fabuqu\">"
												+"<div class=\"bianjiqikuang\"><textarea style=\"width: 100%;height: 80%;\" id=\"xiaoxishurukuang"+gukeid+"\"></textarea></div>"
												+"<input type=\"button\" class=\"shangchuantupian\" onclick=\"shangchuantupian('xiaoxishurukuang"+gukeid+"');\" value=\"上传图片\"/>"
												+"<input type=\"button\" class=\"fabuanniu\" onclick=\"faxiaoxi('xiaoxishurukuang"+gukeid+"');\" value=\"发送\">"
											+"</div>"
										+"</div>"
									+"</div>");
			
			console.log("初始加载编辑器：");
			
			var K = KindEditor;
			var editor = K.create('#xiaoxishurukuang'+gukeid, {
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
								tijiao('xiaoxishurukuang'+gukeid);
							});
							K.ctrl(self.edit.doc, 13, function() {
								self.sync();
								tijiao('xiaoxishurukuang'+gukeid);
							});
						},
	
						//工具栏
						items:gongjulan,
						afterChange : function(){
//							时时调整编辑框内图像宽度关闭
							onchange(this.srcElement[0].id,this);
						},
					});
					bianjiqiMap.put('xiaoxishurukuang'+gukeid, editor);
			
			console.log("初始编辑器完成");
			
			
		}
		//收到消息弹窗提示
		layer.msg('有新消息', {icon: 6});
	}

	//展开聊天区域
	function zhankai_liaotian_qu(gukeid){
		//修改点击头像样式
		touxiangDianjiXiaoguo(gukeid);
		
		console.log("gukemokuai"+gukeid);
		//去掉红色未读提示
		
		$("#touxiang"+gukeid+" .tishitubiao").hide();
		//展开聊天区
		$(".gukemokuai").hide();
		$("#gukemokuai"+gukeid).show();
		
		//展开聊天区域的同时获取和该顾客的聊天记录
		if(yemaMap.get(gukeid) == null){
			huoquLiaotianJilu("",gukeid);
		}
		
	}
	
	//聊天记录翻页部分-----------------------------------------------------------------------------
	//存储格式key是gukeid，value是页码
	var yemaMap = new Map();
	//获取聊天记录请求发送
	function huoquLiaotianJilu(zhuangtaiId,gukeid){
		layer.msg('正在加载...', {icon: 1});
		console.log("获取他的记录"+gukeid);
		$("#"+zhuangtaiId).html("<div class=\"shijian\">"
									+"<div class=\"shijiannei\">"
										+"<a>正在加载……</a>"
									+"</div>"
								+"</div>");
		
		var yema = yemaMap.get(gukeid);
		console.log(yema);
		if(yema == null){//获取不到页码说明是第一次点击该窗口，默认页码为1
			yemaMap.put(gukeid,1);
			yema = 1;
		}
		//聊天记录请求发送需要带参数：顾客id，页码
		xinxiModel=new Object();
		xinxiModel.type="lishixiaoxi";
		//获取固定的sessionid，客户端唯一标示
		xinxiModel.gukeId = gukeid;
		xinxiModel.yema = yema;
		
		var xinxiModelString = JSON.stringify(xinxiModel);
		console.log(xinxiModelString);
		webSocket.send(xinxiModelString);
	}
	//显示历史消息
	function addLishijilu(messages){
		//加载状态提示id
		var zhuangtaiId = "yema"+yemaMap.get(messages.gukeId)+messages.gukeId;
		console.log("拼接状态id"+zhuangtaiId);
		//隐藏上一页提示
		$("#"+zhuangtaiId).html("<div class=\"shijian\">"
											+"<div class=\"yemafengexian\">"
											+"</div>"
								+"</div>");
		console.log("循环输出到信息区域顶端");
		if(messages.zongtiaoshu>0){
			console.log(">0");
			//为了防止在内容顶部加入内容导致页面向下滑动，看不到原来的信息，
			//所以在加载完成历史消息后在将滚动条滑动到原来的高度
			//获取加载前内容高度
			var jiaZaiQianNeiRongGaodu = document.getElementById("xinxiqu"+messages.gukeId).scrollHeight;
			
			for(var i = 0 ; i < messages.list.length ; i++){
				var xinxi = messages.list[i];
				console.log(xinxi.neirong);
				if(xinxi.fasongzhe==2){//顾客发送显示在左边
					//增加内容;
					$("#xinxiqu"+messages.gukeId).prepend("<div class=\"hang\">"
															+"<div class=\"neirong\">"
																+xinxi.neirong
															+"</div>"
														+"</div>");
					//增加时间
					$("#xinxiqu"+messages.gukeId).prepend("<div class=\"hang\">"
																+"<div class=\"shijian\">"
																	+"<div class=\"shijiannei\">"
																		+xinxi.datetime
																	+"</div>"
																+"</div>"
															+"</div>");
				}else{//客服发送显示在右边
					//增加内容;
					$("#xinxiqu"+messages.gukeId).prepend("<div class=\"hang\">"
																+"<div class=\"myneirong\">"
																	+xinxi.neirong
																+"</div>"
															+"</div>");
					//增加时间
					$("#xinxiqu"+messages.gukeId).prepend("<div class=\"hang\">"
																+"<div class=\"shijian\">"
																	+"<div class=\"shijiannei\">"
																		+xinxi.datetime
																	+"</div>"
																+"</div>"
															+"</div>");
					
				}
			}
			//页码加1
			yemaMap.put(messages.gukeId,yemaMap.get(messages.gukeId)+1);
			console.log("增加页码后的页码"+yemaMap.get(messages.gukeId));
			//判断是否是10条以下如果是则表示没有下一页了
			if(messages.zongtiaoshu<10){
				console.log("总条数小于10没有更多内容");
				$("#xinxiqu"+messages.gukeId).prepend("<div class=\"hang\" id=\""+zhuangtaiId+"\">"
						+"<div class=\"shijian\">"
						+"<div class=\"shijiannei\">"
						+"<a>没有更多内容</a>"
						+"</div>"
						+"</div>"
						+"</div>");
			}else{
				console.log("点击查看更多");
				$("#xinxiqu"+messages.gukeId).prepend("<div class=\"hang\" id=\""+"yema"+yemaMap.get(messages.gukeId)+messages.gukeId+"\">"
						+"<div class=\"shijian\">"
						+"<div class=\"shijiannei\">"
						+"<a href='javascript:huoquLiaotianJilu(\""+"yema"+yemaMap.get(messages.gukeId)+messages.gukeId+"\",\""+messages.gukeId+"\");'>点击查看更多</a>"
						+"</div>"
						+"</div>"
						+"</div>");
			}
			
			
			//获取加载后内容高度
			var jiaZaiHouNeiNongGaodu = document.getElementById("xinxiqu"+messages.gukeId).scrollHeight;
			//将滚动条滑动到原来的位置=加载后的高度-加载前高度，就是滚动条距离顶端的高度
			$("#xinxiqu"+messages.gukeId).stop();
			$("#xinxiqu"+messages.gukeId).animate({scrollTop: (jiaZaiHouNeiNongGaodu-jiaZaiQianNeiRongGaodu)+'px'}, 0);
		}else{
			console.log("没有更多内容");
			
			//判断如果是第一页没有任何记录则直接添加一个“没有内容”
			if(yemaMap.get(messages.gukeId)==1){
				$("#xinxiqu"+messages.gukeId).prepend("<div class=\"hang\">"
						+"<div class=\"shijian\">"
						+"<div class=\"shijiannei\">"
						+"<a>没有内容</a>"
						+"</div>"
						+"</div>"
						+"</div>");
			}else{
				$("#"+zhuangtaiId).html("<div class=\"shijian\">"
						+"<div class=\"shijiannei\">"
							+"<a>没有更多内容</a>"
						+"</div>"
					+"</div>");
			}
		}
		layer.msg('加载完成', {icon: 1});
	}
	
	
	//websocket链接部分----------------------------------------------------------------------------
	//信息传递对象
	
	var webSocket = null;
	//链接完成
	function onOpen(event) {
		console.log("系统链接完成");
		layer.msg('已连接服务器', {icon: 6});
	}
	//异常
	function onError(event) {
		alert('网络断开或链接断开,点击确认后刷新页面,如果刷新未解决请选择或升级浏览器至最新版本，如果是360浏览器请选择极速模式。');
		location.reload(true);
	}
	//收到消息
	function onMessage(event) {
		console.log("收到消息"+event.data);
		var message = JSON.parse(event.data);
		//注册
		if(message.type=='zhuce'){
			if(message.jieguo =='ok'){
				console.log("注册完成");
				console.log('已连接');
				layer.msg('连接完成', {icon: 6});
			}
		}
		//发消息回调
		if(message.type=='faxiaoxihuidiao'){
			faxiaoxihuidiao(message);
		}
		//消息
		if(message.type=='faxiaoxi'){
			console.log(message.ip);
			shouxiaoxi(message.date, message.neirong, message.gukeId,message.ip);
		}
		//未登录
		if(message.type=='noLogin'){
			alert('请先登录');
			location.href='SysUser/login.jsp';
		}
		//收到顾客列表
		if(message.type=='kehuliebiao'){
			addGukeLiebiao(message);
		}
		//收到聊天记录
		if(message.type=='lishijilu'){
			addLishijilu(message);
		}
		
	}
	
	//链接关闭
	function onClose(event){
		console.log("onclose");
		alert('网络断开或链接断开,点击确认后刷新页面,如果刷新未解决请选择或升级浏览器至最新版本，如果是360浏览器请选择极速模式。');
		location.reload(true);
	}
	
	
	var sedMessageAudio = document.getElementById("sedMessageAudio");
	var audio = document.getElementById("tishiyin");
	$(document).ready(function(){
		layer.msg('开始连接', {icon: 1});
		//注册的id应该在后台从登陆的人的session中获取目前临时使用
		webSocket = new WebSocket('ws:'+wspath+'websocketXinxiKefu');

		webSocket.onerror = function(event) {
			onError(event)
		};
		
		webSocket.onopen = function(event) {
			onOpen(event)
		
		};
		
		webSocket.onmessage = function(event) {
			onMessage(event)
		};
		webSocket.onclose = function (event) {
			   console.log('链接关闭');
			   onClose(event);
		};
		
		
		//上传图片插件
		KindEditor.ready(function(K) {
			var uploadbutton = K.uploadbutton({
				button : K('#uploadButton')[0],
				fieldName : 'imgFile',
				url : basepath+'public/kindeditor/jsp/upload_json.jsp',
				afterUpload : function(data) {
					if (data.error === 0) {
						var imgsrc = "<img src='"+data.url+"'/>";
						var editor = bianjiqiMap.get(shangchuanTupianEditorId);
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

	
	//当前编辑器
	var shangchuanTupianEditorId = "";
	//点击上传图片按钮
	function shangchuantupian(editorid){
		shangchuanTupianEditorId = editorid;
		
		//1表示使用kindeditor编辑器上传图片工具上传到服务器
		//2使用七牛上传到云存储cdn加速
		var falg = 2;
		if(falg==1){
			console.log("上传点击");
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
		
		console.log('增加顾客id以防止不同用户的文件名重复导致上传被覆盖');
		imgName = "upload/img/"+getDateTime()+"/"+kefuid+"/"+getFileName("qiniuFileImageUpload");
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
		var editor = bianjiqiMap.get(shangchuanTupianEditorId);
		editor.insertHtml(imgsrc);
	}
	
	
	//将文本中的url链接加超链接
	function replaceReg(str) {
		var reg = /(http:\/\/|https:\/\/)((\w|=|\?|\.|\/|&|-)+)/g;
		return str.replace(reg, function(m) {
			return '<a href="'+m+'">' + m + '</a>';
		})
	}