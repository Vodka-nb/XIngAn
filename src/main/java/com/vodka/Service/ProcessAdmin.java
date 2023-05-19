package com.vodka.Service;

import com.vodka.Mapper.*;
import com.vodka.PJO.ServerInfo;
import com.vodka.PJO.SuperInfo;
import com.vodka.Dao.GetResult;
import com.vodka.Utility.MysqlConnection;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class ProcessAdmin {
    //    获取Mysql数据库连接
    private  static final SqlSession ss = MysqlConnection.SqlSessionStartUp();
    //获取各数据表的Dao映射类
    public SuperInfoMapper superInfoMapper = ss.getMapper(SuperInfoMapper.class);
    public com.vodka.Mapper.CarInfoMapper CarInfoMapper = ss.getMapper(CarInfoMapper.class);
    public com.vodka.Mapper.CustomerCommentInfoMapper CustomerCommentInfoMapper = ss.getMapper(CustomerCommentInfoMapper.class);
    public com.vodka.Mapper.CustomerInfoMapper CustomerInfoMapper = ss.getMapper(CustomerInfoMapper.class);
    public com.vodka.Mapper.ReturnCarInfoMapper ReturnCarInfoMapper = ss.getMapper(com.vodka.Mapper.ReturnCarInfoMapper.class);
    public OrderInfoMapper OrderInfoMapper = ss.getMapper(OrderInfoMapper.class);
    public RentCarInfoMapper RentCarInfoMapper = ss.getMapper(RentCarInfoMapper.class);
    public ServerInfoMapper ServerInfoMapper = ss.getMapper(ServerInfoMapper.class);

    //管理员数据，用于表格初始化
    public String GetAdminTableData(){
        GetResult getResult = new GetResult();
        List<SuperInfo> superInfoList = superInfoMapper.GetAllSuper();
        return getResult.TableResultProcessing(superInfoList);
    }

    //保存管理员列表经过编辑的数据
    public Integer EditAdminInfo(HttpServletRequest request, HttpServletResponse response){
        SuperInfo SuperInfo = new SuperInfo();

        String nickName = request.getParameter("nickName");
        String gender = request.getParameter("gender");
        String remark = request.getParameter("remark");
        String phone = request.getParameter("phone");
       int code = superInfoMapper.EditAdminInfo(nickName,gender,remark,phone,"");

       if(code == 1){
           ss.commit();
           return 200; //状态码
       }
       else return 401;
    }

    //处理待删除的管理员数据
    public Integer DeleteAdminInfo(HttpServletRequest request , HttpServletResponse response){
        SuperInfo SuperInfo = new SuperInfo();
        String phone = request.getParameter("phone");
        String superAccount = request.getParameter("superAccount");
        superInfoMapper.DeleteAdminInfo(phone,superAccount);
        ss.commit();
        return 200;
    }

    public Integer AddAdminInfo(HttpServletRequest request, HttpServletResponse response) {
        SuperInfo superInfo = new SuperInfo();    //获取http协议携带的数据

        superInfo.setNickName(request.getParameter("nickName"));
        superInfo.setSuperAccount(request.getParameter("account"));
        superInfo.setGender(request.getParameter("gender"));
        superInfo.setRemark(request.getParameter("remark"));
        superInfo.setPhone(request.getParameter("phone"));
        superInfo.setPassWord(request.getParameter("passWordOne"));

        superInfoMapper.AddAdminInfo(superInfo);
        ss.commit();
        return 200;
    }

    //管理员编辑个人信息
    public String AdminEditProfile(HttpServletRequest request, HttpServletResponse response) {
        String Name = request.getParameter("Name");
        String Phone = request.getParameter("Phone");
        String Gender = request.getParameter("Gender");
        String Remark = request.getParameter("Remark");
        String Account = request.getParameter("Account");
        SuperInfo SI = superInfoMapper.SearchAdminInfo(Account);

        if(SI.equals(null)) return "201"; //找不到该对象
        else{
            SI.setNickName(Name);
            SI.setPhone(Phone);
            SI.setGender(Gender);
            SI.setRemark(Remark);

            int Sign = superInfoMapper.EditAdminInfo(SI.getNickName(),SI.getGender(),SI.getRemark(),SI.getPhone(),SI.getSuperAccount());

            ss.commit(); //提交
            if(Sign == 1)  return "200";
            else return "202";  //修改失败
        }
    }

    //管理员修改密码
    public String AdminEditPassWord(HttpServletRequest request, HttpServletResponse response) {
        String Account = request.getParameter("Account");
        String OldPassWord = request.getParameter("OldPassWord");
        String NewPassWord = request.getParameter("NewPassWord");
        String EnsurePassWord = request.getParameter("EnsurePassWord");

        SuperInfo SI = superInfoMapper.SearchAdminInfo(Account);
        if(!OldPassWord.equals(SI.getPassWord())) return "201";   //旧密码不正确
        else{
            SI.setPassWord(NewPassWord);
            int Sign = superInfoMapper.EditAdminPassWord(SI);
            if(Sign == 1 ){
                ss.commit();  //提交
                return "200";
            }
            else return "400";
        }
    }
}
