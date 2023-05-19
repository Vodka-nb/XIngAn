//layuiJS
// 数据渲染
layui.use(['table','jquery'],function(){
    var table = layui.table;
    layer = layui.layer,
    tree = layui.tree,
    $ = layui.jquery;

    //动态渲染归还车辆信息表
    table.render({
        elem: '#ProcessReturnBill'
        ,url: '/ServerProcessReturn' //数据接口
        ,page: true //开启分页
        ,method: 'post'
        ,width: 1400
        ,height: 650 //设置高度
        ,limit: 10
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
                      {field: 'returnId', title : '还车账单编号' , width:180 ,fixed:'left'},
                      {field: 'rentId', title: '租车账单编号', width:130,sort: true},
                      {field: 'evaluateCar', title: '车辆评估状况', width:100,sort: true},
                      {field: 'acctuallyReturnCarTime', title: '实际归还时间', width:181 },
                      {field: 'returnCarAddress', title: '还车地点', width:155 ,sort: true},
                      {field: 'rentMoney', title: '租金', width:161,sort: true},
                      {field: 'penaltyMoney', title: '赔偿金', width:161,sort: true},
                      {field: 'operatorId', title: '操作员ID', width:161,sort: true},
                      {field: 'Operation' , title:'操作', width:160, toolbar:'#ProcessingReturnBillFunction' }
        ]]
    });


    //监听表单信息变化
    table.on('tool(ReturnCarProcessing)',function (obj){
      var data = obj.data; //获得当前行数据
      var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
      var tr = obj.tr; //获得当前行 tr 的DOM对象
      var jsonData = JSON.stringify(data);

       switch(obj.event){
        // 1.编辑还车订单
           case 'EditReturnOrder':
            if($("#ServerName",parent.document).text()=="" || $("#AccountDisplay",parent.document).text() == "") layer.msg("请先登录");
           else if(obj.data.evaluateCar  != undefined ) layer.msg('此订单已完结');
           else {
              layer.open({
                          //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                           type: 1,
                           title: "还车信息处理",
                           area: ['420px', '330px'],
                           btn: ['保存','取消'],
                           content: $("#ProcessPage"),//引用的弹出层的页面层的方式加载修改界面表单
                           success: function(index,layero){
                                    //父层传数据至弹出层
                                    $('#PenaltyInfo').val(obj.data.penaltyMoney);
                                    $('#EvaluateCarInfo').val(obj.data.evaluateCarInfo);
                                    $('#OperatorId').val($('#AccountDisplay',parent.document).text());
                           },
                           yes: function(index,layero){
                              console.log(obj.data.returnId);
                              //通过ajax刷新后台，成功后，再刷新表单
                               $.ajax({
                                   url:"/ServerEditReturn",
                                   type:"post",
                                   data: {
                                       "PenaltyMoney":$('#PenaltyInfo').val(),
                                       "EvaluateCarInfo":$('#EvaluateCarInfo').val(),
                                       "OperatorId":$('#AccountDisplay',parent.document).text(),
                                       "ReturnId":obj.data.returnId
                                   },
                                   traditional:true,  //阻止深度序列化
                                   dataType:"json",
                                   async: true,
                                   success:function(data){
                                       if(data == 200){
                                           //更新表单
                                          table.reload('ProcessReturnBill');
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
                          });  //open
           }  //else

            break;

        // 2.删除账本条目
           case 'DeleteReturnOrder':
            if($("#ServerName",parent.document).text()=="" || $("#AccountDisplay",parent.document).text() == "") layer.msg("请先登录");
            else {
                var x;
                           var r=confirm("确认删除?");
                           if (r==true){
                              //ajax通信,删除数据库相关数据
                              $.ajax({
                                                 url:"/ServerDeleteReturn",
                                                 type:"post",
                                                 data: {
                                                     "ReturnId": obj.data.returnId,

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
                           }
                           else layer.msg('取消');
            }//else
           break;              
       }
    });

 });