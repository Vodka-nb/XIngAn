//layuiJS
$(function(){
//点击相应选项，切换相应的动态画面
  $(".FunctionBar").on("click",function(){
      var address =$(this).attr("data-src");
      //跳转
      $("iframe").attr("src",address);
  });




});


layui.use(['element', 'layer', 'util','jquery'], function(){
    var element = layui.element
    ,layer = layui.layer
    ,util = layui.util
    ,$ = layui.jquery;

   //修改资料
        $('#Profile').click(function(){
               //修改资料
               layer.open({
                                       //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                                        type: 1,
                                        title: "修改资料",
                                        area: ['420px', '330px'],
                                        offset: [100,300],
                                        btn: ['保存'],
                                        content: $("#ProfilePage"),//引用的弹出层的页面层的方式加载修改界面表单
                                        success: function(index,layero){
                                                 //父层传数据至弹出层
     //                                            $('#PenaltyInfo').val(obj.data.penaltyMoney);
     //                                            $('#EvaluateCarInfo').val(obj.data.evaluateCarInfo);
     //                                            $('#OperatorId').val($('#AccountDisplay',parent.document).text());
                                        },
                                        yes: function(index,layero){

                                           //通过ajax刷新后台，成功后，再刷新表单
                                           if($('#AdminNameInfo').val()=='') layer.msg('昵称不能为空');
                                           else if($('#AdminPhoneInfo').val()=='') layer.msg('电话号码不能为空');
                                           else if($('#AdminGenderInfo').val()=='') layer.msg('性别不能为空');
                                           else if($('#AdminRemark').val()=='') layer.msg('备注不能为空');
                                           else{
                                                 $.ajax({
                                                                                          url:"/AdminEditProfile",
                                                                                          type:"post",
                                                                                          data: {
                                                                                              "Name":$('#AdminNameInfo').val(),
                                                                                              "Phone":$('#AdminPhoneInfo').val(),
                                                                                              "Gender":$('#AdminGenderInfo').val(),
                                                                                              "Remark":$('#AdminRemarkInfo').val(),
                                                                                              "Account": $('#AccountDisplay',parent.document).text()
                                                                                          },
                                                                                          traditional:true,  //阻止深度序列化
                                                                                          dataType:"json",
                                                                                          async: true,
                                                                                          success:function(data){
                                                                                              if(data == "200"){
                                                                                                  //更新表单
                                                                                                  layer.msg("修改成功!");


                                                                                              }
                                                                                              else  layer.msg("修改失败!");
                                                                                          }
                                                                                      });//ajax
                                                                                      //自动关闭
                                                layer.close(index);
                                                document.getElementById("ProfilePage",parent.document).style.display="none";  //隐藏弹出层
                                           }  //esle
                           			   }, //yes
                           			 cancel:function(index,layero){
                           				 layer.msg('取消');
                           				  //自动关闭
                                          layer.close(index);
                                           document.getElementById("ProfilePage",parent.document).style.display="none";  //隐藏弹出层
                           			 } //cancel
                      });  //open
        });// Profile

   //修改密码
        $('#Setting').click(function(){
                       //修改资料
                       layer.open({
                                               //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                                                type: 1,
                                                title: "修改密码",
                                                area: ['420px', '330px'],
                                                offset: [100,300],
                                                btn: ['保存'],
                                                content: $("#PassWordPage"),//引用的弹出层的页面层的方式加载修改界面表单
                                                success: function(index,layero){
                                                         //父层传数据至弹出层
             //                                            $('#PenaltyInfo').val(obj.data.penaltyMoney);
             //                                            $('#EvaluateCarInfo').val(obj.data.evaluateCarInfo);
             //                                            $('#OperatorId').val($('#AccountDisplay',parent.document).text());
                                                },
                                                yes: function(index,layero){

                                                   //通过ajax刷新后台，成功后，再刷新表单
                                                   if($('#OldPassWord').val()=='') layer.msg('旧密码不能为空');
                                                   else if($('#NewPassWord').val()=='') layer.msg('新密码不能为空');
                                                   else if($('#EnsurePassWord').val()=='') layer.msg('确认密码不能为空');
                                                   else if($('#NewPassWord').val()!=$('#EnsurePassWord').val()) layer.msg('新密码与确认密码不一致');
                                                   else{
                                                         $.ajax({
                                                                                                  url:"/AdminEditPassWord",
                                                                                                  type:"post",
                                                                                                  data: {
                                                                                                      "OldPassWord":$('#OldPassWord').val(),
                                                                                                      "NewPassWord":$('#NewPassWord').val(),
                                                                                                      "EnsurePassWord":$('#EnsurePassWord').val(),
                                                                                                      "Account": $('#AccountDisplay',parent.document).text()
                                                                                                  },
                                                                                                  traditional:true,  //阻止深度序列化
                                                                                                  dataType:"json",
                                                                                                  async: true,
                                                                                                  success:function(data){
                                                                                                      if(data == "200"){
                                                                                                          //更新表单
                                                                                                          layer.msg("修改成功!");
                                                                                                      }
                                                                                                      else if(data == '201'){
                                                                                                          layer.msg('旧密码不正确');
                                                                                                      }
                                                                                                      else  layer.msg("修改失败!");
                                                                                                  }
                                                                                              });//ajax
                                                                                              //自动关闭
                                                    layer.close(index);
                                                    document.getElementById("ProfilePage").style.display="none";  //隐藏弹出层
                                                   }  //esle

                                   			   }, //yes
                                   			 cancel:function(index,layero){
                                   				 layer.msg('取消');
                                   				  //自动关闭
                                                  layer.close(index);
                                                   document.getElementById("ProfilePage").style.display="none";  //隐藏弹出层
                                   			 } //cancel
                              });  //open
                });// Profile
});


//
