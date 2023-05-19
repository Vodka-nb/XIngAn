$(function(){
    //点击相应选项，切换相应的动态画面
    $(".FunctionBar").on("click",function(){
        var address =$(this).attr("data-src");
        $("iframe").attr("src",address);
    });
});

layui.use(['table','jquery'], function(){
    var table = layui.table;
    var $ = layui.jquery;

    //顾客表格动态数据渲染
    table.render({
        elem: '#UserInfo'
        ,url: '/CustomerTable' //数据接口
        ,page: true //开启分页
        ,method: 'post'
        ,width: 1100
        ,height: 540 //设置高度
        ,limit: 10
        ,parseData:function (res) {   //解析传回的数据
            // 最后返回规定的数据格式
            return {
                "code": res.code, //解析状态
                "msg": res.msg, //解析提示文本
                "count": res.length,
                "data": res.data //解析数据列表
            };
        }
        ,cols: [[ //表头,这里的filed名称要与后端传回的数据名称一致
            {field: 'customerID', title: '用户ID', width:195, sort: true, fixed: 'left'},
            {field: 'realName', title: '真实姓名', color:'red',width:130,sort: true},
            {field: 'iDCardType', title: '证件类型', width:100},
            {field: 'iDCardNum', title: '证件号码', width:180, sort: true},
            {field: 'phone', title: '联系电话', width:130 ,sort: true},
            {field: 'address', title: '常用地址', width:200 ,sort: true},
            {field: 'Operation' , title : '操作', width : 160, toolbar:'#FunctionBar'}
        ]]

    });

    //利用弹出层修改顾客信息
    table.on('tool(CustomerFilter)',function (obj){
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
                                 title: "顾客信息处理",
                                 area: ['520px', '430px'],
                                 btn: ['保存','取消'],
                                 content: $("#ProcessPage"),//引用的弹出层的页面层的方式加载修改界面表单
                                 success: function(index,layero){
                                     //父层传数据至弹出层
                                     $('#CustomerIDInfo').val(obj.data.customerID);
                                     $('#CustomerRealNameInfo').val(obj.data.realName);
                                     $('#CustomerIDCardType').val(obj.data.iDCardType);
                                     $('#CustomerIDCardNumInfo').val(obj.data.iDCardNum);
                                     $('#CustomerPhoneInfos').val(obj.data.phone);
                                     $('#CustomerRemarkInfo').val(obj.data.customerRemark);
                                 },
                                 yes: function(index,layero){
                                     layer.msg("ajax");
                                     //通过ajax刷新后台，成功后，再刷新表单
                                     $.ajax({
                                         url:"/EditCustomerInfo",
                                         type:"post",
                                         data: {
                                             "customerID":$('#CustomerIdInfo').val(),
                                             "iDCardType":$('#CustomerIDCardType').val(),
                                             "iDCardNum":$('#CustomerIDCardTypeNum').val(),
                                             "phone":$('#CustomerPhoneInfos').val(),
                                             "realName":$('#CustomerRealNameInfo').val(),
                                             "customerRemark":$('#CustomerRemarkInfo').val(),
                                         },
                                         traditional:true,  //阻止深度序列化
                                         dataType:"json",
                                         async: true,
                                         success:function(data){
                                             if(data == 200){
                                                 //更新表单
                                                 obj.update({
                                                     customerID:$('#CustomerIdInfo').val(),
                                                     customerCardType:$('#CustomerIDCardType').val(),
                                                     customerCardTypeNum:$('#CustomerIDCardTypeNum').val(),
                                                     customerPhone:$('#CustomerPhoneInfos').val(),
                                                     customerRealName:$('#CustomerRealNameInfo').val(),
                                                     customerRemark:$('#CustomerRemarkInfo').val(),
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
             }

                break;

            // 2.删除用户信息
            case 'del':
                //ajax通信,删除数据库相关数据
                 if($("#SuperName",parent.document).text()=="" || $("#AccountDisplay",parent.document).text() == "") layer.msg("请先登录");
                 else{
                 var msg = confirm("确定删除该用户数据？");
                                 if(msg == true ) {
                                 $.ajax({
                                     url:"/DeleteCustomerInfo",
                                     type:"post",
                                     data: {
                                         "CustomerID": data.customerID,
                                         "Phone": data.phone,
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
                                 else{
                                    layer.msg('取消');
                                 }
                 }//else

                break;
        }
    });   //table.on

    //表格搜索
         //以下是搜索框进行监测
         var active = {
                	  reload: function(){
                	      var UserIDInfo = $('#IDSearch').val();	//得到搜索框里已输入的用户ID
                	      var  NickNameInfo= $('#NickNameSearch').val();	//得到搜索框里昵称

                	      //执行重载
                	      table.reload('UserInfo', {
                	      url: '/SearchUser',
                	        page: {
                	          curr: 1 //重新从第 1 页开始
                	        },
                	        where: {
                	          customerID:  UserIDInfo	,
                	          realName:  NickNameInfo

                	        }
                	      });
                	    }
          };  //var active

         $('#btn .layui-btn').on('click', function(){
           var type = $(this).data('type');
           active[type] ? active[type].call(this) : '';
         });
});