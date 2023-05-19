<%@page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>租车管理</title>
  <link rel="stylesheet" href="layui/css/layui.css">
  <link rel="stylesheet" href="css/ServerRentProcessing.css  ">
  
</head>
 <body>
 <!-- 搜索框 -->
 <fieldset class="table-search-fieldset">
     <legend>管理员信息</legend>
     <div style="margin: 10px 10px 10px 10px" id="btn">
         <form class="layui-form layui-form-pane" action="">
             <div class="layui-form-item">
                 <div class="layui-inline">
                     <label class="layui-form-label">ID查询</label>
                     <div class="layui-input-inline">
                         <!--注意此处input标签里的id-->
                         <input class="layui-input" name="keyword" id="IDSearch" autocomplete="off">
                     </div>
                 </div>

                 <div class="layui-inline">
                     <label class="layui-form-label">昵称查询</label>
                     <div class="layui-input-inline">
                         <!--注意此处input标签里的id-->
                         <input class="layui-input" name="keyword" id="NickNameSearch" autocomplete="off">
                     </div>
                 </div>

                 <div class="layui-inline">
                     <!--注意此处button标签里的type属性-->
                     <button type="button" class="layui-btn layui-btn-primary"  data-type="reload" lay-filter="data-search-btn">
                         <i class="layui-icon"></i> 搜 索
                     </button>
                 </div>
             </div>
         </form>
     </div>
 </fieldset>
     <!-- 订单处理，处理用户的订单，给客户租赁号码，车牌号，账单详情，总金额 -->
    <div id="OrderProcessingBox">
        <!-- 车辆数据信息 -->
        <table id="ProcessOrder" lay-filter="OrderProcessing"></table>
    </div>
 </body>
 
 <!-- /这里是弹出层的表单信息
 	//表单的id用于表单的选择，style是在本页隐藏，只有点击编辑才会弹出 -->
 	<div class="layui-row" id="ProcessPage" >
     <div class="layui-col-md10" >
         <form class="layui-form layui-from-pane" action="" style="margin-top:20px" >
             
             <div class="layui-form-item">
                 <label class="layui-form-label">租赁ID</label>
                 <div class="layui-input-block">
                     <input type="text" name="neweqptIdCode" id="RentIdInfo" required  lay-verify="required" autocomplete="off" placeholder="请输入用户唯一的租赁ID" class="layui-input">
                 </div>
             </div>
             <div class="layui-form-item">
                 <label class="layui-form-label">车牌号</label>
                 <div class="layui-input-block">
                     <input type="text" name="neweqptName"  id="CarIdInfo" required  lay-verify="required" autocomplete="off" placeholder="请输入用户车牌号" class="layui-input">
                 </div>
             </div>
 
         
         </form>
     </div>
 </div>
 
 
<!-- 数据表操作按钮 -->
<script type="text/html" id="ProcessingFunction">
  <a class="layui-btn layui-btn-mini" lay-event="EditOrder" >编辑</a>
  <a class="layui-btn layui-btn-danger" lay-event="DeleteOrder" >删除</a>
</script> 
 <script src="js/jquery-3.5.1.js"></script>
 <script src="layui/layui.js"></script>
 <script src="js/ServerRentProcessing.js"></script>
</html>