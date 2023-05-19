<%--
  Created by IntelliJ IDEA.
  User: 伟神
  Date: 2023/3/28
  Time: 13:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>顾客租车订单</title>
    <link rel="stylesheet" href="layui/css/layui.css">
    <link rel="stylesheet" href="css/CustomerRentOrder.css  ">
</head>
<body>

<%--      租车账单界面--%>
<div id="CustomerRentOrderTable">
    <!-- 搜索框 -->
<%--    <fieldset class="table-search-fieldset" id="SearchBar">--%>
<%--        <legend>顾客租车订单</legend>--%>
<%--        <div style="margin: 10px 10px 10px 10px; display: flex; align-items: center; justify-content: center;" id="btn">--%>
<%--            <form class="layui-form layui-form-pane" action="">--%>
<%--                <div class="layui-form-item">--%>
<%--                    <div class="layui-inline">--%>
<%--                        <label class="layui-form-label">顾客账号</label>--%>
<%--                        <div class="layui-input-inline">--%>
<%--                            <!--注意此处input标签里的id-->--%>
<%--                            <input class="layui-input" name="keyword" id="IDSearch" autocomplete="off">--%>
<%--                        </div>--%>
<%--                    </div>--%>

<%--                    <div class="layui-inline">--%>
<%--                        <label class="layui-form-label">操作员</label>--%>
<%--                        <div class="layui-input-inline">--%>
<%--                            <!--注意此处input标签里的id-->--%>
<%--                            <input class="layui-input" name="keyword" id="CarTypeSearch" autocomplete="off">--%>
<%--                        </div>--%>
<%--                    </div>--%>

<%--                    <div class="layui-inline">--%>
<%--                        <label class="layui-form-label">账单编号</label>--%>
<%--                        <div class="layui-input-inline">--%>
<%--                            <!--注意此处input标签里的id-->--%>
<%--                            <input class="layui-input" name="keyword" id="RentIdSearch" autocomplete="off">--%>
<%--                        </div>--%>
<%--                    </div>--%>

<%--                    <div class="layui-inline">--%>
<%--                        <!--注意此处button标签里的type属性-->--%>
<%--                        <button type="button" class="layui-btn layui-btn-primary"  data-type="reload" lay-filter="data-search-btn">--%>
<%--                            <i class="layui-icon"></i> 搜 索--%>
<%--                        </button>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </form>--%>
<%--        </div>--%>
<%--    </fieldset>--%>

    <!-- 1.用户账单信息 ，用户订购之后，去店里提车，适合就可提车，不适合就可取消
        2.这里可缴纳赔偿金
 -->
    <table id="CustomerRentOrderInfo" lay-filter="RentOrderFilter"></table>
</div>

</body>


<!-- 数据表操作按钮 -->
<script type="text/html" id="toolsBar">
    <a class="layui-btn layui-btn-mini" lay-event="CustomerPay" >支付</a>
    <a class="layui-btn layui-btn-danger" lay-event="CustomerCancel" >删除</a>
</script>

<%--顾客支付界面--%>
<div class="layui-row" id="ProcessPage" style="display: none;">
    <div class="layui-col-md10" >
        <form class="layui-form layui-from-pane" action="" style="margin-top:20px" >

            <!-- 支付金额 -->
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <input type="text" class="layui-input" id="RentOrderMoney" disabled readonly class="layui-input layui-disabled" >
                </div>
            </div>

        </form>
    </div>
</div>

<%--头部工具栏--%>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="getRefresh">刷新</button>
    </div>
</script>

<script src="layui/layui.js"></script>
<script src="js/jquery-3.5.1.js"></script>
<script src="js/CustomerRentOrder.js"></script>
</html>
