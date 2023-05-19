<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>用户评价界面</title>
  <link rel="stylesheet" href="layui/css/layui.css">
  <link rel="stylesheet" href="css/AdminReview.css">
    <%@page contentType="text/html;charset=UTF-8" language="java"%>
    <!--引入标签库-->
    <%@ taglib prefix="v" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
</head>
<body>

  <!-- 评论区 -->
    <div class="layui-card" id="Bigbox">
     <v:forEach items="${CList}" var="Content">
         <div class="layui-card-body">
             <div class="ReviewBox">
                 <!-- 评论区头部 -->
                 <div class="ReviewHeader">
                     <div class="Reviewer">顾客账号:${Content.customerAccount}</div>
                     <div class="OrderId">订单账号:${Content.orderId}</div>
                     <div class="ReviewTime">
                        时间: ${Content.commentTime}
                     </div>
                 </div>
                 <!-- 评论区内容，可滑动 -->
                 <div class="ReviewContent">
                     ${Content.content}
                 </div>
             </div>
         </div>
     </v:forEach>
    </div>

</body>

<!-- 引入js -->
<script src="layui/layui.js"></script>
<script src="js/jquery-3.5.1.js"></script>
<script src="js/AdminReview.js"></script>
<!-- 按钮 -->
<script type="text/html" id="barDemo">
  <a class="layui-btn layui-btn-primary layui-btn-mini" lay-event="detail">查看</a>
  <a class="layui-btn layui-btn-mini" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">通过</a>
</script>

</html>