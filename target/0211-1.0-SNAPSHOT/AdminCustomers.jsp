<%@page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>用户信息界面</title>
  <link rel="stylesheet" href="layui/css/layui.css">
  <link rel="stylesheet" href="css/AdminUsers.css">
</head>
<body>

    <!-- 搜索框 -->
    <fieldset class="table-search-fieldset">
    <legend>顾客信息</legend>
    <div style="margin: 10px 10px 10px 10px" id="btn">
        <form class="layui-form layui-form-pane" action="">
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">ID查询</label>
                    <div class="layui-input-inline">
                        <!--注意此处input标签里的id-->
                        <input class="layui-input" name="keyword" id="IDSearch" autocomplete="off">
                    </div>
                </div>
  
                <div class="layui-inline">
                    <label class="layui-form-label">姓名查询</label>
                    <div class="layui-input-inline">
                        <!--注意此处input标签里的id-->
                        <input class="layui-input" name="keyword" id="NickNameSearch" autocomplete="off">
                    </div>
                </div>
                
                <div class="layui-inline">  
                  <!--注意此处button标签里的type属性-->
                    <button type="button" class="layui-btn layui-btn-primary"  data-type="reload" lay-filter="data-search-btn">
                      <i class="layui-icon"></i> 查询
                    </button>
                </div>
            </div>
        </form>
    </div>
  </fieldset>   
  
  <!-- 用户数据表 -->
  <table id="UserInfo" lay-filter="CustomerFilter"></table>

</body>

<!-- 引入js -->
<script src="layui/layui.js"></script>
<script src="js/jquery-3.5.1.js"></script>
<script src="js/AdminUser.js"></script>
<!-- 按钮 -->
<script type="text/html" id="FunctionBar">
  <!-- 删除用户信息时，管理员需要输入密码 -->
  <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
</script>
</body>
</html>