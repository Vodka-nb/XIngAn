<%@page contentType="text/html;charset=UTF-8" language="java"%>
<html>
	<head>
		<title>登录界面</title>
		<!-- 引入layuicss -->
		<link rel="stylesheet" type="text/css" href="./layui/css/layui.css"/>
		 <link rel="stylesheet" type="text/css" href="css/index.css"/>
	</head>
	
	<body>
         <div id="Title">行安汽车租赁管理系统</div>

		<!-- 登录表单框 -->
		<div class="layui-container" id="loginForm" >	
		    <form class="layui-form" action="" lay-filter="">
				
		      <div class="layui-form-item" style="margin-top: 100px;">
		        <label class="layui-form-label">账号:</label>
		        <div class="layui-input-block">
		          <input type="text"   id="userAccount" name="username" lay-verify="required"  autocomplete="off" value="" placeholder="请输入用户账号" class="layui-input">
		        </div>
		      </div>
			  
		      <div class="layui-form-item">
		        <label class="layui-form-label">密码:</label>
		        <div class="layui-input-block">
		          <input type="password"  id="password" name="password" lay-verify="required" placeholder="请输入密码" value=""  autocomplete="off" class="layui-input">
		        </div>
		      </div>      
		      
		      <div class="layui-form-item" >
		        <label class="layui-form-label" >身份:</label>
		        <div class="layui-input-block">
		          <select name="chacracter" id="identity" lay-filter="shenfen">
		            <option value="1">顾客</option>
		            <option value="2">服务员</option>
		            <option value="3">管理员</option>
		          </select>
		        </div>
		      </div>
			  
			  <button type="button"  class="layui-btn" lay-submit="" lay-filter="Loginbutton" id="loginbutton" >登录</button>
			  <button type="button" class="layui-btn" id="Registerbutton" >注册</button>
			</form>
		</div>
		
	</body>

<%--    隐藏表单   --%>
	<div class="layui-row" id="ProcessPage" style="display: none;">
		<div class="layui-col-md10" >
			<form class="layui-form layui-from-pane"  action="" style="margin-top:20px" >

				<!-- 用户账号 -->
				<div class="layui-form-item">
					<div class="layui-input-block">
						<input type="text" name="Account"  id="Account"   maxlength="20" lay-reqtext="请输入账号" placeholder="顾客账号" autocomplete="off" class="layui-input">
					</div>
				</div>

				<!-- 联系方式 -->
				<div class="layui-form-item">
					<div class="layui-input-block">
						<input type="text" name="PhoneNum"  id="PhoneNum"   maxlength="11"  lay-reqtext="请输入联系方式" placeholder="电话号码" autocomplete="off" class="layui-input">
					</div>
				</div>

				<!-- 证件类型 -->
				<div class="layui-form-item">
					<div class="layui-input-block">
						<input type="text" name="CardType"  id="CardType"  maxlength="3" lay-reqtext="请输入证件类型" placeholder="身份证/驾驶证" autocomplete="off" class="layui-input">
					</div>
				</div>

				<!-- 证件号 -->
				<div class="layui-form-item">
					<div class="layui-input-block">
						<input type="text" name="CardNum"  id="CardNum"   maxlength="18" lay-reqtext="请输入证件号" placeholder="证件号码" autocomplete="off" class="layui-input">
					</div>
				</div>

				<!-- 用户名 -->
				<div class="layui-form-item">
					<div class="layui-input-block">
						<input type="text" name="Name"  id="Name"  maxlength="20" lay-reqtext="请输入用户名" placeholder="用户名" autocomplete="off" class="layui-input">
					</div>
				</div>

                <%--		地址		--%>
				<div class="layui-form-item">
					<div class="layui-input-block">
						<input type="text" name="Address"  id="Address"  maxlength="40" lay-reqtext="请输入常用地址" placeholder="地址" autocomplete="off" class="layui-input">
					</div>
				</div>

				<!-- 输入密码 -->
				<div class="layui-form-item">
					<div class="layui-input-block">
						<input type="password" name="PW1"  id="PW1"  maxlength="16" lay-reqtext="请输入密码" placeholder="请输入密码" autocomplete="off" class="layui-input">
					</div>
				</div>

				<!-- 确认密码-->
				<div class="layui-form-item">
					<div class="layui-input-block">
						<input type="password" name="PW2"  id="PW2"   maxlength="16" lay-reqtext="请确认密码" placeholder="确认密码" autocomplete="off" class="layui-input">
					</div>
				</div>

			</form>
		</div>
	</div>
	<script src="layui/layui.js" charset="UTF-8"></script>
	<script src="js/index.js" charset="UTF-8"></script>
</html>
