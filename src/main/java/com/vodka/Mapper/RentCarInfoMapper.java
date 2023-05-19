package com.vodka.Mapper;

import com.vodka.PJO.RentCarInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Vodka
 * @date 2023/02//23:51
 */
public interface RentCarInfoMapper {
    //获取订单信息表
    List<RentCarInfo> GetAllRentCar();

    //修改订单信息表
    int EditRentCarInfo(RentCarInfo rentCarInfo);

    //根据订单账号，删除订单信息表某一类型
    int DeleteRentCarInfo(@Param("RentId") Integer RentId);

    //增加订单
    int AddRentCarInfo(RentCarInfo rentCarInfo);

    //查询订单信息
    RentCarInfo SearchRentCarInfo(@Param("RentID") Integer RentID);

    List<RentCarInfo> GetCustomerRentList(@Param("customerAccount") String customerAccount);
}
