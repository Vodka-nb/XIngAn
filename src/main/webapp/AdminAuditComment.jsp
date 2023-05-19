<%--
  Created by IntelliJ IDEA.
  User: 伟神
  Date: 2023/4/8
  Time: 10:36
  To change this template use File | Settings | File Templates.
--%>
<html>
<link rel="stylesheet" href="layui/css/layui.css">
<link rel="stylesheet" href="css/AdminAuditComment.css">
<%@page contentType="text/html;charset=UTF-8" language="java"%>
<!--引入标签库-->
<%@ taglib prefix="v" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <title>管理员审核评论</title>
</head>

<body>
<%--编辑盒子--%>
<div id="AuditBox">
    <!-- 管理员审核客户评价信息 -->
    <table id="AuditCommentInfo" lay-filter="AuditCommentFilter"></table>
</div>

</body>

<!-- 引入js -->
<script src="layui/layui.js"></script>
<script src="js/jquery-3.5.1.js"></script>
<script src="js/AdminAuditComment.js"></script>

<!-- 按钮 -->
<script type="text/html" id="FunctionBar">
    <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="through">通过</a>
    <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
</script>

<%-- 数据表头部工具栏 --%>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="getRefresh">刷新</button>
    </div>
</script>

</html>
