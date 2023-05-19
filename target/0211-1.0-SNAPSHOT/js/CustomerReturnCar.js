//layuiJS 
// 数据渲染
layui.use(['table','jquery'],function(){
    var table = layui.table;
    var $ = layui.jquery;

    //动态渲染归还车辆信息表
    table.render({
        elem: '#ReturnCar'
        ,url: '/CustomerReturn' //数据接口
        ,page: true //开启分页
        ,method: 'post'
        ,width: 1355
        ,height: 650 //设置高度
        ,limit: 10
        ,where:{
                  "CustomerName":  $("#CustomerName",parent.document).text() ,  //iframe获取外部的值，传参到后台
                  "CustomerAccount":  $("#AccountDisplay",parent.document).text()  //iframe获取外部的值，传参到后台
        }
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
            {field: 'returnCarAddress', title: '还车地点', width:111,sort: true},
            {field: 'rentMoney', title: '租金', width:161,sort: true},
            {field: 'penaltyMoney', title: '赔偿金', width:161,sort: true},
            {field: 'operatorId', title: '操作员ID', width:161,sort: true},
            {field: 'Operation' , title:'操作', width:160, toolbar:'#ReturnFunction' }
        ]],
         toolbar: '#toolbarDemo',//开启自定义工具行，指向自定义工具栏模板选择器
                defaultToolbar: ['filter', 'print', 'exports']
    });

   //监听头部工具栏
       table.on('toolbar(CustomerReturnCar)', function (obj) {
           switch (obj.event) {
               case 'getRefresh':
                   location.reload();
                   break;
           	}
       });

    //利用弹出层归还车辆
    table.on('tool(CustomerReturnCar)',function (obj){
        console.log(JSON.stringify(obj.data)); //展示当前行数据
       switch(obj.event){
        // 用户归还车辆
           case 'CustomerReturnCarProcess':
             if($("#CustomerName",parent.document).text()=="" || $("#AccountDisplay",parent.document).text() == "") layer.msg("请先登录");
             else if(obj.data.acctuallyReturnCarTime == undefined && obj.data.operatorId == undefined) {
                layer.open({
                            //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                            type: 1,
                            title: "还车订单处理",
                            area: ['520px', '430px'],
                            btn: ['归还','取消'],
                            content: $("#ProcessPage"),//引用的弹出层的页面层的方式加载修改界面表单
                            success: function(index,layero){
                           		//父层传数据至弹出层
                           		$('#ReturnAddressInfo').val('');
                           		$('#RentIdInfo').val(obj.data.rentId);
                                $('#ReturnIdInfo').val(obj.data.returnId);
                            },
                            yes: function(index,layero){
                               //通过ajax刷新后台，成功后，再刷新表单
                                if($('#ReturnAddressInfo').val()=="") layer.msg("还车地点不能为空！");
                                 else{
                                     $.ajax({
                                                                          url:"/ReturnFunction",
                                                                          type:"post",
                                                                          data: {
                                                                              "rentId":$('#RentIdInfo').val(),
                                                                              "returnCarAddress": $('#ReturnAddressInfo').val(),
                                                                              "returnId": $('#ReturnIdInfo').val() //代填
                                                                          },
                                                                          traditional:true,  //阻止深度序列化
                                                                          dataType:"json",
                                                                          async: true,
                                                                          success:function(data){
                                                                              if(data == 200){
                                                                                  //更新表单
                                                                                table.reload('ReturnCar');
                                                                                  layer.msg("归还成功!");

                                                                              }
                                                                              else  layer.msg("归还失败!");
                                                                          }
                                                                      });
                                      //自动关闭
                                       document.getElementById("ProcessPage").style.display="none";  //隐藏弹出层
                                       layer.close(index);
                                 }          //else
                              },
                              cancel:function(index,layero){
                             	 layer.msg('取消');
                              }
                           });
            }
            else layer.msg("该订单已归还");
           break;


        // 用户缴纳赔偿金
           case 'CustomerPayPenalty':
              if($("#CustomerName",parent.document).text()=="" || $("#AccountDisplay",parent.document).text() == "") layer.msg("请先登录");
               else if(obj.data.penaltyMoney != "0.0" && obj.data.evaluateCar != "无损伤" && obj.data.penaltyMoney != null && obj.data.evaluateCar != null ) {
                             $.ajax({
                                                                          url:"/CustomerPayPenaltyMoney",
                                                                          type:"post",
                                                                          data: {
                                                                              "returnId": obj.data.returnId,
                                                                              "penaltyMoney":obj.data.penaltyMoney
                                                                          },
                                                                          traditional:true,  //阻止深度序列化
                                                                          dataType:"json",
                                                                          async: true,
                                                                          success:function(data){
                                                                              if(data == 200){
                                                                                  //重载表单
                                                                                  table.reload('ReturnCar');
                                                                                  layer.msg("缴纳成功!");
                                                                              }
                                                                              else  layer.msg("缴纳失败!");
                                                                          }
                                                                      });
                       }
               else layer.msg("该订单无需缴纳赔偿金");
            break;

       }
    });
});
