$(function(){
    //点击相应选项，切换相应的动态画面
      $(".FunctionBar").on("click",function(){
          var address =$(this).attr("data-src");
          $("iframe").attr("src",address);
      });
});

layui.use(['jquery'],function(){
    var table = layui.table;
    var $ = layui.jquery;

    $.ajax({
        url:"/GetCommentContent",
        type:"post",
        data: {
            "Identity": 666,
        },
        dataType:"json",
        async: true,
        //将后端传回的数据，用于渲染评论区
        success:function(data){
            console.log(data);
        }
    });

});


    