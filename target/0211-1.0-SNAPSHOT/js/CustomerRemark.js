layui.use(['layedit','jquery'], function(){
    var layedit = layui.layedit,
     $ = layui.jquery;
    //评论区头部工具栏

    //获取意见内容
    $("#Print").click(function(){
    if($("#CustomerName",parent.document).text()=="" || $("#AccountDisplay",parent.document).text() == "") layer.msg("请先登录");
    else{

         //通过ajax把用户评论传到后台，成功后，返回提示
         $.ajax({
             url:"/CustomerRemark",
             type:"post",
             data: {
                 "RemarkContent":$('#Review').val(),
                 "OrderId":$('#OrderId').val(),
                 "CustomerAccount":$("#AccountDisplay",parent.document).text()
             },
             traditional:true,  //阻止深度序列化
             dataType:"json",
             async: true,
             success:function(data){
                 if(data == 200){
                     layer.msg("发表成功!");
                 }
                 else  layer.msg("发表失败!");
             }
         });
    }

    })
 });

