package com.vodka.Mapper;

import com.vodka.PJO.ReturnCarInfo;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author Vodka
 * @date 2023/02//23:51
 */
public interface ReturnCarInfoMapper {
    //管理顾客还车信息
    List<ReturnCarInfo> getAllReturnCarInfo();

    //修改顾客还车账单信息表
    int EditReturnCarInfo(ReturnCarInfo returnCarInfo);

    //根据顾客还车账单账号，删除顾客还车账单信息表某一类型
    int DeleteReturnCarInfo(@Param("ReturnId") Integer ReturnId);

    //根据顾客租车账单编号，删除顾客还车账单信息表某一类型
    int DeleteReturnCarInfoByRentId(@Param("RentId") Integer RentId);

    //增加顾客还车账单
    int AddReturnCarInfo(ReturnCarInfo returnCarInfo);

    //查询顾客还车账单信息
    ReturnCarInfo SearchReturnCarInfo(@Param("ReturnId") String ReturnId);

    //顾客还车提交处理
    int CustomerReturnCarProcess(@Param("ReturnId") Integer ReturnId ,@Param("OrderDetail") String OrderDetail  ,@Param("ReturnCarAddress") String ReturnCarAddress  ,@Param("AcctuallyReturnCarTime") Timestamp AcctuallyReturnCarTime);

    //获取指定用户的还车列表
    ReturnCarInfo getUserReturnCarInfo(@Param("UserRentId") Integer UserRentId);
}
