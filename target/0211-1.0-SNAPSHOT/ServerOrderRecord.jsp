<%@page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>服务</title>
    <link rel="stylesheet" href="layui/css/layui.css">
    <link rel="stylesheet" href="css/ServerOrderRecord.css">

</head>
<body>
<!-- 订单记录 -->
<div id="OrderRecordBox" >
    <!-- 搜索框 -->
    <fieldset class="table-search-fieldset">
        <legend>管理员信息</legend>
        <div style="margin: 10px 10px 10px 10px" id="btn">
            <form class="layui-form layui-form-pane" action="">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">账单编号查询</label>
                        <div class="layui-input-inline">
                            <!--注意此处input标签里的id-->
                            <input class="layui-input" name="keyword" id="OrderIdSearch" autocomplete="off">
                        </div>
                    </div>

                    <div class="layui-inline">
                        <label class="layui-form-label">租/还车订单编号</label>
                        <div class="layui-input-inline">
                            <!--注意此处input标签里的id-->
                            <input class="layui-input" name="keyword" id="RentOrReturnSearch" autocomplete="off">
                        </div>
                    </div>

                    <div class="layui-inline">
                        <label class="layui-form-label">金额形式查询</label>
                        <div class="layui-input-inline">
                            <!--注意此处input标签里的id-->
                            <input class="layui-input" name="keyword" id="MoneyStatusSearch" autocomplete="off">
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

    <div id="OrderRecord" >
        <table id="ProcessRent" lay-filter="OrderRecordProcessing"></table>
    </div>
</div>
</body>


<!-- /这里是弹出层的表单信息
 	//表单的id用于表单的选择，style是在本页隐藏，只有点击编辑才会弹出 -->
<div class="layui-row" id="ProcessPage" style="display: none;">
    <div class="layui-col-md10" >
        <form class="layui-form layui-from-pane" action="" style="margin-top:20px" >
            <div class="layui-form-item">
                <label class="layui-form-label">账单编号</label>
                <div class="layui-input-block">
                    <input type="text" name="neweqptIdCode" id="OrderIdInfo" required  lay-verify="required" autocomplete="off" placeholder="" class="layui-input" disabled readonly class="layui-input layui-disabled">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">还车编号</label>
                <div class="layui-input-block">
                    <input type="text" name="neweqptIdCode" id="ReturnIdInfo" required  lay-verify="required" autocomplete="off" placeholder="" class="layui-input" disabled readonly class="layui-input layui-disabled">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">支付方式</label>
                <div class="layui-input-block">
                    <input type="text" name="neweqptIdCode" id="PayMethodInfo" required  lay-verify="required" autocomplete="off" placeholder="" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">支付金额</label>
                <div class="layui-input-block">
                    <input type="text" name="neweqptIdCode" id="MoneyInfo" required  lay-verify="required" autocomplete="off" placeholder="" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">时间</label>
                <div class="layui-input-block">
                    <input type="text" name="neweqptIdCode" id="PayTimeInfo" required  lay-verify="required" autocomplete="off" placeholder="" class="layui-input">
                </div>
            </div>

        </form>
    </div>
</div>

<!-- 数据表操作按钮 -->
<script src="js/jquery-3.5.1.js"></script>
<script src="layui/layui.js"></script>
<!-- 按钮 -->
<script type="text/html" id="OrderRecordFunction">
    <a class="layui-btn layui-btn-mini" lay-event="ServerEditOrder" >编辑</a>
    <a class="layui-btn layui-btn-danger" lay-event="ServerDeleteOrder" >删除</a>
</script>
<script src="js/ServerOrderRecord.js"></script>

<%-- 数据表头部工具栏 --%>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="getRefresh">刷新</button>
    </div>
</script>

</html>