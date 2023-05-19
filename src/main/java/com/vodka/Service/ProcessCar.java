package com.vodka.Service;

import com.vodka.Dao.GetResult;
import com.vodka.Mapper.*;
import com.vodka.PJO.*;
import com.vodka.PJO.CarInfo;
import com.vodka.Utility.MysqlConnection;
import com.vodka.Utility.PhotoAndBase64;
import com.vodka.Utility.TimeUtility;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.*;


@Service
public class ProcessCar {
    //    获取Mysql数据库连接
    private  static final SqlSession ss = MysqlConnection.SqlSessionStartUp();
    //获取各数据表的Dao映射类
    public SuperInfoMapper superInfoMapper = ss.getMapper(SuperInfoMapper.class);
    public CarInfoMapper carInfoMapper = ss.getMapper(CarInfoMapper.class);
    public CustomerCommentInfoMapper customerCommentInfoMapper = ss.getMapper(CustomerCommentInfoMapper.class);
    public CustomerInfoMapper customerInfoMapper = ss.getMapper(CustomerInfoMapper.class);
    public ReturnCarInfoMapper returnCarInfoMapper = ss.getMapper(ReturnCarInfoMapper.class);
    public OrderInfoMapper orderInfoMapper = ss.getMapper(OrderInfoMapper.class);
    public RentCarInfoMapper rentCarInfoMapper = ss.getMapper(RentCarInfoMapper.class);
    public ServerInfoMapper serverInfoMapper = ss.getMapper(ServerInfoMapper.class);

    TimeUtility timeUtility = new TimeUtility();

    //渲染车辆信息表
    public String GetCarTableData(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<CarInfo> CarList = new ArrayList<>();
        GetResult getResult = new GetResult();

        CarList = (ArrayList<CarInfo>) carInfoMapper.GetAllCar();
        ss.commit();
        return getResult.TableResultProcessing(CarList);
    }

//    编辑车辆数据信息表
    public Integer EditCarInfo(HttpServletRequest request, HttpServletResponse response) {
        CarInfo carInfo = carInfoMapper.SearchCarInfo(request.getParameter("CarSign"));

        carInfo.setCarType(request.getParameter("CarType"));
        carInfo.setCarBrand(request.getParameter("CarBrand"));

        carInfo.setCarSize(Integer.parseInt(request.getParameter("CarSize")));
        carInfo.setPurchaseDate(request.getParameter("PurchaseDate"));
        carInfo.setPurchasePrice(Double.parseDouble(request.getParameter("PurchasePrice")));
        carInfo.setRentPrice(Double.parseDouble(request.getParameter("RentPrice")));
        carInfo.setCarDetail(request.getParameter("CarDetail"));
        carInfo.setRentWay(request.getParameter("RentWay"));
        carInfo.setRemark(request.getParameter("Remark"));

        carInfoMapper.EditCarInfo(carInfo);
        ss.commit();
        return 200;
    }

    //增加车辆信息
    public Integer AddCarInfo(HttpServletRequest request,HttpServletResponse response){
        CarInfo carInfo = new CarInfo();
        PhotoAndBase64 photoAndBase64 = new PhotoAndBase64();  //Base64解码器，将base64格式的数据转存到服务器指定路径

        carInfo.setCarType(request.getParameter("CarType"));
        carInfo.setCarBrand(request.getParameter("CarBrand"));
        carInfo.setCarSign(request.getParameter("CarSign"));
        carInfo.setCarSize(Integer.parseInt(request.getParameter("CarSize")));
        carInfo.setPurchaseDate(request.getParameter("PurchaseDate"));
        carInfo.setPurchasePrice(Double.parseDouble(request.getParameter("PurchasePrice")));
        carInfo.setCarDetail(request.getParameter("CarDetail"));
        carInfo.setRemark(request.getParameter("Remark"));
        carInfo.setRentWay(request.getParameter("RentWay"));
        carInfo.setRentPrice(Double.parseDouble(request.getParameter("RentPrice")));
        String ImgName = request.getParameter("ImgName");
        String ImgBase64 = request.getParameter("ImgBase64");
        carInfo.setImgUrl("img/"+ImgName);

        photoAndBase64.TransformBase64DataToPhoto("D:\\0213\\src\\main\\webapp\\img\\",ImgName,ImgBase64);  //存入服务器的填写绝对路径

        carInfoMapper.AddCarInfo(carInfo);
        ss.commit();
        return 200;
    }

    //删除车辆信息
    public Integer DeleteCarInfo(HttpServletRequest request, HttpServletResponse response) {
        CarInfo CarInfo = new CarInfo();
        CarInfo.setCarSign(request.getParameter("CarSign"));
        carInfoMapper.DeleteCarInfo(CarInfo.getCarSign());
        ss.commit();
         return 200;
    }

    //渲染租车信息表
    public String GetRentCarInfo(HttpServletRequest request, HttpServletResponse response) {
        List<CarInfo> CarList = new ArrayList<>();
        GetResult getResult = new GetResult();
         String Name = request.getParameter("CustomerName");
         System.out.println(Name);
         CarList = carInfoMapper.GetAllCar();
        return getResult.TableResultProcessing(CarList);
    }

    //用户订购(涉及rentcarindo, carinfo)
    public int OrderInfo(HttpServletRequest request, HttpServletResponse response) {
         System.out.println(request.getParameter("phoneNum"));
        RentCarInfo RI = new RentCarInfo();
        RentCarInfo rentcarInfo = new RentCarInfo();
        RI.setCustomerAccount(request.getParameter("customerAccount"));
        RI.setRentCarDate(request.getParameter("rentCarTime"));
        RI.setPredictReturnDate(request.getParameter("returnCarTime"));
        RI.setCustomerAccount(request.getParameter("phoneNum"));
        RI.setRentCarAddress(request.getParameter("rentCarAddress"));
        RI.setCustomerAccount(request.getParameter("phoneNum"));
        RI.setCarSign(request.getParameter("carSign"));
        RI.setRemark("待支付");
        double rentPrice = Double.parseDouble(request.getParameter("rentPrice"));
        String RentWay = request.getParameter("rentWay");
        String CustomerRentWay = request.getParameter("customerRentWay");
        RI.setDeposit(2000);
        double Money;    //计算该租赁周期花费了多少钱

        //修改rentcarinfo
        Integer Period = timeUtility.CalculateDays(Timestamp.valueOf(String.valueOf(RI.getRentCarDate())),Timestamp.valueOf(String.valueOf(RI.getPredictReturnDate()))); //计算租赁周期

        //根据顾客选择租赁方式，计算价格，日租原价，周租九折，月租八折
        switch (CustomerRentWay){
            case "日租":
                Money = Period * rentPrice;
                RI.setRentMoney(Money);
                break;
            case "周租":
                Money = Period * rentPrice * 0.9;
                RI.setRentMoney(Money);
                break;
            case "月租":
                Money = Period * rentPrice * 0.8;
                RI.setRentMoney(Money);
                break;
        }
        System.out.println(RI.toString());
        //添加到rentcarInfo缓存
        rentCarInfoMapper.AddRentCarInfo(RI);
        //修改车辆状态
        CarInfo carInfo = carInfoMapper.SearchCarInfo(request.getParameter("carSign"));
        carInfo.setRemark("已租赁");
        carInfoMapper.EditCarInfo(carInfo);
        ss.commit(); //数据库缓存持久化
        return  200;
    }

    //渲染指定用户还车信息表
    public String ReturnOrderInfo(HttpServletRequest request, HttpServletResponse response) {
        List<ReturnCarInfo> ReturnCarList = new ArrayList<>();
        List<RentCarInfo> RentCarList = new ArrayList<>();
        ReturnCarInfo returnCarInfo = null;
        GetResult getResult = new GetResult();
        String CustomerName = request.getParameter("CustomerName");
        String CustomerAccount = request.getParameter("CustomerAccount");

        RentCarList = rentCarInfoMapper.GetCustomerRentList(CustomerAccount);
        for( int index = 0 ; index < RentCarList.size(); ++index){
           returnCarInfo  =  returnCarInfoMapper.getUserReturnCarInfo(RentCarList.get(index).getRentId());  //根据租车订单号，搜索每一条还车订单，再逐个添加
           if(returnCarInfo!=null) ReturnCarList.add(returnCarInfo);
        }

        return getResult.TableResultProcessing(ReturnCarList);
    }

//    用户归还车辆(修改returncarinfo的归还时间，转交给服务人员进行处理)
    public int ReturnProcess(HttpServletRequest request, HttpServletResponse response) {
         ReturnCarInfo returnCarInfo = returnCarInfoMapper.SearchReturnCarInfo(request.getParameter("returnId"));
         Timestamp nowTime = timeUtility.GetNowTime();

         //修改还车表
       returnCarInfo.setReturnCarAddress(request.getParameter("returnCarAddress"));
       returnCarInfo.setRentId(Integer.valueOf(request.getParameter("rentId")));
       returnCarInfo.setAcctuallyReturnCarTime(String.valueOf(nowTime));
       returnCarInfoMapper.EditReturnCarInfo(returnCarInfo);

       //修改租车信息表的订单状态
         RentCarInfo rentCarInfo = rentCarInfoMapper.SearchRentCarInfo(Integer.valueOf(request.getParameter("rentId")));
         rentCarInfo.setRemark("待评审");   //待服务人员处理还车账单
         rentCarInfoMapper.EditRentCarInfo(rentCarInfo);

         //若用户提前归还车辆，则需在订单记录里面返还部分金额
           CarInfo carInfo = carInfoMapper.SearchCarInfo(rentCarInfo.getCarSign());
           Integer Time = timeUtility.CalculateDays(Timestamp.valueOf(returnCarInfo.getAcctuallyReturnCarTime()),Timestamp.valueOf(rentCarInfo.getRentCarDate()));
           Double ReturnMoney = rentCarInfo.getRentMoney() - Time*carInfo.getRentPrice();   //提前订车，就退还部分租金
        System.out.println(ReturnMoney);
        if(ReturnMoney > 0){
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setReturnId(returnCarInfo.getReturnId());
            orderInfo.setPayMethod("提前还车，退还部分租金");
            orderInfo.setOrderMoney(ReturnMoney);
            orderInfo.setPayTime(String.valueOf(timeUtility.GetNowTime()));

            orderInfoMapper.AddOrderInfo(orderInfo);  //添加到总账单
        }

        ss.commit();
        return 200;
    }

    //用户缴纳赔偿金
    public Integer CustomerPayPenalty(HttpServletRequest req, HttpServletResponse res){
        OrderInfo orderInfo = new OrderInfo();
        ReturnCarInfo returnCarInfo =  returnCarInfoMapper.SearchReturnCarInfo(req.getParameter("returnId"));
        RentCarInfo rentCarInfo =  rentCarInfoMapper.SearchRentCarInfo(returnCarInfo.getRentId());
        CarInfo carInfo = carInfoMapper.SearchCarInfo(rentCarInfo.getCarSign());
        Double Money = returnCarInfo.getPenaltyMoney();
        //判断是否需要缴纳赔偿金
        if(Money != 0) {
            //将赔偿金记录到总账单
            orderInfo.setReturnId(returnCarInfo.getReturnId());
            orderInfo.setPayMethod("赔偿金");
            orderInfo.setOrderMoney(Money);
            orderInfo.setPayTime(String.valueOf(timeUtility.GetNowTime()));


        }
        //此订单完结，且车辆无损，将车辆状态修改为待租赁
        if(returnCarInfo.getEvaluateCar().equals("无损伤")) carInfo.setRemark("待租赁");
        else carInfo.setRemark("维修中");  //等维修完毕，由管理员修改车辆状态
        rentCarInfo.setRemark("已归还");

        carInfoMapper.EditCarInfo(carInfo);
        orderInfoMapper.AddOrderInfo(orderInfo);
        rentCarInfoMapper.EditRentCarInfo(rentCarInfo);
        ss.commit();
        return 200;
    }

    //车辆查询
    public String SearchCar(HttpServletRequest request, HttpServletResponse response) {
        List<CarInfo> carInfoList = new ArrayList<>();
        GetResult getResult = new GetResult();

        String carSign = request.getParameter("carSign");
        String carType = request.getParameter("carType");
        String remark = request.getParameter("remark");

        carInfoList = carInfoMapper.SearchCarInfoByCondition(carSign,carType,remark);
        return getResult.TableResultProcessing(carInfoList);
    }

    //渲染顾客界面的租车信息表
    public String GetCustomerRentCarInfo(HttpServletRequest request, HttpServletResponse response) {
        String CustomerAccount = request.getParameter("CustomerAccount");
        List<RentCarInfo> RentCarList = new ArrayList<>();
        GetResult getResult = new GetResult();

        RentCarList = rentCarInfoMapper.GetCustomerRentList(CustomerAccount);
        ss.commit();
        return getResult.TableResultProcessing(RentCarList);
    }
}
