package com.vodka.Service;
import com.vodka.Dao.GetResult;
import com.vodka.Mapper.*;
import com.vodka.PJO.CustomerCommentInfo;
import com.vodka.Utility.MysqlConnection;
import com.vodka.Utility.TimeUtility;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProcessComment {
    //    获取Mysql数据库连接
    private  static final SqlSession ss = MysqlConnection.SqlSessionStartUp();
    //获取各数据表的Dao映射类
    public SuperInfoMapper superInfoMapper = ss.getMapper(SuperInfoMapper.class);
    public CarInfoMapper CarInfoMapper = ss.getMapper(CarInfoMapper.class);
    public CustomerCommentInfoMapper CustomerCommentInfoMapper = ss.getMapper(CustomerCommentInfoMapper.class);
    public CustomerInfoMapper CustomerInfoMapper = ss.getMapper(CustomerInfoMapper.class);
    public com.vodka.Mapper.ReturnCarInfoMapper ReturnCarInfoMapper = ss.getMapper(com.vodka.Mapper.ReturnCarInfoMapper.class);
    public OrderInfoMapper OrderInfoMapper = ss.getMapper(OrderInfoMapper.class);
    public RentCarInfoMapper RentCarInfoMapper = ss.getMapper(RentCarInfoMapper.class);
    public ServerInfoMapper ServerInfoMapper = ss.getMapper(ServerInfoMapper.class);

    public TimeUtility timeUtility = new TimeUtility();



    //获取评论,传到相应页面，并跳转
    public String GetComment(HttpServletRequest request, HttpSession session) {
        CustomerCommentInfo customerCommentInfo = new CustomerCommentInfo();
        ArrayList<CustomerCommentInfo> CList = new ArrayList<>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");   //规范日期格式

         CList = (ArrayList<CustomerCommentInfo>) CustomerCommentInfoMapper.getAllCommentInfo("1");
//         for(int index = 0 ; index < CList.size(); index++ ){
//              CList.get(index).setCommentTime(df.format(CList.get(index).getCommentTime()));
//         }
         //封装数据进session，再实行跳转
        session.setAttribute("CList",CList);
        session.setAttribute("length",CList.size());
       return "redirect:AdminReview.jsp";

    }

    //新增评论


    //编辑评论
    public Integer EditComment(HttpServletRequest request, HttpServletResponse response){

        return 200;
    }

    //删除评论
    public Integer DeleteComment(HttpServletRequest request, HttpServletResponse response){
        String Content = request.getParameter("Content");
        CustomerCommentInfoMapper.DeleteCustomerCommentInfo(Content);
        ss.commit();
        return 200;
    }

   //审核评论
    public Integer ThroughComment(HttpServletRequest request, HttpServletResponse response){
        String Content = request.getParameter("Content");
        String OrderId = request.getParameter("OrderId");
        String AdminAccount = request.getParameter("SuperAccount");

        CustomerCommentInfo customerCommentInfo = CustomerCommentInfoMapper.SearchCustomerCommentInfoByOrderId(OrderId);

        customerCommentInfo.setStatus("1");
        customerCommentInfo.setAdminAccount(AdminAccount);
        System.out.println(customerCommentInfo);
        CustomerCommentInfoMapper.EditCustomerCommentInfo(customerCommentInfo);

        ss.commit();
        return 200;
    }

    //添加评论
    public Integer AddComment(HttpServletRequest request, HttpServletResponse response) {
        CustomerCommentInfo customerCommentInfo = new CustomerCommentInfo();
        customerCommentInfo.setContent(request.getParameter("RemarkContent"));
        customerCommentInfo.setCommentTime(String.valueOf(timeUtility.GetNowTime()));
        customerCommentInfo.setCustomerAccount(request.getParameter("CustomerAccount"));
        customerCommentInfo.setOrderId(Integer.parseInt(request.getParameter("OrderId")));
       customerCommentInfo.setStatus("0");
        System.out.println(customerCommentInfo.getContent());

        CustomerCommentInfoMapper.AddCustomerCommentInfo(customerCommentInfo);
        ss.commit();
        return 200;
    }

    //管理员获取评论内容
    public String AuditComment(HttpServletRequest request,HttpServletResponse response) {
        GetResult getResult = new GetResult();
        List<CustomerCommentInfo> CList = new ArrayList<>();
        return getResult.TableResultProcessing(CustomerCommentInfoMapper.UnThroughCommentInfo("0"));
    }
}
