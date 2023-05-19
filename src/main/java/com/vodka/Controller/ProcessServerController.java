package com.vodka.Controller;

import com.vodka.Service.ProcessServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Vodka
 * @date 2023/02//0:08
 */
@ResponseBody
@Controller
public class ProcessServerController {

    private ProcessServer processServer = new ProcessServer();

    //服务员处理用户还车订单
    @RequestMapping(value = "/ServerProcessReturn",produces="text/html;charset=utf-8",method ={RequestMethod.GET ,RequestMethod.POST })
    public String ServerProcessReturnBill()
    {
        return processServer.ServerProcessReturn();

    }

    //编辑服务员信息
    @RequestMapping(value = "/ServerEditProfile",produces="text/html;charset=utf-8",method ={RequestMethod.GET ,RequestMethod.POST })
    public String ServerEditProfile(HttpServletRequest request, HttpServletResponse response)
    {
        return processServer.ServerEditProfile(request, response);

    }

    //编辑服务员密码
    @RequestMapping(value = "/ServerEditPassWord",produces="text/html;charset=utf-8",method ={RequestMethod.GET ,RequestMethod.POST })
    public String ServerEditPassWord(HttpServletRequest request, HttpServletResponse response)
    {
        return processServer.ServerEditPassWord(request, response);

    }

    //服务员编辑还车订单
    @RequestMapping(value = "/ServerEditReturn",method ={RequestMethod.GET ,RequestMethod.POST })
    public Integer ServerEditReturnBill(HttpServletRequest request, HttpServletResponse response)
    {
        return processServer.ServerEditReturn(request, response);

    }

    //服务员删除还车记录
    @RequestMapping(value = "/ServerDeleteReturn",method ={RequestMethod.GET ,RequestMethod.POST })
    public Integer ServerDeleteReturnBill(HttpServletRequest request, HttpServletResponse response)
    {
        return processServer.ServerDeleteReturn(request, response);

    }
    //
    //服务员处理用户租车订单
    @RequestMapping(value = "/ServerProcessRent",produces="text/html;charset=utf-8",method ={RequestMethod.GET ,RequestMethod.POST })
    public String ServerProcessRentBill(HttpServletRequest request,HttpServletResponse response)
    {
        return processServer.ServerProcessRent();

    }

    //服务员编辑租车订单
    @RequestMapping(value = "/ServerEditRentInfo",method ={RequestMethod.GET ,RequestMethod.POST })
    public Integer ServerEditRentBill(HttpServletRequest request, HttpServletResponse response)
    {
        return processServer.ServerEditRent(request, response);
    }

    //服务员删除租车记录
    @RequestMapping(value = "/ServerDeleteRent",method ={RequestMethod.GET ,RequestMethod.POST })
    public Integer ServerDeleteRentBill(HttpServletRequest request, HttpServletResponse response)
    {
        return processServer.ServerDeleteRent(request, response);

    }

    //服务员渲染账单记录
    @RequestMapping(value = "/ServerProcessOrder",produces="text/html;charset=utf-8",method ={RequestMethod.GET ,RequestMethod.POST })
    public String ServerProcessOrder(HttpServletRequest request,HttpServletResponse response)
    {
        return processServer.ServerProcessOrder();
    }

    //服务员编辑账单记录
    @RequestMapping(value = "/ServerEditOrder",method ={RequestMethod.GET ,RequestMethod.POST })
    public Integer ServerEditOrder(HttpServletRequest request,HttpServletResponse response)
    {
        return processServer.ServerEditOrder(request,response);
    }

    //服务员删除账单记录
    @RequestMapping(value = "/ServerDeleteOrder",method ={RequestMethod.GET ,RequestMethod.POST })
    public Integer ServerOrder(HttpServletRequest request,HttpServletResponse response)
    {
        return processServer.ServerDeleteOrder(request,response);
    }

    //查询账单
    @RequestMapping(value = "/SearchRent",produces = "text/html;charset=utf8",method={RequestMethod.GET,RequestMethod.POST})
    public String SearchCar(HttpServletRequest request, HttpServletResponse response){
        return processServer.SearchRent(request,response);
    }
}
