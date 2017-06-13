function shanchu(id){
	console.log(id);
	layer.open({
	    content: '确定删除'+"吗？"
	    ,btn: ['确认', '取消']
	    ,yes: function(index, layero){
	    	layer.msg('开始删除');
	    	$.ajax({
				url:"deleteXinxijilu.action",
				type:"post",
				data:{
					"id":id
				},
				success:function (data){
					layer.msg('删除成功');
					//重新加载数据列表
					$('#box').datagrid('reload');
				}
	    	})
		},cancel: function(index){
			layer.msg('取消删除');
	    }
	});
}
$(function() {
	obj = {
			search : function () {
				$('#box').datagrid('load', {
					xinxi : $.trim($('input[name="xinxi"]').val()),
					startdate : $('input[name="startdate"]').val(),
					enddate : $('input[name="enddate"]').val(),
				});
			},
		};
	
	
	//平均列宽
	pingjunlie = 100/6;
	$('#box').datagrid({
		url : 'getLiaotianJiluBypage.action',
		title : '用户列表',
		iconCls : 'icon-search',
		fitColumns:true,//按照各列百分比平分宽度
		columns : [ [ 
					{
						field : 'fasongRenSessionid',
						title : '发送人id',
						width:pingjunlie,
						align:'center',
					}, {
						field : 'id',
						title : 'id',
						width:pingjunlie,
						align:'center',
					}, {
						field : 'shijian',
						title : '发送时间',
						width:pingjunlie,
						align:'center',
					}, {
						field : 'shouXinrenSessionid',
						title : '收信人',
						width:pingjunlie,
						align:'center',
					}, {
						field : 'xinxi',
						title : '内容',
						width:pingjunlie,
						align:'center',
					},{
						field:'1111111111',
						title:'操作',
						width:pingjunlie,
						align:'center',
						formatter: function(value,row,index){
							return '<a href="javascript://" onclick="shanchu('+row.id+')">删除</a>';
						}
					}
		] ],
		pagination : true,
		toolbar : '#tb',
		idField : 'id',
		pageSize : 10,
		pageList : [10, 20, 30],
	});
});