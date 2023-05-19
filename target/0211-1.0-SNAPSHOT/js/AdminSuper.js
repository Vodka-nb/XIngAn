$(function(){
    //点击相应选项，切换相应的动态画面
    $(".FunctionBar").on("click",function(){
        var address =$(this).attr("data-src");
        $("iframe").attr("src",address);
    });

});

layui.use(['table'],function(){
    var table = layui.table;

  //动态数据渲染
    table.render({
        elem: '#SuperInfo'
        ,height: 312
        ,url: '/AdminTable' //数据接口
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
        ,cols: [[ //表头,这里的filed名称要与后端传回的数据名称一致
            {field: 'superAccount',fixed:'left', title: '管理员账号', width:160, sort: true}
            ,{field: 'nickName', title: '昵称', width:180,sort: true}
            ,{field: 'phone', title: '联系方式', width:134,sort: true}
            ,{field: 'gender', title: '性别', width:160}
            ,{field: 'remark', title: '备注', width:160, sort: true}
            ,{field: 'Operation' , title:'操作', width:200, toolbar:'#toolsBar' }
        ]],
        toolbar: '#toolbarDemo',//开启自定义工具行，指向自定义工具栏模板选择器
        defaultToolbar: ['filter', 'print', 'exports']
    });

    //编辑管理员信息
    table.on('tool(SuperFilter)',function (obj){
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象
        var jsonData = JSON.stringify(data);

        //管理员更改的信息
        switch(obj.event){
            // 1.编辑订单
//            case 'edit':
//                layer.open({
//                    //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
//                    type: 1,
//                    title: "管理员信息",
//                    area: ['420px', '330px'],
//                    offset: ['100px', '400px'],
//                    btn: ['保存','取消'],
//                    content: $("#EditSuper"),//引用的弹出层的页面层的方式加载修改界面表单
//                    success: function(index,layero){
//                        //父层传数据至弹出层
//                        $('#NickNameInfo').val(obj.data.nickName);
//                        $('#GenderInfo').val(obj.data.gender);
//                        $('#RemarkInfos').val(obj.data.remark);
//                        $('#PhoneInfos').val(obj.data.phone);
//                    },
//                    yes: function(index,layero){
//                        //通过ajax刷新后台，成功后，再刷新表单
//                        //获取请求数据
//                        var Admindata = {
//                            nickName:$('#NickNameInfo').val(),
//                            gender:$('#GenderInfo').val(),
//                            remark:$('#RemarkInfos').val(),
//                        }
//                         console.log(JSON.stringify(Admindata));
//                        //ajax发送修改请求
//                        $.ajax({
//                            url:"/EditAdminInfo",
//                            type:"post",
//                            data: {
//                                "nickName":$('#NickNameInfo').val(),
//                                "gender":$('#GenderInfo').val(),
//                                "remark":$('#RemarkInfos').val(),
//                                "phone":obj.data.phone,
//                            },
//                            dataType:"json",
//                            async: true,
//                            success:function(data){
//                                if(data == 200) layer.msg("修改成功!");
//                                else  layer.msg("修改失败!");
//                            }
//                        });
//                        //更新表单
//                        obj.update({
//                            nickName:$('#NickNameInfo').val(),
//                            gender:$('#GenderInfo').val(),
//                            remark:$('#RemarkInfos').val(),
//
//                        });
//                        //自动关闭
//                        layer.close(index);
//                        document.getElementById("EditSuper").style.display="none";
//                    },
//                    cancel:function(index,layero){
//                        layer.msg('取消')
//                    }
//                });
//            break;

            // 2.删除账本条目
            case 'del':
             if($("#SuperName",parent.document).text()=="" || $("#AccountDisplay",parent.document).text() == "") layer.msg("请先登录");
             else{
                   //ajax通信,删除数据库相关数据
                                var msg = confirm("确定删除管理员？");
                                if(msg == true ) {
                                 $.ajax({
                                     url:"/DeleteAdminInfo",
                                     type:"post",
                                     data: {
                                         "account": data.account,
                                         "phone": data.phone,
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
             }//else

            break;
        }
    });

 //监听管理员信息表头部工具栏
  table.on('toolbar(SuperFilter)', function (obj) {
      var checkStatus = table.checkStatus(obj.config.id);
      switch (obj.event) {
//      新增管理员
      	case 'getInsert':
      	 if($("#SuperName",parent.document).text()=="" || $("#AccountDisplay",parent.document).text() == "") layer.msg("请先登录");
      	 else{
      	 //              弹出层
                       layer.open({
                                           //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                                           type: 1,
                                           title: "添加管理员",
                                           area: ['420px', '330px'],
                                           btn: ['保存','取消'],
                                           offset: ['100px', '400px'],
                                           content: $("#AddSuper"),//引用的弹出层的页面层的方式加载修改界面表单
                                           success: function(index,layero){

                                           },
                                           yes: function(index,layero){
                                             //通过ajax把新的管理员信息发送至后台，成功后，再返回提示
                                             //监听表单
                                             if($('#SuperAccountInfo').val()=='') layer.msg("账号不能为空!");
                                             else if($('#SuperPhoneInfo').val()=='') layer.msg("联系方式不能为空!");
                                             else if($('#SuperNameInfo').val()=='') layer.msg("姓名不能为空!");
                                             else if($('#SuperPassWordInfo').val()=='') layer.msg("密码不能为空!");
                                             else if($('#CertifySuperPassWordInfo').val()=='') layer.msg("请确认密码!");
                                             else if($('#SuperGenderInfo').val()=='') layer.msg("性别不能为空!");
                                             else if($('#SuperRemarkInfo').val()=='') layer.msg("备注不能为空!");

                                             //若两次密码一样，则发送ajax
                                             else if($('#SuperPassWordInfo').val() != $('#CertifySuperPassWordInfo').val()) layer.msg("两次密码不一致!");
                                             else{
                                               //ajax发送修改请求
                                               $.ajax({
                                                   url:"/AddAdminInfo",
                                                   type:"post",
                                                   data: {
                                                       "nickName":$('#SuperNameInfo').val(),
                                                       "account":$('#SuperAccountInfo').val(),
                                                       "passWordOne":$('#SuperPassWordInfo').val(),
                                                       "passWordTwo":$('#CertifySuperPassWordInfo').val(),
                                                       "gender":$('#SuperGenderInfo').val(),
                                                       "remark":$('#SuperRemarkInfo').val(),
                                                       "phone":$('#SuperPhoneInfo').val(),
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
                                               document.getElementById("EditSuper").style.display="none";
                                              } //else
                                           },
                                           cancel:function(index,layero){
                                               layer.msg('取消')
                                           }
                                       });
      	 }

              break;

          case 'getRefresh':
              location.reload();
              break;
      	}
  });

})
