package com.vodka.Controller;

import com.vodka.Mapper.*;
import com.vodka.PJO.CustomerInfo;
import com.vodka.PJO.ServerInfo;
import com.vodka.PJO.SuperInfo;
import com.vodka.Utility.MysqlConnection;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class PageDispatch {
    //    获取Mysql数据库连接
    private  static final SqlSession ss = MysqlConnection.SqlSessionStartUp();
    public SuperInfoMapper superInfoMapper = ss.getMapper(SuperInfoMapper.class);
    public com.vodka.Mapper.CarInfoMapper CarInfoMapper = ss.getMapper(com.vodka.Mapper.CarInfoMapper.class);
    public com.vodka.Mapper.CustomerCommentInfoMapper CustomerCommentInfoMapper = ss.getMapper(com.vodka.Mapper.CustomerCommentInfoMapper.class);
    public com.vodka.Mapper.CustomerInfoMapper CustomerInfoMapper = ss.getMapper(com.vodka.Mapper.CustomerInfoMapper.class);
    public com.vodka.Mapper.ReturnCarInfoMapper ReturnCarInfoMapper = ss.getMapper(com.vodka.Mapper.ReturnCarInfoMapper.class);
    public OrderInfoMapper OrderInfoMapper = ss.getMapper(OrderInfoMapper.class);
    public RentCarInfoMapper RentCarInfoMapper = ss.getMapper(RentCarInfoMapper.class);
    public ServerInfoMapper ServerInfoMapper = ss.getMapper(ServerInfoMapper.class);
    public SuperInfoMapper SuperInfoMapper = ss.getMapper(SuperInfoMapper.class);

    //页面控制跳转
    @RequestMapping("/AdminPage")
    public String GetAdminPage(HttpServletRequest req, HttpServletResponse res){
        return "/Admin";
    }

    @RequestMapping("/CustomerPage")
    public  String GetCustomerPage(HttpServletRequest req, HttpServletResponse res){
        return "/Customer";
    }

    @RequestMapping("/ServerPage")
    public  String GetServerPage(HttpServletRequest req, HttpServletResponse res){
        return "/Server";
    }

    /*登录页面跳转判断：1. 是否有该用户，若有用户，则判断密码，密码正确则登录成功，否则提示前台密码或账号错误。
    *                   2. 没有该用户则注册，接着可以登录。
    * */
    @RequestMapping("/Admin")
    public void LoginAdminPage(HttpServletRequest req, HttpServletResponse res){
        HttpSession session = req.getSession();

        //获取管理员信息
        String Identity = req.getParameter("identity");
        String UserAccount = String.valueOf(req.getParameter("userAccount"));
        String PassWord = String.valueOf(req.getParameter("password"));

        //判断管理员信息
        SuperInfo superInfo = SuperInfoMapper.SearchAdminInfo(UserAccount);

        if(superInfo==null) {
            res.setHeader("DispatchMsg","hold");   //不跳转页面
            res.setHeader("AccountError","Account had not register Or Info error!");
        }
        else if(!PassWord.equals(superInfo.getPassWord())) {
            res.setHeader("PassWordError", "Password error!");
            res.setHeader("DispatchMsg","hold");

        }
        else{
            session.setAttribute("SuperName",superInfo.getNickName());
            session.setAttribute("SuperAccount",superInfo.getSuperAccount());
            res.setHeader("DispatchMsg", "REDIRECT");//告诉ajax这是重定向
            res.setHeader("CONTEXTPATH", "/AdminPage");//重定向地址
        }

    }

    //若登录身份为顾客
    @RequestMapping("/Customer")
    public  void LoginCustomerPage(HttpServletRequest req, HttpServletResponse res){
        HttpSession session = req.getSession();

        //获取顾客信息
        String Identity = req.getParameter("identity");
        String UserAccount = String.valueOf(req.getParameter("userAccount"));
        String PassWord = String.valueOf(req.getParameter("password"));

        //判断顾客信息
        CustomerInfo customerinfo = CustomerInfoMapper.SearchCustomerInfo(UserAccount);

        if(customerinfo==null) {

            res.setHeader("DispatchMsg","hold");   //不跳转页面
            res.setHeader("AccountError","Account had not register Or Info error!");
        }
        else if(!PassWord.equals(customerinfo.getPassWord())) {
            res.setHeader("PassWordError", "Password error!");
            res.setHeader("DispatchMsg","hold");
        }
        else{
            session.setAttribute("CustomerName",customerinfo.getRealName());
            session.setAttribute("CustomerAccount",customerinfo.getCustomerID());
            res.setHeader("DispatchMsg", "REDIRECT");//告诉ajax这是重定向
            res.setHeader("CONTEXTPATH", "/CustomerPage");//重定向地址
        }
    }

    //若登录身份为服务员
    @RequestMapping("/Server")
    public  void LoginServerPage(HttpServletRequest req, HttpServletResponse res){
        HttpSession session = req.getSession();

        //获取服务员信息
        String Identity = req.getParameter("identity");
        String UserAccount = String.valueOf(req.getParameter("userAccount"));
        String PassWord = String.valueOf(req.getParameter("password"));

        //判断服务员信息
        ServerInfo serverInfo = ServerInfoMapper.SearchServerInfo(UserAccount);

        if(serverInfo==null) {
            res.setHeader("DispatchMsg","hold");   //不跳转页面
            res.setHeader("AccountError","Account had not register Or Info error!");
        }
        else if(!PassWord.equals(serverInfo.getPassWord())) {
            res.setHeader("PassWordError", "Password error!");
            res.setHeader("DispatchMsg","hold");
        }
        else{
            session.setAttribute("ServerName",serverInfo.getServerName());
            session.setAttribute("ServerAccount",serverInfo.getServerID());
            res.setHeader("DispatchMsg", "REDIRECT");//告诉ajax这是重定向
            res.setHeader("CONTEXTPATH", "/ServerPage");//重定向地址
        }
    }

}
