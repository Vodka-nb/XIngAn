//登录
layui.use(['element', 'layer','jquery','util'], function(obj){
    var element = layui.element
        ,layer = layui.layer
        ,util = layui.util
        ,$ = layui.jquery
        ,form = layui.form;

    //登录跳转页面
 form.on('submit(Loginbutton)', function () {

       //判断是哪个系统角色身份登录
       switch ($("#identity").val()) {
           case "1":
               $.ajax({
                   url : "/Customer",
                   data : {
                       'identity' : $("#identity").val(),
                       'userAccount' : $("#userAccount").val(),
                       'password' : $("#password").val()
                   },
                   type : "post",
                   dataType : "json",
                   complete : function(XMLHttpRequest) {
                           //获取响应头
                            const DispatchMsg = XMLHttpRequest.getResponseHeader("DispatchMsg");

                            //重定向
                            if(DispatchMsg == "REDIRECT"){
                               window.location.href = XMLHttpRequest.getResponseHeader("CONTEXTPATH");
                            }
                            else{
                               let AccountError = XMLHttpRequest.getResponseHeader("AccountError");
                               let PassWordError = XMLHttpRequest.getResponseHeader("PassWordError");
                               if( AccountError!= null) layer.msg(AccountError);
                               if( PassWordError!= null) layer.msg(PassWordError);

                            }

                   },
               });
               break;

           case "2":
               $.ajax({
                   url : "/Server",
                   data : {
                        'identity' : $("#identity").val(),
                         'userAccount' : $("#userAccount").val(),
                         'password' : $("#password").val()
                   },
                   type : "post",
                   dataType : "json",
                   complete : function(XMLHttpRequest) {
                                         //获取响应头
                                                                    const DispatchMsg = XMLHttpRequest.getResponseHeader("DispatchMsg");

                                                                    //重定向
                                                                    if(DispatchMsg == "REDIRECT"){
                                                                       window.location.href = XMLHttpRequest.getResponseHeader("CONTEXTPATH");
                                                                    }
                                                                    else{
                                                                       let AccountError = XMLHttpRequest.getResponseHeader("AccountError");
                                                                       let PassWordError = XMLHttpRequest.getResponseHeader("PassWordError");
                                                                       if( AccountError!= null) layer.msg(AccountError);
                                                                       if( PassWordError!= null) layer.msg(PassWordError);

                                                                    }
                    },
               });
               break;

               case "3":
               $.ajax({
                   url : "/Admin",
                   data : {
                        'identity' : $("#identity").val(),
                        'userAccount' : $("#userAccount").val(),
                        'password' : $("#password").val()
                   },
                   type : "post",
                   dataType : "json",
                   complete : function(XMLHttpRequest) {
                                       //获取响应头
                                                                  const DispatchMsg = XMLHttpRequest.getResponseHeader("DispatchMsg");

                                                                  //重定向
                                                                  if(DispatchMsg == "REDIRECT"){
                                                                     window.location.href = XMLHttpRequest.getResponseHeader("CONTEXTPATH");
                                                                  }
                                                                  else{
                                                                     let AccountError = XMLHttpRequest.getResponseHeader("AccountError");
                                                                     let PassWordError = XMLHttpRequest.getResponseHeader("PassWordError");
                                                                     if( AccountError!= null) layer.msg(AccountError);
                                                                     if( PassWordError!= null) layer.msg(PassWordError);

                                                                  }
                    }
               });
               break;
       }
   })


    //注册页面
   $("#Registerbutton").click(function () {
        form =layui.form;

            layer.open({
                            //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                            type: 1,
                            title: "顾客注册",
                            area: ['520px', '530px'],
                            btn: ['确认','取消'],
                            content: $("#ProcessPage"),//引用的弹出层的页面层的方式加载修改界面表单
                            yes: function(index,layero){
                                 //监听表单
                                  if($('#Account').val()=='') layer.msg("账号不能为空!");
                                  else if($('#PhoneNum').val()=='') layer.msg("联系方式不能为空!");
                                  else if($('#Name').val()=='') layer.msg("姓名不能为空!");
                                  else if($('#PW1').val()=='') layer.msg("密码不能为空!");
                                  else if($('#PW2').val()=='') layer.msg("请确认密码!");
                                  else if($('#CardType').val()=='') layer.msg("证件类型!");
                                  else if($('#CardNum').val()=='') layer.msg("证件号不能为空!");
                                  else if($('#Address').val()=='') layer.msg("地址不能为空!");
                                 //若两次密码一样，则发送ajax
                                 else if($('#PW1').val() != $('#PW2').val()) layer.msg("两次密码不一致!");
                                  else{
                                         //通过ajax刷新后台，成功后，再刷新表单
                                          $.ajax({
                                                         url:"/CustomerRegister",
                                                         type:"post",
                                                         data: {
                                                             "Account":$('#Account').val(),
                                                             "PhoneNum":$('#PhoneNum').val(),
                                                             "Name":$('#Name').val(),
                                                             "PW1":$('#PW1').val(),
                                                             "PW2":$('#PW2').val(),
                                                             "CardType":$('#CardType').val(),
                                                             "CardNum":$('#CardNum').val(),
                                                             "Address":$('#Address').val(),

                                                        },
                                                       traditional:true,  //阻止深度序列化
                                                      dataType:"json",
                                                       async: true,
                                                      success:function(data){
                                                          if(data == "200"){
                                                              layer.msg("顾客注册成功!");
                                                               //自动关闭
                                                               layer.close(index);
                                                               document.getElementById("ProcessPage").style.display="none";  //隐藏弹出层
                                                          }
                                                          else  if(data == "401" ) layer.msg("该账号已存在");
                                                          else layer.msg("注册失败");
                                                      }
                                                  });
                                  }

                            },
                             cancel:function(index,layero){
                                layer.msg('取消')
                             }
            });
   })
});
