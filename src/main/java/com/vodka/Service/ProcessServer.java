package com.vodka.Service;

import com.vodka.Dao.GetResult;
import com.vodka.Mapper.*;
import com.vodka.PJO.*;
import com.vodka.Utility.MysqlConnection;
import com.vodka.Utility.TimeUtility;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vodka
 * @date 2023/02//0:22
 */
@Service
public class ProcessServer {
    //    获取Mysql数据库连接
    private  static final SqlSession ss = MysqlConnection.SqlSessionStartUp();
    //获取各数据表的Dao映射类
    public SuperInfoMapper superInfoMapper = ss.getMapper(SuperInfoMapper.class);
    public com.vodka.Mapper.CarInfoMapper CarInfoMapper = ss.getMapper(com.vodka.Mapper.CarInfoMapper.class);
    public com.vodka.Mapper.CustomerCommentInfoMapper CustomerCommentInfoMapper = ss.getMapper(com.vodka.Mapper.CustomerCommentInfoMapper.class);
    public com.vodka.Mapper.CustomerInfoMapper CustomerInfoMapper = ss.getMapper(com.vodka.Mapper.CustomerInfoMapper.class);
    public com.vodka.Mapper.ReturnCarInfoMapper ReturnCarInfoMapper = ss.getMapper(com.vodka.Mapper.ReturnCarInfoMapper.class);
    public OrderInfoMapper OrderInfoMapper = ss.getMapper(OrderInfoMapper.class);
    public RentCarInfoMapper RentCarInfoMapper = ss.getMapper(RentCarInfoMapper.class);
    public ServerInfoMapper ServerInfoMapper = ss.getMapper(ServerInfoMapper.class);
    public TimeUtility timeUtility = new TimeUtility();

    //初始化服务员信息表
    public String GetServerTableData() {

        GetResult getResult = new GetResult();


        return getResult.TableResultProcessing(ServerInfoMapper.GetAllServer());

    }

    //编辑服务员信息
    public Integer EditServerInfo(HttpServletRequest request, HttpServletResponse response) {
        ServerInfo serverInfo = new ServerInfo();
        serverInfo.setServerID(request.getParameter("serverID"));
        serverInfo.setServerName(request.getParameter("serverName"));
        serverInfo.setServerPhone(request.getParameter("serverPhone"));
        serverInfo.setServerSex(request.getParameter("serverSex"));
        serverInfo.setServerRemark(request.getParameter("serverRemark"));

        ss.commit();
        return 200; //状态码
    }


    //删除服务员身份信息
    public Integer DeleteServerInfo(HttpServletRequest request, HttpServletResponse response) {

        ServerInfo ServerInfo = new ServerInfo();

        ServerInfo.setServerID(request.getParameter("serverID"));

        ServerInfo.setServerPhone(request.getParameter("serverPhone"));

        if(1==ServerInfoMapper.DeleteServerInfo(ServerInfo.getServerID())) {
            ss.commit();
            return 200;
        }
        else return 400;

    }



    //服务员界面

    //渲染服务员还车信息表
    public String ServerProcessReturn() {

        ArrayList<ReturnCarInfo> returnCarInfoList = new ArrayList<>();

        GetResult getResult = new GetResult();

        returnCarInfoList = (ArrayList<ReturnCarInfo>) ReturnCarInfoMapper.getAllReturnCarInfo();

        return getResult.TableResultProcessing(returnCarInfoList);

    }

    //服务员编辑还车信息表(修改returnCarInfo,加入orderinfo)
    public Integer ServerEditReturn(HttpServletRequest request, HttpServletResponse response) {
       ReturnCarInfo returnCarInfo = ReturnCarInfoMapper.SearchReturnCarInfo(request.getParameter("ReturnId"));
        RentCarInfo rentCarInfo = RentCarInfoMapper.SearchRentCarInfo(returnCarInfo.getRentId());
       CarInfo carInfo = CarInfoMapper.SearchCarInfo(rentCarInfo.getCarSign());
        OrderInfo orderInfo = new OrderInfo();
       //修改returncarinfo
        returnCarInfo.setPenaltyMoney(Double.valueOf(request.getParameter("PenaltyMoney")));
        returnCarInfo.setEvaluateCar(request.getParameter("EvaluateCarInfo"));
        returnCarInfo.setOperatorId(request.getParameter("OperatorId"));
        returnCarInfo.setAcctuallyReturnCarTime(String.valueOf(timeUtility.GetNowTime()));
        ReturnCarInfoMapper.EditReturnCarInfo(returnCarInfo);

        //修改orderinfo,方便客户查看和处理账单
        if(returnCarInfo.getPenaltyMoney() == 0)  {    //如果归还车辆有损伤，则等顾客缴纳完毕，再加入orderinfo
            //若无损伤，
            //修改车辆状态
            carInfo.setRemark("待租赁");
        }
        //若有损伤，修改车辆转态为维修中
        else carInfo.setRemark("维修中");
        CarInfoMapper.EditCarInfo(carInfo);
        //退押金给顾客
        orderInfo.setReturnId(Integer.valueOf(request.getParameter("ReturnId")));
        orderInfo.setPayMethod("退押金");
        orderInfo.setOrderMoney(rentCarInfo.getDeposit());
        orderInfo.setPayTime(String.valueOf(timeUtility.GetNowTime()));

        rentCarInfo.setRemark("已归还");   //修改租车账单状态
        RentCarInfoMapper.EditRentCarInfo(rentCarInfo);
        OrderInfoMapper.AddOrderInfo(orderInfo);
        ss.commit();
        return 200;
    }


    //服务员删除还车信息表,同时也需要删除总账单信息表
    public Integer ServerDeleteReturn(HttpServletRequest request, HttpServletResponse response) {

        ReturnCarInfo returnCarInfo = ReturnCarInfoMapper.SearchReturnCarInfo(request.getParameter("ReturnId"));
        OrderInfo orderInfo = OrderInfoMapper.SearchOrderInfoByOtherId(String.valueOf(returnCarInfo.getReturnId()));
        ReturnCarInfoMapper.DeleteReturnCarInfo(returnCarInfo.getReturnId());
        OrderInfoMapper.DeleteOrderInfo(orderInfo.getOrderId());
        ss.commit();
        return 200;
    }


    //渲染服务员租车信息表
    public String ServerProcessRent() {
        ArrayList<RentCarInfo> RentCarList = new ArrayList<>();

        GetResult getResult = new GetResult();
        RentCarList = (ArrayList<RentCarInfo>) RentCarInfoMapper.GetAllRentCar();

        return getResult.TableResultProcessing(RentCarList);

    }

    //服务员编辑租车信息表,修改orderinfo，把rentcarinfo里面的某条待处理订单，改变订单状态，加入到还车订单(customerreturncarinfo)
    public Integer ServerEditRent(HttpServletRequest request, HttpServletResponse response) {
        //修改rentCarInfo(租车信息表)
        RentCarInfo RI = RentCarInfoMapper.SearchRentCarInfo(Integer.valueOf(request.getParameter("rentId")));
        RI.setRentMoney(Double.parseDouble(request.getParameter("rentMoney")));
        RI.setRemark(request.getParameter("remark"));
        RI.setDeposit(Double.parseDouble(request.getParameter("deposit")));
        RI.setCustomerAccount(request.getParameter("customerAccount"));
        RI.setOperator(request.getParameter("operator"));
        RentCarInfoMapper.EditRentCarInfo(RI);

        //把正在使用的账单添加到还车信息表
        if(request.getParameter("remark").equals("正在使用")){
            ReturnCarInfo returnCarInfo = new ReturnCarInfo();
            returnCarInfo.setRentId(RI.getRentId());

            returnCarInfo.setRentMoney(String.valueOf(RI.getRentMoney()));
            returnCarInfo.setPenaltyMoney(Double.valueOf(0));

            ReturnCarInfoMapper.AddReturnCarInfo(returnCarInfo);
        }

        ss.commit();   //数据持久化
        return 200;
    }


    //服务员删除租车信息表某记录（根据账单状态，进行相关处理）
    public Integer ServerDeleteRent(HttpServletRequest request, HttpServletResponse response) {
        RentCarInfo rentCarInfo = RentCarInfoMapper.SearchRentCarInfo(Integer.valueOf(request.getParameter("rentId")));
        CarInfo carInfo = CarInfoMapper.SearchCarInfo(rentCarInfo.getCarSign());

        //账单状态为已归还，还车账单也同时删除，
        if(rentCarInfo.getRemark().equals("已归还")){
            ReturnCarInfoMapper.DeleteReturnCarInfoByRentId(rentCarInfo.getRentId());
        }
        //若订单状态为'待使用'，即顾客已支付，又想临时取消的，则删除对应租车订单，车辆的状态;
        //退租金，退押金
        if(rentCarInfo.getRemark().equals("待使用")){
           carInfo.setRemark("待租赁");
           CarInfoMapper.EditCarInfo(carInfo);
           //将退押金，租金记录到账单
            OrderInfo o1 = new OrderInfo();
            o1.setReturnId(rentCarInfo.getRentId());
            o1.setPayMethod("租金退还");
            o1.setOrderMoney(rentCarInfo.getRentMoney());
            o1.setPayTime(String.valueOf(timeUtility.GetNowTime()));

            OrderInfo o2 = new OrderInfo();
            o2.setReturnId(rentCarInfo.getRentId());
            o2.setPayMethod("押金退还");
            o2.setOrderMoney(rentCarInfo.getDeposit());
            o2.setPayTime(String.valueOf(timeUtility.GetNowTime()));
            OrderInfoMapper.AddOrderInfo(o1);
            OrderInfoMapper.AddOrderInfo(o2);
        }
        //若订单状态为‘待支付’，则修改车辆状态即可
        if(rentCarInfo.getRemark().equals("待支付")){
            carInfo.setRemark("待租赁");
            CarInfoMapper.EditCarInfo(carInfo);
        }

        //删除租车订单
       RentCarInfoMapper.DeleteRentCarInfo(rentCarInfo.getRentId());
       ss.commit();
       return 200;
    }


    //管理员渲染账单界面
    public String ServerProcessOrder() {
        ArrayList<OrderInfo> OrderList = new ArrayList<>();
        GetResult getResult = new GetResult();

       OrderList = (ArrayList<OrderInfo>) OrderInfoMapper.GetAllOrder();
        return getResult.TableResultProcessing(OrderList);
    }

    //管理员删除账单记录
    public Integer ServerDeleteOrder(HttpServletRequest request,HttpServletResponse response) {
        OrderInfo orderInfo = OrderInfoMapper.SearchOrderInfo(request.getParameter("orderId"));
        ReturnCarInfo returnCarInfo = ReturnCarInfoMapper.SearchReturnCarInfo(request.getParameter("returnId"));

        Integer Sign = OrderInfoMapper.DeleteOrderInfo(orderInfo.getOrderId());
        ReturnCarInfoMapper.DeleteReturnCarInfo(returnCarInfo.getReturnId());
        RentCarInfoMapper.DeleteRentCarInfo(returnCarInfo.getRentId());
        ss.commit();
        return 200;
    }

    //管理员编辑账单
    public Integer ServerEditOrder(HttpServletRequest request,HttpServletResponse response) {
        OrderInfo orderInfo = OrderInfoMapper.SearchOrderInfo(request.getParameter("orderId"));
        System.out.println(orderInfo.toString());
        orderInfo.setReturnId(Integer.valueOf(request.getParameter("returnId")));
        orderInfo.setPayMethod(request.getParameter("payMethod"));
        orderInfo.setOrderMoney(Double.parseDouble(request.getParameter("money")));
        orderInfo.setPayTime(request.getParameter("payTime"));

        if(OrderInfoMapper.EditOrderInfo(orderInfo) == 1){
            ss.commit();
            return 200;  //修改Orderinfo
        }
        return 400;
    }

    public Integer AddServerInfo(HttpServletRequest request, HttpServletResponse response) {
       ServerInfo serverInfo = new ServerInfo();    //获取http协议携带的数据

       serverInfo.setServerName(request.getParameter("nickName"));
       serverInfo.setServerID(request.getParameter("account"));
       serverInfo.setServerSex(request.getParameter("gender"));
       serverInfo.setServerRemark(request.getParameter("remark"));
       serverInfo.setServerPhone(request.getParameter("phone"));
       serverInfo.setPassWord(request.getParameter("passWordOne"));
       ServerInfoMapper.AddServerInfo(serverInfo);
        ss.commit();
        return 200;
    }

    //编辑服务员信息
    public String ServerEditProfile(HttpServletRequest request, HttpServletResponse response) {
        String Name = request.getParameter("Name");
        String Phone = request.getParameter("Phone");
        String Gender = request.getParameter("Gender");
        String Remark = request.getParameter("Remark");
        String Account = request.getParameter("Account");
        ServerInfo SI = ServerInfoMapper.SearchServerInfo(Account);

        if(SI.equals(null)) return "201"; //找不到该对象
        else{
            SI.setServerName(Name);
            SI.setServerPhone(Phone);
            SI.setServerSex(Gender);
            SI.setServerRemark(Remark);
            Integer Sign = ServerInfoMapper.EditServerInfo(SI);
            ss.commit(); //提交
            if(Sign == 1)  return "200";
            else return "202";  //修改失败
        }
    }

    public String ServerEditPassWord(HttpServletRequest request, HttpServletResponse response) {
        String Account = request.getParameter("Account");
        String OldPassWord = request.getParameter("OldPassWord");
        String NewPassWord = request.getParameter("NewPassWord");
        String EnsurePassWord = request.getParameter("EnsurePassWord");

        ServerInfo SI = ServerInfoMapper.SearchServerInfo(Account);
        if(!OldPassWord.equals(SI.getPassWord())) return "201";   //旧密码不正确
        else{
            SI.setPassWord(NewPassWord);
            int Sign = ServerInfoMapper.EditServerPassWord(SI);
            if(Sign == 1 ){
                ss.commit();  //提交
                return "200";
            }
            else return "400";
        }
    }

    //搜寻指定账单
    public String SearchRent(HttpServletRequest request, HttpServletResponse response) {
        List<OrderInfo> orderInfoList = new ArrayList<>();
        GetResult getResult = new GetResult();

        String orderId = request.getParameter("orderId");
        String RentOrReturnId = request.getParameter("RentOrReturnId");
        String moneyStatus = request.getParameter("moneyStatus");

        orderInfoList = OrderInfoMapper.SearchOrderInfoByCondition(orderId,RentOrReturnId,moneyStatus);
        return getResult.TableResultProcessing(orderInfoList);
    }
}
