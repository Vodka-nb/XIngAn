<%@page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>服务人员信息界面</title>
  <link rel="stylesheet" href="layui/css/layui.css">
  <link rel="stylesheet" href="css/AdminServers.css">
</head>
<body>

<!-- 服务员数据表 -->
<table id="ServerInfo" lay-filter="ServerFilter">
</table>
 
	
</body>

<!-- 服务员信息探窗 -->
<div class="layui-row" id="ProcessPage" style="display: none;">
    <div class="layui-col-md10" >
        <form class="layui-form layui-from-pane" action="" style="margin-top:20px" >  
            <div class="layui-form-item">
                <label class="layui-form-label">服务员ID</label>
                <div class="layui-input-block">
                    <input type="text" name="neweqptIdCode"   id="ServerIdInfo" required  lay-verify="required" autocomplete="off" placeholder="" class="layui-input">
                </div>
            </div>
            
            <div class="layui-form-item">
                <label class="layui-form-label">昵称</label>
                <div class="layui-input-block">
                    <input type="text" name="neweqptIdCode"   id="ServerNameInfo" required  lay-verify="required" autocomplete="off" placeholder="" class="layui-input">
                </div>
            </div>
            
            <div class="layui-form-item">
                <label class="layui-form-label">联系方式</label>
                <div class="layui-input-block">
                    <input type="text" name="neweqptIdCode"   id="ServerPhoneInfos" required  lay-verify="required" autocomplete="off" placeholder="" class="layui-input">
                </div>
            </div>
             
             <div class="layui-form-item">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-block">
                    <input type="text" name="neweqptIdCode"   id="ServerSexInfo" required  lay-verify="required" autocomplete="off" placeholder="" class="layui-input">
                </div>
            </div>
            
            <div class="layui-form-item">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <input type="text" name="neweqptIdCode"   id="ServerRemarkInfo" required  lay-verify="required" autocomplete="off" placeholder="" class="layui-input">
                </div>
            </div>
        </form>
    </div>
</div>

<!-- 引入js -->
<script src="layui/layui.js"></script>
<script src="js/jquery-3.5.1.js"></script>
<script src="js/AdminServer.js"></script>
<!-- 按钮 -->
<script type="text/html" id="FunctionBar">
  <a class="layui-btn layui-btn-mini" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
</script>
<%-- 数据表头部工具栏 --%>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="getInsert">新增</button>
        <button class="layui-btn layui-btn-sm" lay-event="getRefresh">刷新</button>
    </div>
</script>

<!-- 添加服务员信息 -->
<div class="layui-row" id="AddServer" type="text/html"   >
    <div class="layui-col-md10">
        <form class="layui-form layui-from-pane" action=""  >

            <div class="layui-form-item">
                <label class="layui-form-label">服务员账号</label>
                <div class="layui-input-block">
                    <input type="text" style="margin-top: 50px;" name="neweqptIdCode" id="AddServerAccountInfo" required  lay-verify="required"   maxlength="12" maxlength="12" required autocomplete="off" placeholder="" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">名字</label>
                <div class="layui-input-block">
                    <input type="text" name="neweqptName"  id="AddServerNameInfo" required  lay-verify="required"   maxlength="10" autocomplete="off" placeholder="" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">联系方式</label>
                <div class="layui-input-block">
                    <input type="text" name="neweqptName"  id="AddServerPhoneInfo" required  lay-verify="required"   maxlength="11" autocomplete="off" placeholder="" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-block">
                    <input type="text" name="neweqptName"  id="AddServerGenderInfo" required  lay-verify="required"   maxlength="1" autocomplete="off" placeholder="" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <input type="text" name="neweqptName"  id="AddServerRemarkInfo" required  lay-verify="required"   maxlength="120" autocomplete="off" placeholder="" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-block">
                    <input type="password" name="neweqptName"  id="AddServerPassWordInfo" required  lay-verify="required"  maxlength="15" autocomplete="off" placeholder="" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">验证密码</label>
                <div class="layui-input-block">
                    <input type="password" name="neweqptName"  id="CertifyAddServerPassWordInfo" required  lay-verify="required"   maxlength="15" autocomplete="off" placeholder="" class="layui-input">
                </div>
            </div>
        </form>

    </div>
</div>

</html>