//layuiJS 
// 数据渲染
layui.use(['table','jquery','laydate'],function(){
    var table = layui.table;
    var $ = layui.jquery;
    var laydate = layui.laydate;

      //日期时间选择器
          laydate.render({
              elem: '#PayTimeInfo'
              ,type: 'datetime'
          });

    //服务员账单记录表
    table.render({
        elem: '#ProcessRent'
        ,url: '/ServerProcessOrder' //数据接口
        ,page: true //开启分页
        ,method: 'post'
        ,width: 1040
        ,height: 500 //设置高度
        ,limit: 8
        ,parseData:function (res) {   //解析传回的数据
            // 最后返回规定的数据格式
            return {
                "code": res.code, //解析口状态
                "msg": res.msg, //解析提示文本
                "count": res.length,
                "data": res.data //解析数据列表
            };
        }
        ,cols: [[ //表头
                                  {field: 'orderId', title: '账单编号' , width:130  },
                                  {field: 'returnId', title: '租/还账单编号', width:130,sort: true},
                                  {field: 'payMethod', title: '金额形式', width:150,sort: true},
                                  {field: 'orderMoney', title: '具体金额', width:130,sort: true},
                                  {field: 'payTime', title: '支付时间', width:300,sort: true},
                                  {field: 'Operation' ,  title:'操作', width:160, toolbar:'#OrderRecordFunction' }
        ]],
        toolbar: '#toolbarDemo', //开启自定义工具行，指向自定义工具栏模板选择器
        defaultToolbar: ['filter', 'print', 'exports']
    });


    //利用弹出层修改订单信息
    table.on('tool(OrderRecordProcessing)',function (obj){
       var data = obj.data; //获得当前行数据
       var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
       var tr = obj.tr; //获得当前行 tr 的DOM对象
       var jsonData = JSON.stringify(data);

       //服务人员编辑账单信息
        switch(obj.event){
         // 1.编辑订单

            case 'ServerEditOrder':
             if($("#ServerName",parent.document).text()=="" || $("#AccountDisplay",parent.document).text() == "") layer.msg("请先登录");
                      else{
                         layer.open({
                                       //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                                    type: 1,
                                    title: "账单编辑处理",
                                    area: ['520px', '430px'],
                                    btn: ['保存','取消'],
                                    content: $("#ProcessPage"),//引用的弹出层的页面层的方式加载修改界面表单
                      			  success: function(index,layero){
                      				  //父层传数据至弹出层
                      				  $('#OrderIdInfo').val(obj.data.orderId);
                      				  $('#ReturnIdInfo').val(obj.data.returnId);
                      				  $('#PayMethodInfo').val(obj.data.payMethod);
                      				  $('#MoneyInfo').val(obj.data.orderMoney);
                      				  $('#PayTimeInfo').val(obj.data.payTime);
                      			  },
                                    yes: function(index,layero){
                                      //通过ajax刷新后台，成功后，再刷新表单
                                             $.ajax({
                                                           url:"/ServerEditOrder",
                                                           type:"post",
                                                           data: {
                                                                "orderId":$('#OrderIdInfo').val(),
                                                                "returnId":$('#ReturnIdInfo').val(),
                                                                "payMethod":$('#PayMethodInfo').val(),
                                                                "money":$('#MoneyInfo').val(),
                                                                "payTime":$('#PayTimeInfo').val(),
                                                           },
                                                           traditional:true,  //阻止深度序列化
                                                           dataType:"json",
                                                           async: true,
                                                           success:function(data){
                                                               if(data == 200){
                                                                   //更新表单
                                                                 table.reload('ProcessRent')
                                                                   layer.msg("修改成功!");
                                                               }
                                                               else  layer.msg("修改失败!");
                                                           }
                                                       });
                             				  //自动关闭
                             				  layer.close(index);
                             			 },
                             			 cancel:function(index,layero){
                             				 layer.msg('取消')
                             			 }
                                   });
                      }//else

             break;
       
         // 2.删除账本条目
            case 'ServerDeleteOrder':
             if($("#ServerName",parent.document).text()=="" || $("#AccountDisplay",parent.document).text() == "") layer.msg("请先登录");
             else{
              var msg = confirm("确定删除该账单？");
                           if(msg == true){
                               //ajax通信,删除数据库相关数据
                               $.ajax({
                                               url:"/ServerDeleteOrder",
                                               type:"post",
                                               data: {
                                                    "orderId":obj.data.orderId,
                                                    "returnId": obj.data.returnId
                                               },
                                               traditional:true,  //阻止深度序列化
                                               dataType:"json",
                                               async: true,
                                               success:function(data){
                                                   if(data == 200){
                                                       //更新表单
                                                       obj.del(); //删除该行
                                                       layer.msg("删除成功!");
                                                   }
                                                   else  layer.msg("删除失败!");
                                               }
                                           });
                           }else {
                               layer.msg('取消');
                           }
             }//else
            break;              
        }
    });

    //监听头部工具栏
        table.on('toolbar(OrderRecordProcessing)', function (obj) {
            switch (obj.event) {

                case 'getRefresh':
                    location.reload();
                    break;
            	}
        });

        //表格搜索
             //以下是搜索框进行监测
             var active = {
                    	  reload: function(){
                    	      var OrderIdInfo = $('#OrderIdSearch').val();	//得到搜索框里已输入的车牌号
                    	      var RentOrReturnInfo = $('#RentOrReturnSearch').val();	//得到搜索框里已输入的车型
                    	      var MoneyStatusSearchInfo = $('#MoneyStatusSearch').val();	//得到搜索框里已输入的租赁状况

                    	      //执行重载
                    	      table.reload('ProcessRent', {
                    	      url: '/SearchRent',
                    	        page: {
                    	          curr: 1 //重新从第 1 页开始
                    	        },
                    	        where: {
                    	          orderId:  OrderIdInfo	,
                    	          RentOrReturnId:  RentOrReturnInfo	,
                    	          moneyStatus:  MoneyStatusSearchInfo	,
                    	        }
                    	      });
                    	    }
              };  //var active

             $('#btn .layui-btn').on('click', function(){
               var type = $(this).data('type');
               active[type] ? active[type].call(this) : '';
             });
});