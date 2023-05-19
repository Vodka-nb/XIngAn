package com.vodka.Service;

import com.vodka.Dao.GetResult;
import com.vodka.Mapper.*;
import com.vodka.PJO.*;
import com.vodka.PJO.CustomerInfo;
import com.vodka.Utility.MysqlConnection;
import com.vodka.Utility.TimeUtility;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProcessCustomer {
    //    获取Mysql数据库连接
    private  static final SqlSession ss = MysqlConnection.SqlSessionStartUp();
    //    获取各数据表的Dao映射类
    public SuperInfoMapper superInfoMapper = ss.getMapper(SuperInfoMapper.class);
    public CarInfoMapper carInfoMapper = ss.getMapper(CarInfoMapper.class);
    public CustomerCommentInfoMapper customerCommentInfoMapper = ss.getMapper(CustomerCommentInfoMapper.class);
    public CustomerInfoMapper customerInfoMapper = ss.getMapper(CustomerInfoMapper.class);
    public ReturnCarInfoMapper returnCarInfoMapper = ss.getMapper(ReturnCarInfoMapper.class);
    public OrderInfoMapper orderInfoMapper = ss.getMapper(OrderInfoMapper.class);
    public RentCarInfoMapper rentCarInfoMapper = ss.getMapper(RentCarInfoMapper.class);
    public ServerInfoMapper serverInfoMapper = ss.getMapper(ServerInfoMapper.class);

    public TimeUtility timeUtility = new TimeUtility();

    //渲染顾客数据表
    public String GetCustomerTableData() {
        ArrayList<CustomerInfo> CustomerList = new ArrayList<>();
        GetResult getResult = new GetResult();
        CustomerList = (ArrayList<CustomerInfo>) customerInfoMapper.GetAllCustomer();
        ss.commit();
        return getResult.TableResultProcessing(CustomerList);
    }

    //编辑顾客信息
    public Integer EditCustomerInfo(HttpServletRequest request, HttpServletResponse response) {

        return 200;
    }

    //删除顾客信息
    public Integer DeleteCustomerInfo(HttpServletRequest request, HttpServletResponse response) {
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setCustomerID(request.getParameter("CustomerID"));
        customerInfo.setPhone(request.getParameter("Phone"));

       customerInfoMapper.DeleteCustomerInfo(customerInfo.getCustomerID(),customerInfo.getPhone());
       ss.commit();
        return 200;
    }

    //挑选指定顾客账单信息
    public String CustomerBill(HttpServletRequest request, HttpServletResponse response) {
        List<OrderInfo> orderInfoList = new ArrayList<>();
        List<OrderInfo> OITempList = new ArrayList<>();
        GetResult getResult = new GetResult();
        List<ReturnCarInfo> ReturnCarList = new ArrayList<>();
        List<RentCarInfo> RentCarList = new ArrayList<>();
        ReturnCarInfo returnCarInfo = null;
        OrderInfo orderInfo = null;
        String CustomerName = request.getParameter("Name");
        String CustomerAccount = request.getParameter("CustomerAccount");

        RentCarList = rentCarInfoMapper.GetCustomerRentList(CustomerAccount);

        for( int index = 0 ; index < RentCarList.size(); ++index){
            returnCarInfo  =  returnCarInfoMapper.getUserReturnCarInfo(RentCarList.get(index).getRentId());  //根据租车订单号，搜索每一条还车订单，再逐个添加
            if(returnCarInfo!=null) ReturnCarList.add(returnCarInfo);
        }

        OITempList = orderInfoMapper.GetAllOrder();
        System.out.println(OITempList);
        System.out.println(RentCarList);
        for( int i=  0 ; i< ReturnCarList.size(); ++i) {
            for (int j = 0; j < OITempList.size(); ++j) {
                if (OITempList.get(j).getReturnId().equals(ReturnCarList.get(i).getReturnId()))  //根据还车订单号，搜索每一条订单，再逐个添加
                {
                    orderInfo = OITempList.get(j);
                    if (orderInfo != null) orderInfoList.add(orderInfo);
                }

            }
        }
       return getResult.TableResultProcessing(orderInfoList);
    }

    //取消用户某条租车账单记录(修改车辆信息表状态，删除租车账单，将退押金，订金等录入总账单系统)
    public Integer CancelCustomerBill(HttpServletRequest request, HttpServletResponse response) {
//        修改租车账单
        RentCarInfo RI = rentCarInfoMapper.SearchRentCarInfo(Integer.valueOf(request.getParameter("rentId")));
        CarInfo carInfo = carInfoMapper.SearchCarInfo(RI.getCarSign());

        if(RI.getRemark().equals("待使用")) {  //顾客取消已支付，但未使用的订单，退租金，退押金
            OrderInfo o1 = new OrderInfo();
            o1.setReturnId(RI.getRentId());
            o1.setPayMethod("租金退还");
            o1.setOrderMoney(RI.getRentMoney());
            o1.setPayTime(String.valueOf(timeUtility.GetNowTime()));

            OrderInfo o2 = new OrderInfo();
            o2.setReturnId(RI.getRentId());
            o2.setPayMethod("押金退还");
            o2.setOrderMoney(RI.getDeposit());
            o2.setPayTime(String.valueOf(timeUtility.GetNowTime()));
            orderInfoMapper.AddOrderInfo(o1);
            orderInfoMapper.AddOrderInfo(o2);
        }
        carInfo.setRemark("待租赁");
        carInfoMapper.EditCarInfo(carInfo);  //修改车辆备注为“待租赁”
        //删除租车订单
        rentCarInfoMapper.DeleteRentCarInfo(RI.getRentId());

        ss.commit();
        return 200;
    }

    //用户支付租车账单(修改租车账单状态，并登记到总账单信息表）
    public Integer CustomerPayRentOrder(HttpServletRequest request, HttpServletResponse response) {
        //修改orderinfo的(Remark)
        RentCarInfo RI = rentCarInfoMapper.SearchRentCarInfo(Integer.valueOf(request.getParameter("rentId")));     //获取租车信息
        RI.setRemark("待使用");
        rentCarInfoMapper.EditRentCarInfo(RI);   //更新租车信息表

//        将租金，押金登记到总账单号
        OrderInfo o1 = new OrderInfo();
        o1.setReturnId(RI.getRentId());
        o1.setPayMethod("租金缴纳");
        o1.setOrderMoney(RI.getRentMoney());
        o1.setPayTime(String.valueOf(timeUtility.GetNowTime()));

        OrderInfo o2 = new OrderInfo();
        o2.setReturnId(RI.getRentId());
        o2.setPayMethod("押金缴纳");
        o2.setOrderMoney(RI.getDeposit());
        o2.setPayTime(String.valueOf(timeUtility.GetNowTime()));
        orderInfoMapper.AddOrderInfo(o1);
        orderInfoMapper.AddOrderInfo(o2);

        ss.commit();
        return 200;
    }

    //顾客注册
    public String CustomerRegister(HttpServletRequest req, HttpServletResponse res) {
         CustomerInfo customerInfo = new CustomerInfo();

         customerInfo.setCustomerID(req.getParameter("Account"));
         customerInfo.setPhone(req.getParameter("PhoneNum"));
         customerInfo.setRealName(req.getParameter("Name"));
         customerInfo.setPassWord(req.getParameter("PW1"));
         customerInfo.setIDCardType(req.getParameter("CardType"));
         customerInfo.setIDCardNum(req.getParameter("CardNum"));
         customerInfo.setAddress(req.getParameter("Address"));

         System.out.println(customerInfo.toString());
         System.out.println(customerInfoMapper.SearchCustomerInfo(customerInfo.getPhone()) == null);
         System.out.println(customerInfoMapper.SearchCustomerInfo(customerInfo.getPhone()));

        if(customerInfoMapper.SearchCustomerInfo(customerInfo.getPhone()) != null)  return "401";  //若顾客账号已存在
        else  {
            int sign = customerInfoMapper.AddCustomerInfo(customerInfo);

            ss.commit();  //数据持久化
            if(sign == 1)  return "200";   //添加成功
        }
        return  "400"; // 添加失败
    }

    //顾客租车账单
    public String CustomerRentOrder(HttpServletRequest request, HttpServletResponse response) {
        List<RentCarInfo> orderInfoList = new ArrayList<>();
        GetResult getResult = new GetResult();
        List<RentCarInfo> rentOrderList;

        rentOrderList =  rentCarInfoMapper.GetAllRentCar();
        return getResult.TableResultProcessing(rentOrderList);
    }

    //编辑顾客信息
    public String CustomerEditProfile(HttpServletRequest request, HttpServletResponse response) {
        String RealName = request.getParameter("RealName");
        String IDCardType = request.getParameter("IDCardType");
        String IDCardNum = request.getParameter("IDCardNum");
        String Address = request.getParameter("Address");
        String CustomerAccount = request.getParameter("CustomerAccount");
        CustomerInfo CI = customerInfoMapper.SearchCustomerInfo(CustomerAccount);

        if(CI.equals(null)) return "201"; //找不到该对象
        else{
            CI.setRealName(RealName);
            CI.setIDCardType(IDCardType);
            CI.setIDCardNum(IDCardNum);
            CI.setAddress(Address);
            Integer Sign = customerInfoMapper.EditCustomerInfo(CI);
            ss.commit(); //提交
           if(Sign == 1)  return "200";
           else return "202";  //修改失败
        }
    }

    //顾客修改密码
    public String CustomerEditPassWord(HttpServletRequest request, HttpServletResponse response) {
        String Account = request.getParameter("Account");
        String OldPassWord = request.getParameter("OldPassWord");
        String NewPassWord = request.getParameter("NewPassWord");
        String EnsurePassWord = request.getParameter("EnsurePassWord");

        CustomerInfo CI = customerInfoMapper.SearchCustomerInfo(Account);
        if(!OldPassWord.equals(CI.getPassWord())) return "201";   //旧密码不正确
        else{
            CI.setPassWord(NewPassWord);
            int Sign = customerInfoMapper.EditCustomerPassWord(CI);
            if(Sign == 1 ){
                ss.commit();  //提交
                return "200";
            }
            else return "400";
        }
    }

    public String GetCustomerByCondition(HttpServletRequest request, HttpServletResponse response) {
        String ID = request.getParameter("customerID");
        String Name = request.getParameter("realName");
        ArrayList<CustomerInfo> CustomerList = new ArrayList<>();
        GetResult getResult = new GetResult();

        CustomerList = (ArrayList<CustomerInfo>) customerInfoMapper.GetCustomerByCondition(ID,Name);
        ss.commit();
        return getResult.TableResultProcessing(CustomerList);
    }
}