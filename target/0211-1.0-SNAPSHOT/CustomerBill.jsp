<%@page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>顾 客 账 单</title>
  <link rel="stylesheet" href="layui/css/layui.css">
  <link rel="stylesheet" href="css/CustomerBill.css">
</head>

 <body>
    <div id="CustomerBillTable">
           <!-- 1.用户账单信息 ，用户订购之后，去店里提车，适合就可提车，不适合就可取消
               2.这里可缴纳赔偿金
        -->
        <table id="CustomerBillInfo" lay-filter="BillFilter"></table>
    </div>   
 </body>

<!-- /这里是弹出层的表单信息
	//表单的id用于表单的选择，style是在本页隐藏，只有点击编辑才会弹出 -->
<div class="layui-row" id="ProcessPage" >
    <div class="layui-col-md10" >
        <form class="layui-form layui-from-pane" action="" style="margin-top:20px" >
            <div class="layui-form-item">
                <label class="layui-form-label">支付金额</label>
                <div class="layui-input-block">
                    <input type="text" name="neweqptIdCode" id="PaymentInfo" required  lay-verify="required" autocomplete="off" placeholder="金额" class="layui-input">
                </div>
            </div>
        </form>
    </div>
</div>

 <!-- 用户支付账单 -->
  <script type="text/html" id="CustomerPayment">
    <a class="layui-btn layui-btn-mini" lay-event="CustomerPay" >支付</a>
    <a class="layui-btn layui-btn-danger" lay-event="CustomerCancel">取消</a>
  </script>
 <script src="layui/layui.js"></script>
 <script src="js/CustomerBill.js"></script>
</html>