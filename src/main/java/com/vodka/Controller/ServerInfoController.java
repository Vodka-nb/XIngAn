package com.vodka.Controller;
import com.vodka.Service.ProcessServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@ResponseBody
public class ServerInfoController {
    private ProcessServer processServer = new ProcessServer();

    //管理员界面，服务员身份信息表格数据
    @RequestMapping(value = "/ServerTable",produces="text/html;charset=utf-8",method ={RequestMethod.GET ,RequestMethod.POST })
    public String GetServerTable(){
        return processServer.GetServerTableData();
    }

    //服务员身份信息编辑过后，发送至后端，保存到数据库
    @RequestMapping(value = "/EditServerInfo",method ={ RequestMethod.GET , RequestMethod.POST })
    public Integer EditServerTable(HttpServletRequest request, HttpServletResponse response){
        return  processServer.EditServerInfo(request,response);
    }

    //管理界面，服务员待删除数据
    @RequestMapping(value = "/DeleteServerInfo",method={RequestMethod.GET,RequestMethod.POST})
    public  Integer DeleteServerInfo(HttpServletRequest request, HttpServletResponse response){
        return processServer.DeleteServerInfo(request,response);
    }

    //管理界面，添加服务员数据
    @RequestMapping(value = "/AddServerInfo",method={RequestMethod.GET,RequestMethod.POST})
    public  Integer AddServerInfo(HttpServletRequest request, HttpServletResponse response){
        return processServer.AddServerInfo(request,response);
    }
}
