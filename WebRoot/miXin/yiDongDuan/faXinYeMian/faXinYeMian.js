function wancheng(){
	console.log("开始");
	var options={
			url:"addMiXin.action",
			type:"POST",
			dataType:"json",
			success:function(msg){
				console.log(msg);
				if(msg==1){
					alert("不能为空");
				}
				if(msg==2){
					alert("内容太长，不可超过1000字哦！！");
				}
				if(msg.uuid!=null){
					location.href='miXin/yiDongDuan/shengcheng/shengcheng.jsp?uuid='+msg.uuid;
				}
			},
			error:function(){
				alert("error");
			}
	}
	$('#form1').ajaxSubmit(options);
}