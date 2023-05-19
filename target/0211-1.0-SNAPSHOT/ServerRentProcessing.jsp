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
<!-- 订单处理，处理用户的订单，给客户租赁号码，车牌号，账单详情，总金额 -->
<div id="OrderProcessingBox">
	<!-- 车辆数据信息 -->
	<table id="ProcessRent" lay-filter="OrderProcessing"></table>
</div>
</body>

<%-- 数据表头部工具栏 --%>
<script type="text/html" id="toolbarDemo">
	<div class="layui-btn-container">
		<button class="layui-btn layui-btn-sm" lay-event="getRefresh">刷新</button>
	</div>
</script>

<!-- /这里是弹出层的表单信息
    //表单的id用于表单的选择，style是在本页隐藏，只有点击编辑才会弹出 -->
<div class="layui-row" id="ProcessPage" >
	<div class="layui-col-md10" >
		<form class="layui-form layui-from-pane" action="" style="margin-top:20px" >

			<div class="layui-form-item">
				<label class="layui-form-label">操作员</label>
				<div class="layui-input-block">
					<input type="text" name="neweqptIdCode" id="Operator" required  lay-verify="required" autocomplete="off" placeholder="账单操作员" class="layui-input">
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">备注</label>
				<div class="layui-input-block">
					<input type="text" name="neweqptName"  id="Remark" required  lay-verify="required" autocomplete="off" placeholder="账单备注" class="layui-input">
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">押金</label>
				<div class="layui-input-block">
					<input type="text" name="neweqptName"  id="Deposit" required  lay-verify="required" autocomplete="off" placeholder="押金" class="layui-input">
				</div>
			</div>

			<div class="layui-form-item">
				<label class="layui-form-label">租金</label>
				<div class="layui-input-block">
					<input type="text" name="neweqptName"  id="RentMoney" required  lay-verify="required" autocomplete="off" placeholder="租金" class="layui-input">
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