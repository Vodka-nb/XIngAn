<%@page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>服务</title>
    <link rel="stylesheet" href="layui/css/layui.css">
    <link rel="stylesheet" href="css/ServerReturnBillProcessing.css  ">

</head>
<body>
<!-- 还车处理，处理用户的订单，给客户租赁号码，车牌号，账单详情，总金额 -->
<div id="ReturnCarProcessingBox">
    <!-- 车辆数据信息 -->
    <table id="ProcessReturnBill" lay-filter="ReturnCarProcessing"></table>

</div>
</body>

<!-- /这里是弹出层的表单信息
	//表单的id用于表单的选择，style是在本页隐藏，只有点击编辑才会弹出 -->
<div class="layui-row" id="ProcessPage" >
    <div class="layui-col-md10" >
        <form class="layui-form layui-from-pane" action="" style="margin-top:20px" >

            <div class="layui-form-item">
                <label class="layui-form-label">赔偿金额</label>
                <div class="layui-input-block">
                    <input type="text" name="neweqptIdCode" id="PenaltyInfo" required  lay-verify="required" autocomplete="off" placeholder="赔偿金额" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">车况评估</label>
                <div class="layui-input-block">
                    <input type="text" name="neweqptName"  id="EvaluateCarInfo" required  lay-verify="required" autocomplete="off" placeholder="车况评估" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">操作员编号</label>
                <div class="layui-input-block">
                    <input type="text" name="neweqptName"  id="OperatorId" required  lay-verify="required" autocomplete="off" placeholder="操作员编号" class="layui-input">
                </div>
            </div>

        </form>
    </div>
</div>

<!-- 数据表操作按钮 -->
<script type="text/html" id="ProcessingReturnBillFunction">
    <a class="layui-btn layui-btn-mini" lay-event="EditReturnOrder" >编辑</a>
    <a class="layui-btn layui-btn-danger" lay-event="DeleteReturnOrder" >删除</a>
</script>
</body>
<script src="layui/layui.js"></script>
<script src="js/jquery-3.5.1.js"></script>
<script src="js/ServerReturnBillProcessing.js"></script>
</html>