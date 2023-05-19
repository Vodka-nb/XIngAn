﻿﻿//layuiJS

 // 数据渲染

 layui.use(['table','jquery'],function(){

     var table = layui.table;

     var $ = layui.jquery;

     //服务员租车界面渲染
     table.render({

         elem: '#ProcessRent'

         ,url: '/ServerProcessRent' //数据接口

         ,page: true //开启分页

         ,method: 'post'

         ,width: 1405

         ,height: 640 //设置高度

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
                      {field: 'rentId', title: '租赁账单编号', width:120,sort: true},
                      {field: 'customerAccount', title: '顾客账号', width:110,sort: true},
                      {field: 'rentCarDate', title: '租车日期', width:150,sort: true},
                      {field: 'predictReturnDate', title: '预计还车日期', width:150},
                      {field: 'operator', title: '操作员', width:120, sort: true},
                      {field: 'remark', title: '备注', width:120 ,sort: true},
                      {field: 'rentCarAddress', title: '租车地点', width:121},
                      {field: 'deposit', title: '押金', width:111},
                      {field: 'rentMoney', title : '租金' , align:"center", width:115 },
                      {field: 'carSign', title : '车牌号' , align:"center", width:115 },
                      {field: 'Operation' , title:'操作', width:160, toolbar:'#ProcessingFunction' },

         ]]

     });


     //处理服务员界面租车信息
     table.on('tool(OrderProcessing)',function (obj){

        var data = obj.data; //获得当前行数据

        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）

        var tr = obj.tr; //获得当前行 tr 的DOM对象

        var jsonData = JSON.stringify(data);

        //顾客到店提车时，服务人员更改租车信息
         switch(obj.event){

          // 1.编辑订单
             case 'EditOrder':
              if($("#ServerName",parent.document).text()=="" || $("#AccountDisplay",parent.document).text() == "") layer.msg("请先登录");
             else if(obj.data.remark == '已归还') layer.msg('此订单已完结');
             else {
                layer.open({
                             //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）

                               type: 1,

                               title: "租车信息处理",

                               area: ['420px', '330px'],

                               btn: ['保存','取消'],

                               content: $("#ProcessPage"),//引用的弹出层的页面层的方式加载修改界面表单

                                   success: function(index,layero){

                                      //父层传数据至弹出层

                                      $('#Operator').val($('#ServerName',parent.document).text());

                                      $('#Remark').val(obj.data.remark);

                                      $('#Deposit').val(obj.data.deposit);

                                      $('#RentMoney').val(obj.data.rentMoney);

                                   },

                               yes: function(index,layero){

                                  //通过ajax刷新后台，成功后，再刷新表单

                                   $.ajax({

                                       url:"/ServerEditRentInfo",

                                       type:"post",

                                       data: {

                                           "operator": $('#Operator').val(),

                                            "serverAccount": $('#AccountDisplay',parent.document).text(),

                                           "remark":$('#Remark').val(),

                                           "deposit": $('#Deposit').val(),

                                           "rentMoney":  $('#RentMoney').val(),

                                           "rentId": obj.data.rentId,

                                           "customerAccount": obj.data.customerAccount,

                                           "carSign":  obj.data.carSign

                                       },

                                       traditional:true,  //阻止深度序列化

                                       dataType:"json",

                                       async: true,

                                       success:function(data){
                                           if(data ==  200){
                                               //更新表单

                                             obj.update({

                                                 remark:$('#Remark').val(),

                                             });
                                               table.reload('ProcessRent');
                                               layer.msg("处理成功!");
                                           }

                                           else  layer.msg("处理失败!");

                                       }

                                   });

                                   //自动关闭

                                   layer.close(index);

                          },

                          cancel:function(index,layero){

                          layer.msg('取消')

                          }

                 });   //layer.open
             }  //else

              break;



          // 2.删除账本条目

             case 'DeleteOrder':
              if($("#ServerName",parent.document).text()=="" || $("#AccountDisplay",parent.document).text() == "") layer.msg("请先登录");
             else if(obj.data.remark == '正在使用' || obj.data.remark == '待送审') layer.msg('该订单正在使用，不允许删除!');
             else {
                  var x;
                  var r=confirm("确认删除?");
                  if (r==true){
                                	    //ajax通信,删除数据库相关数据
                                     $.ajax({
                                                        url:"/ServerDeleteRent",
                                                        type:"post",

                                                        data: {

                                                            "rentId":obj.data.rentId,
                                                            "customerAccount": obj.data.customerAccount,
                                                            "carSign": obj.data.carSign,

                                                        },

                                                        traditional:true,  //阻止深度序列化

                                                        dataType:"json",

                                                        async: true,

                                                        success:function(data){

                                                            if(data == 200){

                                                                obj.del(); //删除该行

                                                                layer.msg("删除成功!");

                                                            }

                                                            else  layer.msg("删除失败!");

                                                        }

                                                    });  //ajax
                                }
                  else{
                  	layer.msg("取消")
                  }
             }
             break;

         }

     });

 });
