<%@page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>顾 客 还 车</title>
  <link rel="stylesheet" href="layui/css/layui.css">
  <link rel="stylesheet" href="css/CustomerReturnCar.css">
</head>

 <body>
  <div  id="ReturnCarDisplay">
    <!-- 车辆数据信息 -->
    <table id="ReturnCar" lay-filter="CustomerReturnCar"></table>
  </div>
 </body>
 
 <!-- /这里是弹出层的表单信息
 //表单的id用于表单的选择，style是在本页隐藏，只有点击编辑才会弹出 -->
 <div class="layui-row" id="ProcessPage" style="display:none;">
     <div class="layui-col-md10">
         <form class="layui-form layui-from-pane" action="" style="margin-top:20px;" >

             <div class="layui-form-item">
                 <label class="layui-form-label">归还地点</label>
                 <div class="layui-input-block">
                     <input type="text" name="neweqptIdCode" id="ReturnAddressInfo" required  lay-verify="required" autocomplete="off" placeholder="输入还车城市" class="layui-input">
                 </div>
             </div>

			 <div class="layui-form-item">
                 <label class="layui-form-label">租车账单编号</label>
                 <div class="layui-input-block">
                     <input type="text" name="neweqptIdCode" id="RentIdInfo" required  lay-verify="required" autocomplete="off" placeholder="租车账单编号" class="layui-input"  class="layui-input"  disabled readonly class="layui-input layui-disabled" >
                 </div>
             </div>

             <div class="layui-form-item">
                 <label class="layui-form-label">还车账单编号</label>
                 <div class="layui-input-block">
                     <input type="text" name="neweqptIdCode" id="ReturnIdInfo" required  lay-verify="required" autocomplete="off" placeholder="还车账单编号" class="layui-input"  disabled readonly class="layui-input layui-disabled" >
                 </div>
             </div>

         </form>
     </div>
 </div>
 
<!-- 归还处理 -->
<script type="text/html"  id="ReturnFunction">
  <a class="layui-btn layui-btn-normal" lay-event="CustomerReturnCarProcess" >归还</a>
  <a class="layui-btn layui-btn-danger" lay-event="CustomerPayPenalty" >缴纳</a>
</script>

<%--头部工具栏--%>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="getRefresh">刷新</button>
    </div>
</script>

<script src="layui/layui.js"></script>
 <script src="js/CustomerReturnCar.js"></script>
</html>