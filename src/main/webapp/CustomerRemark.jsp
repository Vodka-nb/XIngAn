<%@page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>顾 客 评 论</title>
  <link rel="stylesheet" href="layui/css/layui.css">
  <link rel="stylesheet" href="css/CustomerRemark.css  ">
  
</head>
 <body>
  <div id="ReviewBox" >
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
      <legend>欢迎您留下宝贵意见!</legend>
    </fieldset>
     账单号
      <textarea id="OrderId" class="layui-textarea"   ></textarea>
      评论内容
    <textarea class="layui-textarea" id="Review" style="display:flex;" >  

    </textarea>
     
    <div class="site-demo-button" style="margin-top: 20px;">
      <button class="layui-btn" data-type="content" id="Print">发表意见</button>
    </div>

  </div>
  
 </body>
 <script src="layui/layui.js"></script>
 <script src="js/CustomerRemark.js"></script>
</html>