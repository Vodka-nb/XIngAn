<%@page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>管理员界面</title>
  <link rel="stylesheet" href="layui/css/layui.css">
  <link rel="stylesheet" href="css/Admin.css">
  <%@ taglib prefix="v" uri="http://java.sun.com/jsp/jstl/core" %>）

</head>
<body>
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo layui-hide-xs" id="RenYuanTitle"> 管 理 界 面 </div>
    <!-- 头部区域（可配合layui 已有的水平导航） -->
    <ul class="layui-nav layui-layout-left" id="Head">
      <!-- 移动端显示 -->
      <li class="layui-nav-item layui-show-xs-inline-block layui-hide-sm" lay-header-event="menuLeft">
        <i class="layui-icon layui-icon-spread-left"></i>
      </li>
      <!-- 标题 -->
       <div id="AdminTitle">行 安 汽 车 租 赁 管 理 系 统</div>
      <label style="display: flex;justify-content: center;align-items: center; width:50px; height: 50px;font-size: 18px;
       ">账号:</label>
       <div id="AccountDisplay">${sessionScope.SuperAccount}</div>
    </ul>

    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item layui-hide layui-show-md-inline-block">
        <a href="javascript:;" id="SuperName">
          ${sessionScope.SuperName}
        </a>
        <dl class="layui-nav-child">
          <dd ><a href="javascript:void(0)" id="Profile">资 料</a></dd>
          <dd ><a href="javascript:void(0)" id="Setting">设 置</a></dd>
          <dd ><a href="javascript:void(0)" id="LogOut">退 出</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item" lay-header-event="menuRight" lay-unselect>
        <a href="javascript:;">
          <i class="layui-icon layui-icon-more-vertical"></i>
        </a>
      </li>
    </ul>

  </div>
  
  <div class="layui-side " id="LeftBar">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree" lay-filter="test">
        <li class="layui-nav-item ">
          <a class="FunctionBar" data-src="AdminSuper.jsp" href="javascript:;" id="AdminInfo">管理员信息</a>
        </li>

        <li class="layui-nav-item ">
          <a class="FunctionBar" data-src="AdminServers.jsp" href="javascript:;" id="ServerInfo">服务人员信息</a>
        </li>

        <li class="layui-nav-item ">
          <a class="FunctionBar" data-src="AdminCars.jsp" href="javascript:;" id="CarInfo">车辆信息</a>
        </li>

        <div class="layui-nav-item" >
          <a href="javascript:;" class="FunctionBar" id="Customer" data-src="AdminCustomers.jsp">顾客信息</a>
       </div>

        <div class="layui-nav-item" >
           <a href="javascript:;" class="FunctionBar" id="Review" data-src="AdminReview.jsp">查看评价</a>
        </div>

        <div class="layui-nav-item" >
           <a href="javascript:;" class="FunctionBar" id="AuditComment" data-src="AdminAuditComment.jsp">审核评论内容</a>
        </div>

        <div class="layui-nav-item" >
          <a href="javascript:;" class="FunctionBar" id="OrderRecord" data-src="ServerOrderRecord.jsp">订单记录</a>
        </div>

      </ul>

    </div>
  </div>

  <!-- 内容主体  -->
  <div class="layui-body"   style="width:1335px; height:700px; ">
    <!-- 内容主体区域 -->
    <iframe frameborder="0" scrolling="yes" style=" width:100%; height:700px" src="" id="Display_box">
    </iframe>
  </div>

</div>
</body>


<!-- /这里是弹出层的表单信息
	//表单的id用于表单的选择，style是在本页隐藏，只有点击编辑才会弹出 -->
<%--修改个人资料--%>
<div class="layui-row" id="ProfilePage" style="display:none;">
  <div class="layui-col-md10" >
    <form class="layui-form layui-from-pane" action="" style="margin-top:20px" >

      <!-- 昵称 -->
      <div class="layui-form-item">
        <div class="layui-input-block">
          <input type="text" class="layui-input" id="AdminNameInfo"  lay-verify="required" lay-reqtext="必填项" placeholder="姓名">
        </div>
      </div>

      <!-- 电话 -->
      <div class="layui-form-item">
        <div class="layui-input-block">
          <input type="text" class="layui-input" id="AdminPhoneInfo" lay-verify="required" lay-reqtext="必填项" maxlength="11" placeholder="电话号码">
        </div>
      </div>

      <!-- 性别 -->
      <div class="layui-form-item">
        <div class="layui-input-block">
          <input type="text" name="AdminGender"  id="AdminGenderInfo" lay-verify="required" lay-reqtext="必填项" placeholder="性别" maxlength="1" autocomplete="off" class="layui-input">
        </div>
      </div>

      <!-- 备注-->
      <div class="layui-form-item">
        <div class="layui-input-block">
          <input type="text" name="AdminRemark"  id="AdminRemarkInfo" lay-verify="required" lay-reqtext="必填项" placeholder="备注" autocomplete="off" class="layui-input">
        </div>
      </div>

    </form>
  </div>
</div>

<%--修改密码表单--%>
<div class="layui-row" id="PassWordPage" style="display: none;">
  <div class="layui-col-md10" >
    <form class="layui-form layui-from-pane" action="" style="margin-top:20px" >

      <!-- 旧密码 -->
      <div class="layui-form-item">
        <div class="layui-input-block">
          <input type="password" class="layui-input" id="OldPassWord"  lay-verify="required" lay-reqtext="必填项" maxlength="20" placeholder="请输入旧密码">
        </div>
      </div>

      <!-- 新密码 -->
      <div class="layui-form-item">
        <div class="layui-input-block">
          <input type="password" class="layui-input" id="NewPassWord" lay-verify="required" lay-reqtext="必填项" maxlength="20" placeholder="新密码">
        </div>
      </div>

      <!-- 新密码确认 -->
      <div class="layui-form-item">
        <div class="layui-input-block">
          <input type="password" name="AdminGender"  id="EnsurePassWord" lay-verify="required" lay-reqtext="必填项" placeholder="新密码确认" maxlength="20" autocomplete="off" class="layui-input">
        </div>
      </div>

    </form>
  </div>
</div>


<!-- 引入js -->
<script src="layui/layui.js"></script>
<script src="js/jquery-3.5.1.js"></script>
<script src="js/Admin.js"></script>
</html>