// 数据渲染
layui.use(['table','jquery','laydate'],function(){
      var table = layui.table;
          var $ = layui.jquery;
          var laydate = layui.laydate;

          //动态渲染顾客租车信息表
          table.render({
                  elem: '#CustomerRentOrderInfo'
                  ,url: '/CustomerRentOrder' //数据接口
                  ,page: true //开启分页
                  ,method: 'post'
                  ,width: 1500
                  ,height: 590 //设置高度
                  ,limit: 8
                  ,where:{
                    "CustomerName":  $("#CustomerName",parent.document).text(), //iframe获取外部的值，传参到后台
                    "CustomerAccount":  $("#AccountDisplay",parent.document).text()   //iframe获取外部的值，传参到后台
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
                      {field: 'rentId', title: '租赁账单编号', width:128,sort: true},
                      {field: 'customerAccount', title: '顾客账号', width:128,sort: true},
                      {field: 'rentCarDate', title: '租车日期', width:150,sort: true},
                      {field: 'predictReturnDate', title: '预计还车日期', width:150},
                      {field: 'operator', title: '操作员', width:120, sort: true},
                      {field: 'remark', title: '备注', width:120 ,sort: true},
                      {field: 'rentCarAddress', title: '租车地点', width:121},
                      {field: 'deposit', title: '押金', width:111},
                      {field: 'rentMoney', title : '租金' , align:"center", width:115 },
                      {field: 'carSign', title : '车牌号' , align:"center", width:115 },
                      {field: 'Operation' , title:'功能', width:230, toolbar:'#toolsBar'}
                  ]],
                   toolbar: '#toolbarDemo',//开启自定义工具行，指向自定义工具栏模板选择器
                   defaultToolbar: ['filter', 'print', 'exports']
              });

          //监听头部工具栏
              table.on('toolbar(RentOrderFilter)', function (obj) {
                  switch (obj.event) {
                      case 'getRefresh':
                          table.reload('CustomerRentOrderInfo')
                          break;
                  	}
              });

          //顾客租车账单相关操作（支付或取消）
          table.on('tool(RentOrderFilter)',function (obj){
                             console.log(JSON.stringify(obj.data)); //展示当前行数据
                                    switch(obj.event){
                                    /* 用户支付账单:  1.已归还,不用再重复支付，正在使用的账单不能删除，不能支付
                                                      2. 用户支付了账单后，租车账单状态由‘待支付’转为‘待使用’
                                                      3. 顾客去提车后，服务人员处理完，租车账单状态由‘待使用’转为‘正在使用’，同时加入还车账单，方便用户归还
                                    */
                                        case 'CustomerPay':

                                        if(obj.data.remark == '已归还' || obj.data.remark == '正在使用' || obj.data.remark == '待使用' || obj.data.remark == '待评审') layer.msg("该账单无需重复支付");
                                         else{
                                          layer.open({
                                                                               //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                                                                               type: 1,
                                                                               title: "租车账单",
                                                                               area: ['520px','430px'],
                                                                               btn: ['支付','取消'],
                                                                               content: $("#ProcessPage"),//引用的弹出层的页面层的方式加载修改界面表单
                                                                               success: function(index,layero){
                                                                              		 //父层传数据至弹出层
                                                                              		 $('#RentOrderMoney').val(obj.data.rentMoney+" 元");
                                                                               },
                                                                                 yes: function(index,layero){
                                                                                   //通过ajax刷新后台，成功后，再刷新表单
                                                                                       $.ajax({
                                                                                               url:"/CustomerPayRentOrder",
                                                                                               type:"post",
                                                                                               data: {
                                                                                                   "rentId":obj.data.rentId,
                                                                                                   "customerAccount":obj.data.customerAccount,
                                                                                                   "rentMoney":obj.data.rentMoney,
                                                                                                   "carSign": obj.data.carSign
                                                                                               },
                                                                                               traditional:true,  //阻止深度序列化
                                                                                               dataType:"json",
                                                                                               async: true,
                                                                                               success:function(data){
                                                                                                   if(data == 200){

                                                                                                         	//更新表单
                                                                                                       table.reload('CustomerRentOrderInfo');
                                                                                                         layer.msg("支付成功!");
                                                                                                   }
                                                                                                   else  layer.msg("支付失败!");
                                                                                               }
                                                                                        });
                                                                                     	 //自动关闭
                                                                                     	 layer.close(index);
                                                                                 },
                                                                                 cancel:function(index,layero){

                                                                                 }
                                                                               });
                                         }  //else
                                         break;

                                     //可以取消待支付租车订单，其他不行(取消订单后，若账单状态为‘待使用’，账单记录表也删除相应租金，押金订单)
                                       case 'CustomerCancel':

                                        if(obj.data.remark == '正在使用' || obj.data.remark=='待评审' ) layer.msg("该账单不允许删除!");
                                        else{
                                         //ajax通信,删除数据库相关数据
                                         var msg = confirm("确定取消该订单？");
                                         if(msg == true ) {
                                     	   $.ajax({
                                                                 url:"/CancelCustomerRentOrder",
                                                                 type:"post",
                                                                 data: {
                                                                     "rentId":obj.data.rentId,
                                                                     "carSign": obj.data.carSign,
                                                                     "customerAccount":obj.data.customerAccount
                                                                 },
                                                                 traditional:true,  //阻止深度序列化
                                                                 dataType:"json",
                                                                 async: true,
                                                                 success:function(data){
                                                                     if(data == 200){
                                                                           obj.del();  //删除当前行
                                                                           layer.msg("删除成功!");
                                                                     }
                                                                     else  layer.msg("删除失败!");
                                                                  }
                                           })
                                         }
                                       break;
                                       }//switch
                                    }
                             })

});
