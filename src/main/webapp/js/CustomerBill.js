layui.use(['table','jquery'],function(){
     var table = layui.table;
        var $ = layui.jquery;

         //渲染用户账单表
        table.render({
                    elem: '#CustomerBillInfo'
                   ,url: '/CustomerBillTable' //数据接口
                   ,page: true //开启分页
                   ,method: 'post'
                   ,width: 1250
                   ,height: 640 //设置高 度
                   ,limit: 8
                   ,where: {
                       "Name":$("#CustomerName",parent.document).text(),
                       "CustomerAccount":$("#AccountDisplay",parent.document).text()
                    }
                   ,parseData:function (res) {   //解析传回的数据
                        // 最后返回规定的数据格式
                       return {
                           "code": res.code, //解析口状态
                           "msg": res.msg, //解析提示文本
                           "count": res.length,
                           "data": res.data //解析数据列表
                       };
                   }
                   ,cols: [[ //表头
                      {field: 'orderId', title: '账单编号' , width:230  },
                      {field: 'returnId', title: '租/还车账单编号', width:230,sort: true},
                      {field: 'payMethod', title: '支付方式', width:250,sort: true},
                      {field: 'orderMoney', title: '支付金额', width:230,sort: true},
                      {field: 'payTime', title: '支付时间', width:300,sort: true},
                   ]]
               });



})//layui.use




