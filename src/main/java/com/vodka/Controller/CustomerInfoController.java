package com.vodka.Controller;

import com.vodka.Service.ProcessCar;
import com.vodka.Service.ProcessCustomer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@ResponseBody
public class CustomerInfoController {
    private ProcessCustomer processCustomer = new ProcessCustomer();
    private ProcessCar processCar = new ProcessCar();
    //顾客身份信息表
    @RequestMapping(value = "/CustomerTable",produces="text/html;charset=utf-8")
    public String GetCustomerTable(){
        return processCustomer.GetCustomerTableData();
    }

 //按指定条件查找顾客身份信息表
    @RequestMapping(value = "/SearchUser",produces="text/html;charset=utf-8")
    public String SearchUser(HttpServletRequest request, HttpServletResponse response){
        return processCustomer.GetCustomerByCondition(request,  response);
    }

//顾客身份信息表
    @RequestMapping(value = "/CustomerEditProfile",produces="text/html;charset=utf-8")
    public String CustomerEditProfile(HttpServletRequest request, HttpServletResponse response){
        return processCustomer.CustomerEditProfile( request,  response);
    }

//修改顾客密码信息
    @RequestMapping(value = "/CustomerEditPassWord",produces="text/html;charset=utf-8")
    public String CustomerEditPassWord(HttpServletRequest request, HttpServletResponse response){
        return processCustomer.CustomerEditPassWord( request,  response);
    }

    //顾客注册
    @RequestMapping(value = "/CustomerRegister",produces="text/html;charset=utf-8")
    public String CustomerRegister(HttpServletRequest request, HttpServletResponse response){
        return processCustomer.CustomerRegister( request, response);
    }

    //管理员数据编辑过后，发送至后端，保存到数据库
    @RequestMapping(value = "/EditCustomerInfo",method ={RequestMethod.GET ,RequestMethod.POST })
    public Integer EditCustomerTable(HttpServletRequest request, HttpServletResponse response){
        return  processCustomer.EditCustomerInfo(request,response);
    }

    //处理管理员待删除数据
    @RequestMapping(value = "/DeleteCustomerInfo",method={RequestMethod.GET,RequestMethod.POST})
    public  Integer DeleteCustomerInfo(HttpServletRequest request, HttpServletResponse response){
        return processCustomer.DeleteCustomerInfo(request,response);
    }

    //渲染用户账单
    @RequestMapping(value = "/CustomerBillTable",produces="application/json;charset=utf-8",method={RequestMethod.GET,RequestMethod.POST})
    public  String CustomerBillInfo(HttpServletRequest request, HttpServletResponse response){
        return processCustomer.CustomerBill(request,response);
    }

    //取消(删除)用户账单某条记录
    @RequestMapping(value = "/CancelCustomerRentOrder",method={RequestMethod.GET,RequestMethod.POST})
    public  Integer CancelCustomerBillInfo(HttpServletRequest request, HttpServletResponse response){
        return processCustomer.CancelCustomerBill(request,response);
    }

    //用户支付账单
    @RequestMapping(value = "/CustomerPayRentOrder",method={RequestMethod.GET,RequestMethod.POST})
    public  Integer CustomerPayRentOrderInfo(HttpServletRequest request, HttpServletResponse response){
        return processCustomer.CustomerPayRentOrder(request,response);
    }

    //渲染顾客界面的租车信息表（根据账号，来获取对应账单）
    @RequestMapping(value = "/CustomerRentOrder",produces = "text/html;charset=utf8",method={RequestMethod.GET,RequestMethod.POST})
    public String CustomerRentCarInfo(HttpServletRequest request,HttpServletResponse response){
        return processCar.GetCustomerRentCarInfo(request,response);
    }
}