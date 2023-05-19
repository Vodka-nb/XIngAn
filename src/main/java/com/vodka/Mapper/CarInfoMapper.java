package com.vodka.Mapper;

import com.vodka.PJO.CarInfo;
import com.vodka.PJO.CarInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Vodka
 * @date 2023/02//23:51
 */
public interface CarInfoMapper {
    //获取车辆信息表
    List<CarInfo> GetAllCar();

    //修改车辆信息表
    int EditCarInfo(CarInfo carInfo );

    //删除车辆信息表某一类型
    int DeleteCarInfo(@Param("carSign") String carSign);

    //增加车辆
    int AddCarInfo(CarInfo carInfo);

    //查询车辆信息
    CarInfo SearchCarInfo(String carSign);

    //搜索符合条件的车辆，返回列表
    List<CarInfo> SearchCarInfoByCondition(@Param("carSign") String carSign,@Param("carType") String carType, @Param("remark") String remark);
}
