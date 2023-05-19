<%@page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>管理员界面</title>
  <link rel="stylesheet" href="layui/css/layui.css">
  <link rel="stylesheet" href="css/AdminSuper.css">
</head>
<body>

  <!-- 管理员数据信息 -->
  <table id="SuperInfo" lay-filter="SuperFilter"></table>

<!-- 编辑管理员信息 -->
<div class="layui-row" id="EditSuper" type="text/html"   >
  <div class="layui-col-md10">
    <form class="layui-form layui-from-pane" action=""  >


        <div class="layui-form-item">
            <label class="layui-form-label">昵称</label>
            <div class="layui-input-block">
                <input type="text" style="margin-top: 50px;" name="neweqptIdCode" id="NickNameInfo" required  lay-verify="required"  required autocomplete="off" placeholder="" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <input type="text" name="neweqptName"  id="GenderInfo" required  lay-verify="required" autocomplete="off" placeholder="" class="layui-input">
            </div>
        </div>


        <div class="layui-form-item">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
                <input type="text" name="neweqptName"  id="RemarkInfos" required  lay-verify="required" autocomplete="off" placeholder="" class="layui-input">
            </div>
        </div>  
       
    </form>
  </div>
</div>
</body>

<!-- 添加管理员信息 -->
<div class="layui-row" id="AddSuper" type="text/html"   >
    <div class="layui-col-md10">
        <form class="layui-form layui-from-pane" action=""  >

            <div class="layui-form-item">
                <label class="layui-form-label">管理员账号</label>
                <div class="layui-input-block">
                    <input type="text" style="margin-top: 50px;" name="neweqptIdCode" id="SuperAccountInfo" required  lay-verify="required"   maxlength="12" maxlength="12" required autocomplete="off" placeholder="" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">名字</label>
                <div class="layui-input-block">
                    <input type="text" name="neweqptName"  id="SuperNameInfo" required  lay-verify="required"   maxlength="10" autocomplete="off" placeholder="" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">联系方式</label>
                <div class="layui-input-block">
                    <input type="text" name="neweqptName"  id="SuperPhoneInfo" required  lay-verify="required"   maxlength="11" autocomplete="off" placeholder="" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">性别</label>
                <div class="layui-input-block">
                    <input type="text" name="neweqptName"  id="SuperGenderInfo" required  lay-verify="required"   maxlength="1" autocomplete="off" placeholder="" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <input type="text" name="neweqptName"  id="SuperRemarkInfo" required  lay-verify="required"   maxlength="120" autocomplete="off" placeholder="" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">密码</label>
                <div class="layui-input-block">
                    <input type="password" name="neweqptName"  id="SuperPassWordInfo" required  lay-verify="required"  maxlength="15" autocomplete="off" placeholder="" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">验证密码</label>
                <div class="layui-input-block">
                    <input type="password" name="neweqptName"  id="CertifySuperPassWordInfo" required  lay-verify="required"   maxlength="15" autocomplete="off" placeholder="" class="layui-input">
                </div>
            </div>
        </form>

    </div>
</div>

</body>

<!-- 数据表操作按钮 -->
<script type="text/html" id="toolsBar" >
<%--  <a class="layui-btn layui-btn-mini" lay-event="edit">编辑</a>--%>
  <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
</script>  
<!-- 引入js -->
<script src="layui/layui.js" charset="utf-8"></script>
<script src="js/jquery-3.5.1.js"></script>
<script src="js/AdminSuper.js"></script>
<%-- 数据表头部工具栏 --%>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="getInsert">新增</button>
        <button class="layui-btn layui-btn-sm" lay-event="getRefresh">刷新</button>
    </div>
</script>

</html>