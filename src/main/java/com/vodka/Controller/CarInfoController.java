package com.vodka.Controller;

import com.vodka.Service.ProcessCar;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@ResponseBody
@Controller
public class CarInfoController{
    private ProcessCar processCar = new ProcessCar();

    //车辆信息
    //顾客身份信息表
    @RequestMapping(value = "/CarTable",produces = "text/html;charset=utf8")
    public String GetCarTable(HttpServletRequest request,HttpServletResponse response){
        return processCar.GetCarTableData(request,response);
    }

    //管理员数据编辑过后，发送至后端，保存到数据库
    @RequestMapping(value = "/EditCar",method ={RequestMethod.GET ,RequestMethod.POST })
    public Integer EditCarTable(HttpServletRequest request, HttpServletResponse response){
        return  processCar.EditCarInfo(request,response);

    }

    //新增数据编辑过后，发送至后端，保存到数据库
    @RequestMapping(value = "/AddCar",method ={RequestMethod.GET ,RequestMethod.POST })
    public Integer AddCarTable(HttpServletRequest request, HttpServletResponse response){
        return  processCar.AddCarInfo(request,response);
    }

    //删除车辆信息数据
    @RequestMapping(value = "/DeleteCar",method={RequestMethod.GET,RequestMethod.POST})
    public  Integer DeleteCarTable(HttpServletRequest request, HttpServletResponse response){
        return processCar.DeleteCarInfo(request,response);
    }

//租车信息表
    //渲染服务员界面的租车信息表
    @RequestMapping(value = "/RentCarTable",produces = "text/html;charset=utf8",method={RequestMethod.GET,RequestMethod.POST})
    public String RentCarInfo(HttpServletRequest request,HttpServletResponse response){
        return processCar.GetRentCarInfo(request,response);
    }



    //顾客订购信息表
    @RequestMapping(value = "/CustomerPurchase",produces = "text/html;charset=utf8",method={RequestMethod.GET,RequestMethod.POST})
    public Integer CustomerOrder(HttpServletRequest request,HttpServletResponse response){
        return processCar.OrderInfo(request,response);

    }

    //顾客还车信息表渲染
    @RequestMapping(value = "/CustomerReturn",produces="application/json;charset=utf-8",method={RequestMethod.GET,RequestMethod.POST})
    public String CustomerReturnOrder(HttpServletRequest request, HttpServletResponse response){
        return processCar.ReturnOrderInfo(request,response);

    }

    //顾客还车处理，修改还车信息表
    @RequestMapping(value = "/ReturnFunction",method={RequestMethod.GET,RequestMethod.POST})
    public int CustomerReturn(HttpServletRequest request, HttpServletResponse response){
        return processCar.ReturnProcess(request,response);
    }

    //顾客缴纳赔偿金，并将该订单记录进orderinfo
    @RequestMapping(value = "/CustomerPayPenaltyMoney",method={RequestMethod.GET,RequestMethod.POST})
    public Integer CustomerPayPenaltyMoney(HttpServletRequest request, HttpServletResponse response){
        return processCar.CustomerPayPenalty(request,response);
    }

    //查询车辆
    @RequestMapping(value = "/SearchCar",produces = "text/html;charset=utf8",method={RequestMethod.GET,RequestMethod.POST})
    public String SearchCar(HttpServletRequest request, HttpServletResponse response){
        return processCar.SearchCar(request,response);
    }
}
