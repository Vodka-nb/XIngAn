layui.use(['table','jquery','laydate'],function(){
  var table = layui.table;
  var $ = layui.jquery;
  var upload = layui.upload;
   var laydate = layui.laydate;

   //日期时间选择器
      laydate.render({
          elem: '#AddPurchaseDateInfo'
          ,type: 'datetime'
      });

  //动态渲染车辆信息表
    table.render({
        elem: '#CarInfo'
        ,url: '/CarTable' //数据接口
        ,page: true //开启分页
        ,method: 'post'
        ,width: 1550
        ,height: 640 //设置高度
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
            {field: 'carType', title: '车型', width:100,sort: true},
            {field: 'carBrand', title: '品牌', width:100,sort: true},
            {field: 'carSign', title: '车牌号', width:100,sort: true},
            {field: 'carSize', title: '座位数', width:50},
            {field: 'purchaseDate', title: '购买日期', width:170, sort: true},
            {field: 'purchasePrice', title: '购买价格/万)', width:90 ,sort: true},
            {field: 'carDetail', title: '车辆简介', width:140},
            {field: 'remark', title: '备注', width:100,sort: true},
            {field: 'imgUrl', title : '图片' , align:"center", width:150 ,
            templet:function (v) {
                   return '<img src="'+v.imgUrl+'" onclick="show_img(this)"/>'
            }},
            {field: 'rentWay', title:'租赁方式', width:100},
            {field: 'rentPrice', title:'租赁价格/元', width:90 },
            {title:'操作', width:120, toolbar:'#toolsBar' }
        ]],
        toolbar: '#toolbarDemo',//开启自定义工具行，指向自定义工具栏模板选择器
        defaultToolbar: ['filter', 'print', 'exports']
    });

    //编辑车辆信息(修改，删除)
    table.on('tool(CarFilter)',function (obj){
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的DOM对象
        var jsonData = JSON.stringify(data);

        //顾客到店提车时，更改的信息
        switch(obj.event){
            // 1.编辑订单
            case 'edit':
             if($("#SuperName",parent.document).text()=="" || $("#AccountDisplay",parent.document).text() == "") layer.msg("请先登录");
               else{
                layer.open({
                                 //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                                 type: 1,
                                 title: "车辆信息管理",
                                 area: ['420px', '330px'],
                                 btn: ['保存','取消'],
                                  offset: ['100px', '400px'],
                                 content: $("#EditCar"),//引用的弹出层的页面层的方式加载修改界面表单
                                 success: function(index,layero){
                                     //父层传数据至弹出层
                                     $('#CarTypeInfo').val(obj.data.carType);
                                     $('#CarBrandInfos').val(obj.data.carBrand);
                                     $('#CarSignInfo').val(obj.data.carSign);
                                     $('#CarSizeInfo').val(obj.data.carSize);
                                     $('#PurchaseDateInfo').val(obj.data.purchaseDate);
                                     $('#PurchasePriceInfo').val(obj.data.purchasePrice);
                                     $('#CarDetailInfo').val(obj.data.carDetail);
                                     $('#RemarkInfo').val(obj.data.remark);
                                     $('#RentWayInfo').val(obj.data.rentWay);
                                     $('#RentPriceInfo').val(obj.data.rentPrice);
                                 },
                                 yes: function(index,layero){
                                   if($('#CarTypeInfo').val()=="") layer.msg("车型不能为空！");
                                   else if($('#CarBrandInfos').val()=="") layer.msg("品牌不能为空！");
                                   else if($('#CarSignInfo').val()=="") layer.msg("车牌号不能为空！");
                                   else if($('#CarSizeInfo').val()=="") layer.msg("座位数不能为空！");
                                   else if($('#PurchaseDateInfo').val()=="") layer.msg("购买日期不能为空！");
                                   else if($('#PurchasePriceInfo').val()=="") layer.msg("购买价格不能为空！");
                                   else if($('#CarDetailInfo').val()=="") layer.msg("车辆详情不能为空！");
                                   else if($('#RemarkInfo').val()=="") layer.msg("备注不能为空！");
                                   else if($('#RentWayInfo').val()=="") layer.msg("租赁方式不能为空！");
                                   else if($('#RentPriceInfo').val()=="") layer.msg("租赁价格不能为空！");
                                   else {
                                        //编辑表单，通过ajax刷新后台，成功后，再刷新表单
                                                             $.ajax({
                                                                 url:"/EditCar",
                                                                 type:"post",
                                                                 data: {
                                                                     "CarType": $('#CarTypeInfo').val(),
                                                                     "CarBrand": $('#CarBrandInfos').val(),
                                                                     "CarSign": $('#CarSignInfo').val(),
                                                                     "CarSize": $('#CarSizeInfo').val(),
                                                                     "PurchaseDate": $('#PurchaseDateInfo').val(),
                                                                     "PurchasePrice": $('#PurchasePriceInfo').val(),
                                                                     "CarDetail": $('#CarDetailInfo').val(),
                                                                     "Remark": $('#RemarkInfo').val(),
                                                                     "RentWay": $('#RentWayInfo').val(),
                                                                     "RentPrice": $('#RentPriceInfo').val(),
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
                                                             });
                                                             //自动关闭
                                                             document.getElementById("EditCar").style.display="none";  //隐藏弹出层
                                                             layer.close(index);
                                   } //else
                                 },  //yes
                                 cancel:function(index,layero){
                                 }
                             });
               } //else
             break;

            // 2.删除账本条目
            case 'del':
             if($("#SuperName",parent.document).text()=="" || $("#AccountDisplay",parent.document).text() == "") layer.msg("请先登录");
             else{
             //ajax通信,删除数据库相关数据
                             var msg = confirm("确定删除该车辆？");
                             if(msg == true ) {
                                  $.ajax({
                                      url:"/DeleteCar",
                                      type:"post",
                                      data: {
                                          "CarSign": obj.data.carSign,
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

   // 图片上传函数
   var uploadInst = upload.render({
                          elem: '#submitButton'
              //            ,url: 'https://httpbin.org/post' //此处用的是第三方的 http 请求演示，实际使用时改成您自己的上传接口即可。
                          ,before: function(obj){
                            //预读本地文件示例，不支持ie8
                            obj.preview(function(index, file, result){
                              $('#uploadImg').attr('src', result); //图片链接（base64）
                              console.log(result);
                            });
                            layer.msg('上传中', {icon: 16, time: 0});
                          }
                          ,done: function(res){
                            //如果上传失败
                            if(res.code > 0){
                              return layer.msg('上传失败');
                            }
                            else layer.msg('上传成功');
                          }
   });   //uploadInst

   //监听头部工具栏(添加车辆，刷新车辆信息表）
    table.on('toolbar(CarFilter)', function (obj) {
        switch (obj.event) {
        	case 'getInsert':
        	 if($("#SuperName",parent.document).text()=="" || $("#AccountDisplay",parent.document).text() == "") layer.msg("请先登录");
        	 else{
        	     //                添加车辆信息
                                layer.open({
                                         //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                                           type: 1,
                                           title: "添加车辆",
                                           area: ['420px', '330px'],
                                           offset: ['100px', '400px'],
                                           btn: ['保存','取消'],
                                           content: $("#AddCar"),//引用的弹出层的页面层的方式加载修改界面表单
                                           yes: function(index,layero){
                                              //通过ajax刷新后台，成功后，再刷新表单
                                               $.ajax({
                                                   url:"/AddCar",
                                                   type:"post",
                                                   data: {
                                                        'CarType': $('#AddCarTypeInfo').val(),
                                                        'CarBrand': $('#AddCarBrandInfos').val(),
                                                        'CarSize': $('#AddCarSizeInfo').val(),
                                                        'CarSign': $('#AddCarSignInfo').val(),
                                                        'PurchaseDate' : $('#AddPurchaseDateInfo').val(),
                                                        'PurchasePrice' : $('#AddPurchasePriceInfo').val(),
                                                        'CarDetail': $('#AddCarDetailInfo').val(),
                                                        'Remark': $('#AddRemarkInfo').val(),
                                                        'RentWay': $('#AddRentWayInfo').val(),
                                                        'RentPrice' : $('#AddRentPriceInfo').val(),
                                                        'ImgName' : $('#ImgNameInfo').val(),
                                                        'ImgBase64': $('#uploadImg').attr('src')
                                                   },
                                                   dataType:"json",
                                                   async: true,
                                                   success:function(data){
                                                       if(data == 200) {
                                                           layer.msg("添加成功!");
                                                       }
                                                       else  layer.msg("添加失败!");
                                                   }
                                               });
                                             //重载表单

                                              //自动关闭
                                              layer.close(index);
                                           },
                                           cancel:function(index,layero){
                                             layer.msg('取消')
                                           }
                                       });  //layer.open

        	 }

                break;
            case 'getRefresh':
                location.reload();
                break;
        	}
    });
}); //use

//局部页面跳转
$(function(){
    //点击相应选项，切换相应的动态画面
    $(".FunctionBar").on("click",function(){
        var address =$(this).attr("data-src");
        $("iframe").attr("src",address);
    });
});


 // 点击表格图片放大
 function show_img(t) {
    //页面层
    layer.open({
        type: 1,
        skin: 'layui-layer-rim', //加上边框
        area: ['1050px', '550px'], //宽高 t.width() t.height()
         offset: ['100px', '200px'],
        shadeClose: true, //开启遮罩关闭
        end: function (index, layero) {
            return false;
        },
        content: '<img style="width:1040px; height: 550px;"  src="'+$(t).attr('src')+'">'
    });
 }