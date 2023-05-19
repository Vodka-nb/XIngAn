<%@page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>顾 客 租 车</title>
  <link rel="stylesheet" href="layui/css/layui.css">
  <link rel="stylesheet" href="css/CustomerRentCar.css  ">
  
</head>
 <body>
  <!-- 订车页面 -->
  <div id="OrderBox">

      <!-- 车辆信息横向展示 -->
    <div  id="CarDisplay">
        <!-- 搜索框 -->
        <fieldset class="table-search-fieldset" id="SearchBar">
            <legend>车辆信息</legend>
            <div style="margin: 10px 10px 10px 10px" id="btn">
                <form class="layui-form layui-form-pane" action=""  id="SearchCar">
                    <div class="layui-form-item">
<%--                        车牌查询--%>
                        <div class="layui-inline">
                            <label class="layui-form-label">车牌查询</label>
                            <div class="layui-input-inline">
                                <!--注意此处input标签里的id-->
                                <input class="layui-input" name="keyword" id="IDSearch" autocomplete="off">
                            </div>
                        </div>
<%--                        车型查询--%>
                        <div class="layui-inline">
                            <label class="layui-form-label">车型查询</label>
                            <div class="layui-input-inline">
                                <!--注意此处input标签里的id-->
                                <input class="layui-input" name="keyword" id="CarTypeSearch" autocomplete="off">
                            </div>
                        </div>
<%--                        租赁状况查询--%>
                        <div class="layui-inline">
                            <label class="layui-form-label">租赁状况查询</label>
                            <div class="layui-input-inline">
                                <!--注意此处input标签里的id-->
                                <input class="layui-input" name="keyword" id="RentSolutionSearch" autocomplete="off">
                            </div>
                        </div>

                        <div class="layui-inline">
                            <!--注意此处button标签里的type属性-->
                            <button type="button" class="layui-btn layui-btn-primary" id="SearchCarButton" data-type="reload" lay-filter="data-search-btn">
                                <i class="layui-icon"></i> 搜 索
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </fieldset>

         <!-- 车辆数据信息 -->
        <table id="CarInfo" lay-filter="RentCarFilter"></table>
    </div>


  </div>
 </body>

<!-- /这里是弹出层的表单信息
	//表单的id用于表单的选择，style是在本页隐藏，只有点击编辑才会弹出 -->
<div class="layui-row" id="ProcessPage" style="display: none;">
  <div class="layui-col-md10" >
    <form class="layui-form layui-from-pane" action="" style="margin-top:20px" >

      <!-- 取车时间 -->
      <div class="layui-form-item">
        <div class="layui-input-block">
          <input type="text" class="layui-input" id="RentCarTime" placeholder="yyyy-MM-dd HH:mm:ss">
        </div>
      </div>

      <!-- 还车时间 -->
      <div class="layui-form-item">
        <div class="layui-input-block">
          <input type="text" class="layui-input" id="ReturnCarTime" placeholder="yyyy-MM-dd HH:mm:ss">
        </div>
      </div>

      <!-- 联系方式 -->
<%--      <div class="layui-form-item">--%>
<%--        <div class="layui-input-block">--%>
<%--          <input type="text" name="PhoneNum"  id="PhoneNum" lay-verify="required" lay-reqtext="请输入联系方式" placeholder="电话号码" maxlength="11" autocomplete="off" class="layui-input">--%>
<%--        </div>--%>
<%--      </div>--%>

  <!-- 取车地点-->
  <div class="layui-form-item">
    <div class="layui-input-block">
      <input type="text" name="OrderNum"  id="RentCarAddress" lay-verify="required" lay-reqtext="请输入取车地点" placeholder="取车地点" autocomplete="off" class="layui-input">
    </div>
  </div>

        <!-- 租赁方式-->
        <div class="layui-form-item">
            <div class="layui-input-block">
                <input type="text" name="CustomerRentWay"  id="CustomerRentWay" lay-verify="required" lay-reqtext="请选择租赁方式" maxlength="2" placeholder="日租/周租/月租" autocomplete="off" class="layui-input">
            </div>
        </div>


    </form>
  </div>
</div>

<!-- 数据表操作按钮 -->
<script type="text/html" id="toolsBar">
  <a class="layui-btn layui-btn-mini" lay-event="CustomerOrder" >订购</a>
</script>

<!-- 车辆图片 -->
 <script type="text/html" id="Pic">
  <img src="{{res.imgUrl}}" alt="" class="CarImg"  onclick="show_img(this)"></img>
 </script>

<%-- 数据表头部工具栏 --%>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="getRefresh">刷新</button>
    </div>
</script>
 
 <script src="layui/layui.js"></script>
  <script src="js/jquery-3.5.1.js"></script>
 <script src="js/CustomerRentCar.js"></script>
</html>