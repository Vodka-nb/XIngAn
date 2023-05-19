$(function(){
    //点击相应选项，切换相应的动态画面
      $(".FunctionBar").on("click",function(){
          var address =$(this).attr("data-src");
          $("iframe").attr("src",address);
      });
    
});


//车辆信息表单元素获取
layui.use(['table','jquery'], function(){
  var table = layui.table;
  var $ = layui.jquery;

  //动态数据渲染
table.render({
    elem: '#ServerInfo'
    ,url: '/ServerTable' //数据接口
    ,page: true //开启分页
    ,method: 'post'
    ,width: 1000
    ,height: 540 //设置高度
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
        {field: 'serverID', title: '账号', width:150, sort: true, fixed: 'left'},
        {field: 'serverName', title: '昵称', width:100,sort: true},
        {field: 'serverPhone', title: '联系方式', width:140,sort: true},
        {field: 'serverSex', title: '性别', width:100},
        {field: 'serverRemark', title: '备注', width: 223},
        {fixed: 'right', title : '操作', width : 281, toolbar:'#FunctionBar' }
    ]],
     toolbar: '#toolbarDemo',//开启自定义工具行，指向自定义工具栏模板选择器
     defaultToolbar: ['filter', 'print', 'exports']
});

  
  //利用弹出层修改服务员信息
  table.on('tool(ServerFilter)',function (obj){
     var data = obj.data; //获得当前行数据
     var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
     var tr = obj.tr; //获得当前行 tr 的DOM对象
     var jsonData = JSON.stringify(data);
     console.log(jsonData); //展示当前行json格式数据

     //顾客到店提车时，更改的信息
      switch(obj.event){
       // 1.编辑订单
          case 'edit':
           if($("#SuperName",parent.document).text()=="" || $("#AccountDisplay",parent.document).text() == "") layer.msg("请先登录");
           else{
                      layer.open({
                          //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                       type: 1,
                       title: "服务人员信息处理",
                       area: ['520px', '430px'],
                       btn: ['保存','取消'],
                       content: $("#ProcessPage"),//引用的弹出层的页面层的方式加载修改界面表单
             		    success: function(index,layero){
             			  //父层传数据至弹出层
             			  $('#ServerIdInfo').val(obj.data.serverID);
             			  $('#ServerNameInfo').val(obj.data.serverName);
             			  $('#ServerPhoneInfos').val(obj.data.serverPhone);
             			  $('#ServerSexInfo').val(obj.data.serverSex);
             			  $('#ServerRemarkInfo').val(obj.data.serverRemark);
             		    },
                          yes: function(index,layero){
                          layer.msg("ajax");
                          //通过ajax刷新后台，成功后，再刷新表单
                           $.ajax({
                               url:"/EditServerInfo",
                               type:"post",
                               data: {
                                   "serverID":$('#ServerIdInfo').val(),
                                   "serverName":$('#ServerNameInfo').val(),
                                   "serverPhone":$('#ServerPhoneInfos').val(),
                                   "serverSex":$('#ServerSexInfo').val(),
                                   "serverRemark":$('#ServerRemarkInfo').val(),
                               },
                               traditional:true,  //阻止深度序列化
                               dataType:"json",
                               async: true,
                               success:function(data){
                                   if(data == 200){
                                       //更新表单
                                       obj.update({
                                           serverID: $('#ServerIdInfo').val(),
                                           serverName: $('#ServerNameInfo').val(),
                                           serverPhone: $('#ServerPhoneInfos').val(),
                                           serverRemark: $('#ServerRemarkInfo').val(),
                                           serverSex: $('#ServerSexInfo').val(),
                                       });
                                       layer.msg("修改成功!");
                                       //自动关闭
                                       document.getElementById("ProcessPage").style.display="none";  //隐藏弹出层
                                       layer.close(index);
                                   }
                                   else  layer.msg("修改失败!");
                               }
                           });
                           },
                          cancel:function(index,layero){
                              layer.msg('取消')
                          }
                      });
           }  //else
           break;              
     
       // 2.删除服务员信息
          case 'del':
           if($("#SuperName",parent.document).text()=="" || $("#AccountDisplay",parent.document).text() == "") layer.msg("请先登录");
           else{
            //ajax通信,删除数据库相关数据
                         var msg = confirm("确定删除该服务员？");
                         if(msg == true ) {
                             $.ajax({
                             url:"/DeleteServerInfo",
                             type:"post",
                             data: {
                                 "serverID": obj.data.serverID,
                                 "serverPhone": obj.data.serverPhone,
                             },
                             dataType:"json",
                             async: true,
                             success:function(data){
                                 if(data == 200) {
                                     obj.del(); //删除该行
                                     layer.msg("删除成功!");
                                 }
                                 else  layer.msg("删除失败!");
                             }
                         });
                         }
                         else layer.msg('取消');
           }
          break;              
      }
  });

  //监听头部工具栏
  table.on('toolbar(ServerFilter)', function (obj) {
      var checkStatus = table.checkStatus(obj.config.id);
      switch (obj.event) {
      	case 'getInsert':
      	 if($("#SuperName",parent.document).text()=="" || $("#AccountDisplay",parent.document).text() == "") layer.msg("请先登录");
      	 else{
      	 //              新增服务员信息
                      layer.open({
                                                        //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                                                        type: 1,
                                                        title: "添加服务员",
                                                        area: ['420px', '330px'],
                                                        btn: ['保存','取消'],
                                                        offset: ['100px', '400px'],
                                                        content: $("#AddServer"),//引用的弹出层的页面层的方式加载修改界面表单
                                                        success: function(index,layero){

                                                        },
                                                        yes: function(index,layero){
                                                          //通过ajax把新的管理员信息发送至后台，成功后，再返回提示
                                                          //监听表单
                                                          if($('#AddServerAccountInfo').val()=='') layer.msg("账号不能为空!");
                                                          else if($('#AddServerPhoneInfo').val()=='') layer.msg("联系方式不能为空!");
                                                          else if($('#AddServerNameInfo').val()=='') layer.msg("姓名不能为空!");
                                                          else if($('#AddServerPassWordInfo').val()=='') layer.msg("密码不能为空!");
                                                          else if($('#CertifyAddServerPassWordInfo').val()=='') layer.msg("请确认密码!");
                                                          else if($('#AddServerGenderInfo').val()=='') layer.msg("性别不能为空!");
                                                          else if($('#AddServerRemarkInfo').val()=='') layer.msg("备注不能为空!");

                                                          //若两次密码一样，则发送ajax
                                                          else if($('#AddServerPassWordInfo').val() != $('#CertifyAddServerPassWordInfo').val()) layer.msg("两次密码不一致!");
                                                          else{
                                                            //ajax发送修改请求
                                                            $.ajax({
                                                                url:"/AddServerInfo",
                                                                type:"post",
                                                                data: {
                                                                    "nickName":$('#AddServerNameInfo').val(),
                                                                    "account":$('#AddServerAccountInfo').val(),
                                                                    "passWordOne":$('#AddServerPassWordInfo').val(),
                                                                    "passWordTwo":$('#CertifyAddServerPassWordInfo').val(),
                                                                    "gender":$('#AddServerGenderInfo').val(),
                                                                    "remark":$('#AddServerRemarkInfo').val(),
                                                                    "phone":$('#AddServerPhoneInfo').val(),
                                                                },
                                                                dataType:"json",
                                                                async: true,
                                                                success:function(data){
                                                                    if(data == 200) layer.msg("添加成功!");
                                                                    else  layer.msg("添加失败!");
                                                                }
                                                            });
                                                            //自动关闭
                                                            layer.close(index);
                                                            document.getElementById("AddServer").style.display="none";
                                                           } //else
                                                        },
                                                        cancel:function(index,layero){
                                                            layer.msg('取消')
                                                        }
                                                    });
      	 }

              break;
          case 'getRefresh':
//            刷新
              location.reload();
              break;
      	}
  });

});   