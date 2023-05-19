<%@page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">


  <title>车辆信息界面</title>
  <link rel="stylesheet" href="layui/css/layui.css">
  <link rel="stylesheet" href="css/AdminCar.css">
</head>

  <body>

  <!-- 车辆数据信息表-->
  <table id="CarInfo" lay-filter="CarFilter"></table>

  </body>

<!-- 添加车辆信息 -->
<div class="layui-row" id="AddCar" type="text/html">
    <div class="layui-col-md10">
        <form class="layui-form layui-from-pane" action="" style="margin-top:20px" >

            <div class="layui-form-item">
                <label class="layui-form-label">车型</label>
                <div class="layui-input-block">
                    <input type="text" name="neweqptName"  id="AddCarTypeInfo" required  lay-verify="required" autocomplete="off" placeholder="" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">品牌</label>
                <div class="layui-input-block">
                    <input type="text" name="neweqptName"  id="AddCarBrandInfos" required  lay-verify="required" autocomplete="off" placeholder="" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">车牌号</label>
                <div class="layui-input-block">
                    <input type="text" name="neweqptName"  id="AddCarSignInfo" required  lay-verify="required" autocomplete="off" placeholder="" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">座位数</label>
                <div class="layui-input-block">
                    <input type="text" name="neweqptName"  id="AddCarSizeInfo" required  lay-verify="required" autocomplete="off" placeholder="" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">购买日期</label>
                <div class="layui-input-block">
                    <input type="text" name="neweqptName"  id="AddPurchaseDateInfo" required  lay-verify="required" autocomplete="off" placeholder="yyyy-MM-dd HH:mm:ss" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">购买价格</label>
                <div class="layui-input-block">
                    <input type="text" name="neweqptName"  id="AddPurchasePriceInfo" required  lay-verify="required" autocomplete="off" placeholder="" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">车辆详情</label>
                <div class="layui-input-block">
                    <input type="text" name="neweqptName"  id="AddCarDetailInfo" required  lay-verify="required" autocomplete="off" placeholder="" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <input type="text" name="neweqptName"  id="AddRemarkInfo" required  lay-verify="required" autocomplete="off" placeholder="" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">租赁方式</label>
                <div class="layui-input-block">
                    <input type="text" name="neweqptName"  id="AddRentWayInfo" required  lay-verify="required" autocomplete="off" placeholder="" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">租赁价格</label>
                <div class="layui-input-block">
                    <input type="text" name="neweqptName"  id="AddRentPriceInfo" required  lay-verify="required" autocomplete="off" placeholder="" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">图片名称</label>
                <div class="layui-input-block">
                    <input type="text" name="neweqptName"  id="ImgNameInfo" required  lay-verify="required" autocomplete="off" placeholder="名称格式: xxxx.jpg" class="layui-input">
                </div>
            </div>

            <%--上传图片--%>
            <div class="layui-upload">
                <div class="layui-upload-list">
                    <img class="layui-upload-img" id="uploadImg">
                    <p id="photoTitle"></p>
                </div>
                <button type="button" class="layui-btn" id="submitButton">上传图片</button>
            </div>
        </form>

    </div>
</div>

<!-- 编辑车辆信息 -->
<div class="layui-row" id="EditCar" type="text/html" style="display: none;">
  <div class="layui-col-md10">
    <form class="layui-form layui-from-pane" action="" style="margin-top:20px" >

        <div class="layui-form-item">
            <label class="layui-form-label">车型</label>
            <div class="layui-input-block">
                <input type="text" name="neweqptName"  id="CarTypeInfo" required  lay-verify="required" autocomplete="off" placeholder="" class="layui-input">
            </div>
        </div>  

        <div class="layui-form-item">
            <label class="layui-form-label">品牌</label>
            <div class="layui-input-block">
                <input type="text" name="neweqptName"  id="CarBrandInfos" required  lay-verify="required" autocomplete="off" placeholder="" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">车牌号</label>
            <div class="layui-input-block">
                <input type="text" name="neweqptName"  id="CarSignInfo" required  lay-verify="required" autocomplete="off" placeholder="" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">座位数</label>
            <div class="layui-input-block">
                <input type="text" name="neweqptName"  id="CarSizeInfo" required  lay-verify="required" autocomplete="off" placeholder="" class="layui-input">
           </div>
        </div>


        <div class="layui-form-item">
            <label class="layui-form-label">购买日期</label>
            <div class="layui-input-block">
                <input type="text" name="neweqptName"  id="PurchaseDateInfo" required  lay-verify="required" autocomplete="off" placeholder="" class="layui-input">
           </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">购买价格/万</label>
            <div class="layui-input-block">
                <input type="text" name="neweqptName"  id="PurchasePriceInfo" required  lay-verify="required" autocomplete="off" placeholder="" class="layui-input">
           </div>
        </div>

       <div class="layui-form-item">
            <label class="layui-form-label">车辆详情</label>
            <div class="layui-input-block">
                <input type="text" name="neweqptName"  id="CarDetailInfo" required  lay-verify="required" autocomplete="off" placeholder="" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-block">
                <input type="text" name="neweqptName"  id="RemarkInfo" required  lay-verify="required" autocomplete="off" placeholder="" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">租赁方式</label>
            <div class="layui-input-block">
                <input type="text" name="neweqptName"  id="RentWayInfo" required  lay-verify="required" autocomplete="off" placeholder="" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">租赁价格/元</label>
            <div class="layui-input-block">
                <input type="text" name="neweqptName"  id="RentPriceInfo" required  lay-verify="required" autocomplete="off" placeholder="" class="layui-input">
            </div>
        </div>


    </form>
  </div>
</div>

<!-- 车辆图片 -->
<script type="text/html" id="Pic">
  <img src="/img" alt="" class="CarImg"  onclick="show_img(this)"></img>
</script>

<!-- 数据表操作按钮 -->
<script type="text/html" id="toolsBar">
  <a class="layui-btn layui-btn-mini" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
</script>  

<!-- 引入js -->
<script src="layui/layui.js"></script>
<script src="js/jquery-3.5.1.js"></script>
<script src="js/AdminCar.js"></script>

<%-- 数据表头部工具栏 --%>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="getInsert">新增</button>
        <button class="layui-btn layui-btn-sm" lay-event="getRefresh">刷新</button>
    </div>
</script>

</html>