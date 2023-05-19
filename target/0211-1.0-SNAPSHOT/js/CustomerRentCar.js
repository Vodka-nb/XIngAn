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


//layuiJS 
// 数据渲染
layui.use(['table','jquery','laydate'],function(){
    var table = layui.table;
    var $ = layui.jquery;
    var laydate = layui.laydate;

    //日期时间选择器
    laydate.render({
        elem: '#RentCarTime'
        ,type: 'datetime'
    });

    //日期时间选择器
    laydate.render({
        elem: '#ReturnCarTime'
        ,type: 'datetime'
    });

    //动态渲染车辆信息表
    table.render({
        elem: '#CarInfo'
        ,url: '/RentCarTable' //数据接口
        ,page: true //开启分页
        ,method: 'post'
        ,width: 1500
        ,height: 590 //设置高度
        ,limit: 8
        ,where:{
          "CustomerName":  $("#CustomerName",parent.document).text() ,  //iframe获取外部的值，传参到后台
          "CustomerAccount":  $("#AccountDisplay",parent.document).text()  //iframe获取外部的值，传参到后台
        }
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
            {field: 'carSize', title: '座位数', width:100},
            {field: 'purchaseDate', title: '购买日期', width:150, sort: true},
            {field: 'purchasePrice', title: '购买价格', width:100 ,sort: true},
            {field: 'carDetail', title: '车辆简介', width:121},
            {field: 'remark', title: '备注', width:101},
            {field: 'imgUrl', title : '图片' , align:"center", width:170 ,templet:function (v) {
              return '<img src="'+v.imgUrl+'" onclick="show_img(this)"/>'}},
            {field: 'rentWay', title : '租赁方式' , width:100 },
            {field: 'rentPrice', title: '租赁价格', width:123,sort: true},
            {field: 'Operation' , title:'订购', width:160, toolbar:'#toolsBar'}
        ]],
         toolbar: '#toolbarDemo',//开启自定义工具行，指向自定义工具栏模板选择器
         defaultToolbar: ['filter', 'print', 'exports']
    });

    //监听头部工具栏
    table.on('toolbar(RentCarFilter)', function (obj) {
        switch (obj.event) {
            case 'getRefresh':
                table.reload('CarInfo');
            break;
        }
    });

    //顾客订购车辆
    table.on('tool(RentCarFilter)',function (obj){
        var data = obj.data; //获得当前行数据
        var jsonData = JSON.stringify(data);

        //顾客到店提车时，更改的信息
        switch(obj.event){
            // 1.订购
            case 'CustomerOrder':

              if($("#CustomerName",parent.document).text()=="" || $("#AccountDisplay",parent.document).text() == "") layer.msg("请先登录");
              else if(obj.data.remark == '维修中') layer.msg('该车辆正在维修');
              else if(obj.data.remark == '已租赁') layer.msg('该车辆已租赁');
              else{
                 layer.open({

                                    //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                                    type: 1,
                                    title: "租车账单处理",
                                    area: ['520px', '530px'],
                                    offset: ['100px', '200px'],
                                    btn: ['保存','取消'],
                                    content: $("#ProcessPage"),//引用的弹出层的页面层的方式加载修改界面表单
                                    success: function(index,layero){
                                        //父层传数据至弹出层
                                    },
                                    yes: function(index,layero){
                                          if($('#RentCarTime').val()=="") layer.msg("租车时间不能为空！");
                                          else if($('#ReturnCarTime').val()=="") layer.msg("还车时间不能为空！");
                                          else if($('#RentCarAddress').val()=="") layer.msg("租车地点不能为空！");
                                          else if($('#CustomerRentWay').val()=="") layer.msg("租车方式不能为空！");
                                        else{
                                            //通过ajax刷新后台，成功后，再刷新表单
                                                                                   $.ajax({
                                                                                       url:"/CustomerPurchase",
                                                                                       type:"post",
                                                                                       data: {
                                                                                           "rentCarTime":$('#RentCarTime').val(),
                                                                                           "returnCarTime":$('#ReturnCarTime').val(),
                                                                                           "rentCarAddress":$('#RentCarAddress').val(),
                                                                                           "phoneNum": $('#AccountDisplay',parent.document).text(),
                                                                                           "carType" : obj.data.carType,
                                                                                           "rentWay" : obj.data.rentWay,
                                                                                           "carBrand": obj.data.carBrand,
                                                                                           "rentPrice": obj.data.rentPrice,
                                                                                           "carSign": obj.data.carSign,
                                                                                           "customerRentWay": $('#CustomerRentWay').val()
                                                                                       },
                                                                                       traditional:true,  //阻止深度序列化
                                                                                       dataType:"json",
                                                                                       async: true,
                                                                                       success:function(data){
                                                                                       layer.msg(data);
                                                                                           if(data == "200" ){
                                                                                              layer.msg("订购成功!");
                                                                                              table.reload('CarInfo');
                                                                                           }
                                                                                           else  layer.msg("修改失败!");
                                                                                       }
                                                                                   });
                                                                                   //自动关闭
                                                                                   layer.close(index);
                                                                                   document.getElementById("ProcessPage").style.display="none";  //隐藏弹出层
                                        } //else

                                    },
                                    cancel:function(index,layero){
                                        layer.msg('取消');
                                    }
                                });
              }
            break;

        }
    });

    //表格搜索
     //以下是搜索框进行监测
     var active = {
            	  reload: function(){
            	      var CarSignInfo = $('#IDSearch').val();	//得到搜索框里已输入的车牌号
            	      var CarTypeInfo = $('#CarTypeSearch').val();	//得到搜索框里已输入的车型
            	      var RentSolutionInfo = $('#RentSolutionSearch').val();	//得到搜索框里已输入的租赁状况
            	      console.log(CarSignInfo);
            	      //执行重载
            	      table.reload('CarInfo', {
            	      url: '/SearchCar',
            	        page: {
            	          curr: 1 //重新从第 1 页开始
            	        },
            	        where: {
            	          carSign:  CarSignInfo	,
            	          carType:  CarTypeInfo	,
            	          remark:  RentSolutionInfo	,
            	        }
            	      });
            	    }
      };  //var active

     $('#btn .layui-btn').on('click', function(){
       var type = $(this).data('type');
       active[type] ? active[type].call(this) : '';
     });
});


$('#AddCarInfo').on('click',function(){
  
});