package com.vodka.Dao;
import com.vodka.Mapper.*;
import com.vodka.PJO.RentCarInfo;
import com.vodka.Service.ProcessCar;
import com.vodka.Utility.MysqlConnection;
import com.vodka.Utility.PhotoAndBase64;
import com.vodka.Utility.TimeUtility;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class test {
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

    @Test
    public void testCalculateTime(){
        String Data = "666";
        String ImgName ="lansheng.jpg";
        PhotoAndBase64 photoAndBase64 = new PhotoAndBase64();
        photoAndBase64.TransformBase64DataToPhoto("img\\",ImgName,Data);

    }
    @Test
    public void GetRentCarInfoList(){
        List<RentCarInfo> RentCarList = new ArrayList<>();
        GetResult getResult = new GetResult();
        ProcessCar processCar = new ProcessCar();
        RentCarList = rentCarInfoMapper.GetCustomerRentList("4558");
        System.out.println(RentCarList);
    }

//    @Test
//    public void testSelectOne() throws Exception{
//       List<SuperInfo> superInfoList = superInfoMapper.GetAllSuper();
//       for(int i = 0 ; i < superInfoList.size(); ++i)
//       System.out.println(superInfoList.get(i));
//    }
//
//    @Test
//    public void UpdateSuper() throws Exception{
//        int code = superInfoMapper.EditAdminInfo("韦","男","","16845899653");
//        if(code == 1 ) System.out.println("ok");
//        ss.commit();
//    }
//
//
//    @Test
//    public void DeleteSuper() throws Exception{
//        superInfoMapper.DeleteAdminInfo("19476777351","");
//        ss.commit();
//    }
//
//    @Test
//    public void AddSuper() throws Exception{
//        SuperInfo superInfo = new SuperInfo("02126","Judy","15726288902","女","管理员","786789");
//
//        superInfoMapper.AddAdminInfo(superInfo);
//        ss.commit();
//    }
//
//    @Test
//    public void SearchSuper() throws Exception{
//        SuperInfo superInfo;
//
//        System.out.println(superInfoMapper.SearchAdminInfo("02125"));
//        ss.commit();
//    }
//
//    @Test
//    public void AddCar() throws Exception{
//        CarInfo carInfo = new CarInfo();
//
////        carInfo.setRentPrice("176");
////        carInfo.setCarType("Q7");
////        carInfo.setCarBrand("奥迪");
////        carInfo.setCarSize(5);
//
//        carInfoMapper.AddCarInfo(carInfo);
//        ss.commit();
//    }
//
//    @Test
//    public void GetAllCar() throws Exception{
//        List<CarInfo> carInfoList = carInfoMapper.GetAllCar();
//        System.out.println(carInfoList);
//    }
//
//    @Test
//    public void EditCar() throws Exception{
//        CarInfo carInfo = new CarInfo("揽胜","路虎",7,52,63,"含基础险","231","img/RentCarInfo/揽胜.jpg");
//        carInfoMapper.EditCarInfo(carInfo);
//        ss.commit();
//    }
//
//    @Test
//    public void EditServer() throws Exception{
//        ServerInfo SIOne = new ServerInfo("001","Vodka","13566974721","男","服务员","12387693976");
//        serverInfoMapper.EditServerInfo(SIOne);
//        ss.commit();
//    }
//
//    @Test
//    public void ServerEditRent() throws Exception{
//        RentCarInfo rentCarInfo =rentCarInfoMapper.SearchRentCarInfo("10");
//         System.out.println(rentCarInfo);
//        rentCarInfo.setMoney(1200);
//
//        rentCarInfo.setOrderDetail("");
//
//        rentCarInfo.setCarSign("粤A 36687");
//
//        //修改服务员界面的租车信息表
//        rentCarInfoMapper.EditRentCarInfo(rentCarInfo);
//        ss.commit();
//
//    }
//
//    @Test
//    public void AddServer() throws Exception{
//        ServerInfo SIOne = new ServerInfo("31486","Lura","15687984342","女","服务员","666666");
//        serverInfoMapper.AddServerInfo(SIOne);
//        ss.commit();
//    }
//
//    @Test
//    public void DeleteServer() throws Exception{
//
//        serverInfoMapper.DeleteServerInfo("002");
//        ss.commit();
//    }
//
//    @Test
//    public void SearchServer() throws Exception{
//
//        System.out.println(serverInfoMapper.SearchServerInfo("001"));
//        ss.commit();
//    }
//
//    @Test
//    public void GetAllCustomer() throws Exception{
//
//        System.out.println(customerInfoMapper.GetAllCustomer());
//        ss.commit();
//    }
//
//    @Test
//    public void FindCustomerByName() throws Exception{
//
//        System.out.println(customerInfoMapper.ThroughNameToFindCustomer("Jack"));
//        ss.commit();
//    }
//
//    @Test
//    public void AddCustomer() throws Exception{
//        CustomerInfo CI = new CustomerInfo("4557","韦","身份证","440983199911145466","18469407651","广州","4425");
////        if(customerInfoMapper.SearchCustomerInfo("5655455") != null)  System.out.println("1");
////        else  System.out.println("0");
//        if(customerInfoMapper.AddCustomerInfo(CI) == 1) System.out.println("成功");
//        ss.commit();
//    }
//
//    @Test
//    public void DeleteCustomer() throws Exception{
//
//        System.out.println(customerInfoMapper.DeleteCustomerInfo("00252","19876777654"));
//        ss.commit();
//    }
//
//    @Test
//    public void EditCustomer() throws Exception{
//        CustomerInfo SIOne = new CustomerInfo("0252","vodka","身份证","4409345334222","19876777654","上海","12387693976");
//        System.out.println(customerInfoMapper.EditCustomerInfo(SIOne));
//        ss.commit();
//    }
//
//    @Test
//    public void AddOrder() throws Exception{
//        List<OrderInfo> orderInfoList = new ArrayList<>();
//
//        OrderInfo o1 = new OrderInfo();
//        OrderInfo o2 = new OrderInfo();
//
//
//        o1.setCarType("比亚迪汉");
//        o1.setRentPeriod("3");
//        o1.setCarSign("粤K 61095");
//        o1.setCustomerAccount("13689765432");
//        o1.setOrderDetail("已归还");
//        o1.setOperator("william");
//        o1.setDamageData("无损伤");
//        o1.setMoney(650);
//
//        orderInfoMapper.AddOrderInfo(o1);
//        ss.commit();
//    }
//
//    @Test
//    public void SearchOrder() throws Exception{
//        List<OrderInfo> orderInfoList = new ArrayList<>();
//
//        System.out.println(orderInfoMapper.SearchOrderInfo(String.valueOf(1)));
//        ss.commit();
//    }
//
//    @Test
//    public void AddRentCarInfo() throws Exception{
//        RentCarInfo rentcarInfo = new RentCarInfo();
//
//        rentcarInfo.setCarType("比亚迪汉");
//        rentcarInfo.setRentCarTime("2023-02-26 00:00:00");
//        rentcarInfo.setIdType("身份证");
//        rentcarInfo.setIdNum("42826655465555");
//        rentcarInfo.setCustomerName("Jucy");
//        rentcarInfo.setRentCarAddress("杭州");
//        rentcarInfo.setPhoneNum("13418997929");
//        rentcarInfo.setMoney(1320);
//
//        rentCarInfoMapper.AddRentCarInfo(rentcarInfo);
//        ss.commit();
//    }
//
//    @Test
//    public void EditAdminReturnCarInfo() throws Exception{
//        AdminReturnCarInfo a1 = new AdminReturnCarInfo(0,"粤B 54562","揽胜","3","广州","Jack","24545",0,"无损伤");
//        adminReturnCarInfoMapper.EditAdminReturnCarInfo(a1);
//        ss.commit();
//
//    }
//
//    @Test
//    public void AddCustomerCommentInfo() throws Exception{
//        CustomerCommentInfo customerCommentInfo = new CustomerCommentInfo("2549","小云","汽车种类繁多，服务态度很好!",timeUtility.ConvertStringToDate("2023-03-21 00:00:00"));
//        customerCommentInfoMapper.AddCustomerCommentInfo(customerCommentInfo);
//        ss.commit();
//
//    }
//
//    @Test
//    public void AddCustomerReturnCarInfo() throws Exception{
//        ReturnCarInfo customerReturnCarInfo = new ReturnCarInfo(null,"秦","比亚迪","已归还 ","Jack","25614","佛山",timeUtility.ConvertStringToDate("2023-03-21 00:00:00"));
//        returnCarInfoMapper.EditCustomerReturnCarInfo(customerReturnCarInfo);
//        ss.commit();
//
//    }
//
//    @Test
//    public void EditCustomerReturnCarInfo() throws Exception{
//        List<ReturnCarInfo> customerReturnCarInfos = returnCarInfoMapper.getAllReturnCarInfo();
//        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        returnCarInfoMapper.CustomerReturnCarProcess(1,"待处理","北京",timeUtility.GetNowTime());
//        ss.commit();
//    }
//
//    @Test
//    public void SearchCarInfo() throws Exception{
//       CarInfo carInfo = carInfoMapper.SearchCarInfo("汉");
//       System.out.println(carInfo.toString());
//    }
//
//    @Test
//    public void TestAdmincarInfo() throws Exception{
//       CarInfo carInfo = carInfoMapper.SearchCarInfo("汉");
//       System.out.println(carInfo.toString());
//        AdminReturnCarInfo adminReturnCarInfo = adminReturnCarInfoMapper.SearchAdminReturnCarInfo("3");
//
//                if((int) adminReturnCarInfo.getPenaltyMoney() == 0) System.out.println("ok");
//
//    }

}
