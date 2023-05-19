package com.vodka.Controller;

import com.vodka.Service.ProcessAdmin;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vodka
 * @date 2023/01//22:42
 */

@Controller
@ResponseBody
public class SuperInfoController {

     private  ProcessAdmin processAdmin = new ProcessAdmin();

     //管理员用户表数据
     @RequestMapping(value = "/AdminTable",produces="text/html;charset=utf-8")
      public String GetAdminTable(){
        return processAdmin.GetAdminTableData();
      }

//      //管理员数据编辑过后，发送至后端，保存到数据库
//    @RequestMapping(value = "/EditAdminInfo",method ={ RequestMethod.GET ,RequestMethod.POST })
//    public Integer EditAdminTable(HttpServletRequest request, HttpServletResponse response){
//         return  processAdmin.EditAdminInfo(request,response);
//     }

     //管理员数据编辑过后，发送至后端，保存到数据库
    @RequestMapping(value = "/AdminEditPassWord",method ={ RequestMethod.GET ,RequestMethod.POST })
    public String AdminEditPassWord(HttpServletRequest request, HttpServletResponse response){
         return  processAdmin.AdminEditPassWord(request,response);
     }

    //管理员数据编辑过后，发送至后端，保存到数据库
    @RequestMapping(value = "/AdminEditProfile",method ={ RequestMethod.GET ,RequestMethod.POST })
    public String AdminEditProfile(HttpServletRequest request, HttpServletResponse response){
         return  processAdmin.AdminEditProfile(request,response);
     }

     //处理管理员待删除数据
    @RequestMapping(value = "/DeleteAdminInfo",method={RequestMethod.GET,RequestMethod.POST})
    public  Integer DeleteAdminInfo(HttpServletRequest request, HttpServletResponse response){
         return processAdmin.DeleteAdminInfo(request,response);
    }

     //添加管理员待数据
    @RequestMapping(value = "/AddAdminInfo",method={RequestMethod.GET,RequestMethod.POST})
    public  Integer AddAdminInfo(HttpServletRequest request, HttpServletResponse response){
         return processAdmin.AddAdminInfo(request,response);
    }

}