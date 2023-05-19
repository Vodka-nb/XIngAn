//车辆信息表单元素获取
layui.use(['table','jquery'], function(){
  var table = layui.table;
  var $ = layui.jquery;

//动态数据渲染,只获取未审核的评论
table.render({
    elem: '#AuditCommentInfo'
    ,url: '/AuditCommentTable' //数据接口
    ,page: true //开启分页
    ,method: 'post'
    ,width: 1200
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
        {field: 'customerAccount', title: '顾客账号', width:150, sort: true, fixed:'left'},
        {field: 'orderId', title: '账单号', width:100,sort: true},
        {field: 'content', title: '评论内容', width:604},
        {field: 'commentTime', title: '评论时间', width:160},
        {title: '操作', width:  180 , toolbar:'#FunctionBar'}
    ]],
     toolbar: '#toolbarDemo', //开启自定义工具行，指向自定义工具栏模板选择器
     defaultToolbar: ['filter', 'print', 'exports']
});

  //监听头部工具栏
  table.on('toolbar(AuditCommentFilter)', function (obj) {
      var checkStatus = table.checkStatus(obj.config.id);
      switch (obj.event) {
          case 'getRefresh':
              location.reload();
          break;
      	}
  });

  //删除不合法的评论
    table.on('tool(AuditCommentFilter)',function (obj){
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象
        var jsonData = JSON.stringify(data);

        //顾客到店提车时，更改的信息
        switch(obj.event){
            // 审核评论
             case 'through':
             if($("#SuperName",parent.document).text()=="" || $("#AccountDisplay",parent.document).text() == "") layer.msg("请先登录");
             else {
                          var msg = confirm("确定展示该评论？");
                                        if(msg == true ) {
                                          //ajax通信,删除数据库相关数据
                                          $.ajax({
                                              url:"/ThroughComment",
                                              type:"post",
                                              data: {
                                                  "Content": obj.data.content,
                                                  "OrderId": obj.data.orderId,
                                                  "SuperAccount": $("#AccountDisplay",parent.document).text()
                                              },
                                              dataType:"json",
                                              async: true,
                                              success:function(data){
                                                  if(data == 200) {
                                                      table.reload('AuditCommentInfo');
                                                      layer.msg("审核通过!");
                                                  }
                                                  else  layer.msg("审核失败!");
                                              }
                                          });
                                        }
                                        else layer.msg('取消');
                       }//else
             break;


            // 删除评论
            case 'del':
            if($("#SuperName",parent.document).text()=="" || $("#AccountDisplay",parent.document).text() == "") layer.msg("请先登录");
            else {
               var msg = confirm("确定删除该评论？");
                             if(msg == true ) {
                               //ajax通信,删除数据库相关数据
                               $.ajax({
                                   url:"/DeleteComment",
                                   type:"post",
                                   data: {
                                       "Content": obj.data.content,
                                       "OrderId": obj.data.orderId,
                                   },
                                   dataType:"json",
                                   async: true,
                                   success:function(data){
                                       if(data == 200) {
                                           obj.del(); //删除该行
                                           table.reload('AuditCommentInfo');
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