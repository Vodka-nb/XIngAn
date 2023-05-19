//layuiJS 
layui.use(['element', 'layer', 'util','jquery'], function(){
    var element = layui.element
    ,layer = layui.layer
    ,util = layui.util
    ,$ = layui.jquery;
    
    //头部事件
    util.event('lay-header-event', {
      //左侧菜单事件
      menuLeft: function(othis){
        layer.msg('展开左侧菜单的操作', {icon: 0});
      }
      ,menuRight: function(){
        layer.open({
          type: 1
          ,content: '<div style="padding: 15px;">处理右侧面板的操作</div>'
          ,area: ['260px', '100%']
          ,offset: 'rt' //右上角
          ,anim: 5
          ,shadeClose: true
        });
      }
    });

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
                                      if($('#RealName').val()=='') layer.msg('真实姓名不能为空');
                                      else if($('#IDCarType').val()=='') layer.msg('证件类型不能为空');
                                      else if($('#IDCardNum').val()=='') layer.msg('证件类型不能为空');
                                      else if($('#Address').val()=='') layer.msg('常驻地址不能为空');
                                      else{
                                            $.ajax({
                                                                                     url:"/CustomerEditProfile",
                                                                                     type:"post",
                                                                                     data: {
                                                                                         "RealName":$('#RealName').val(),
                                                                                         "IDCardType":$('#IDCardType').val(),
                                                                                         "IDCardNum":$('#IDCardNum').val(),
                                                                                         "Address":$('#Address').val(),
                                                                                         "CustomerAccount": $('#AccountDisplay',parent.document).text()
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
                                                   yes: function(index,layero){
                                                      //通过ajax刷新后台，成功后，再刷新表单
                                                      if($('#OldPassWord').val()=='') layer.msg('旧密码不能为空');
                                                      else if($('#NewPassWord').val()=='') layer.msg('新密码不能为空');
                                                      else if($('#EnsurePassWord').val()=='') layer.msg('确认密码不能为空');
                                                      else if($('#NewPassWord').val()!=$('#EnsurePassWord').val()) layer.msg('新密码与确认密码不一致');
                                                      else{
                                                            $.ajax({
                                                                                                     url:"/CustomerEditPassWord",
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
});
  

//点击相应选项，切换相应的动态画面
  $(".FunctionBar").on("click",function(){
      var address =$(this).attr("data-src");
      $("iframe").attr("src",address);
  });


//