<%@page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>服 务 人 员 界 面 </title>
  <link rel="stylesheet" href="layui/css/layui.css">
  <link rel="stylesheet" href="css/Server.css  ">
</head>
<body>
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo layui-hide-xs " id="ServerTitle">服 务 人 员 界 面</div>

    <!-- 头部区域（可配合layui 已有的水平导航） -->
    <ul class="layui-nav layui-layout-left" id="Head">
      <!-- 移动端显示 -->
      <li class="layui-nav-item layui-show-xs-inline-block layui-hide-sm" lay-header-event="menuLeft">
        <i class="layui-icon layui-icon-spread-left"></i>
      </li>  
        <!-- 标题 -->
        <div id="Title">行 安 汽 车 租 赁 管 理 系 统</div>
      <label style="display: flex;justify-content: center;align-items: center; width:50px; height: 50px;font-size: 18px;
       ">账号:</label>
      <div id="AccountDisplay">${sessionScope.ServerAccount}</div>
    </ul>
	
    <!-- 右侧信息栏 -->
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item layui-hide layui-show-md-inline-block">
        <a href="javascript:;" id="ServerName">
          ${sessionScope.ServerName}
        </a>
        <dl class="layui-nav-child">
          <dd ><a href="javascript:void(0)" id="Profile">资 料</a></dd>
          <dd ><a href="javascript:void(0)" id="Setting">设 置</a></dd>
          <dd ><a href="javascript:void(0)" id="LogOut">退 出</a></dd>
        </dl>
      </li>
      <!-- 右侧菜单 -->
      <li class="layui-nav-item" lay-header-event="menuRight" lay-unselect>
        <a href="javascript:;">
          <i class="layui-icon layui-icon-more-vertical"></i>
        </a>
      </li>
    
    </ul>
  </div>
  

  <div class="layui-side ">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree" lay-filter="ServerFunctionBar">
        <li class="layui-nav-item layui-nav-itemed">
          <a class="" href="javascript:;" id="RentInfo">租 赁 信 息</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;" id="ReturnBillProcessing" class="FunctionBar" data-src="ServerReturnBillProcessing.jsp">还 车 管 理</a></dd>
            <dd><a href="javascript:;" id="OrderProcessing" class="FunctionBar" data-src="ServerRentProcessing.jsp">租 车 管 理 </a></dd>

          </dl>
        </li>
        <div id="ScanReview" class="FunctionBar" data-src="AdminReview.jsp">查 看 评 价</div>
        
      </ul>
    </div>
  </div>


  
  <div class="layui-body">
    <!-- 内容主体区域 -->
     <!-- 内容主体区域 -->
     <iframe frameborder="0" scrolling="yes" style=" width:100%; height:700px" src="" id="aa">
         
     </iframe>
  </div>
  

</div>
<script src="layui/layui.js"></script>
<script src="js/jquery-3.5.1.js"></script>
<script src="js/Server.js"></script>

<!-- /这里是弹出层的表单信息
	//表单的id用于表单的选择，style是在本页隐藏，只有点击编辑才会弹出 -->
<div class="layui-row" id="ProfilePage" style="display:none;">
  <div class="layui-col-md10" >
    <form class="layui-form layui-from-pane" action="" style="margin-top:20px" >

      <!-- 昵称 -->
      <div class="layui-form-item">
        <div class="layui-input-block">
          <input type="text" class="layui-input" id="ServerNameInfo"  lay-verify="required" lay-reqtext="必填项" placeholder="姓名">
        </div>
      </div>

      <!-- 电话 -->
      <div class="layui-form-item">
        <div class="layui-input-block">
          <input type="text" class="layui-input" id="ServerPhoneInfo" lay-verify="required" lay-reqtext="必填项" placeholder="电话号码">
        </div>
      </div>

      <!-- 性别 -->
      <div class="layui-form-item">
        <div class="layui-input-block">
          <input type="text" name="ServerGender"  id="ServerGenderInfo" lay-verify="required" lay-reqtext="必填项" placeholder="性别" maxlength="1" autocomplete="off" class="layui-input">
        </div>
      </div>

      <!-- 备注-->
      <div class="layui-form-item">
        <div class="layui-input-block">
          <input type="text" name="ServerRemark"  id="ServerRemarkInfo" lay-verify="required" lay-reqtext="必填项" placeholder="备注" autocomplete="off" class="layui-input">
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
</body>
</html>