package com.vodka.Mapper;

import com.vodka.PJO.CustomerInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Vodka
 * @date 2023/02//23:51
 */
public interface CustomerInfoMapper {
    //获取顾客信息表
    List<CustomerInfo> GetAllCustomer();

    //修改顾客信息表
    int EditCustomerInfo(CustomerInfo customerInfo);

    //根据顾客账号，删除顾客信息表某一类型
    int DeleteCustomerInfo(@Param("CustomerID") String CustomerID,@Param("Phone") String Phone);

    //增加顾客
    int AddCustomerInfo(CustomerInfo customerInfo);

    //查询顾客信息
    CustomerInfo SearchCustomerInfo(String CustomerNum);

    //通过昵称，查询顾客信息
    CustomerInfo ThroughNameToFindCustomer(@Param("CustomerName") String CustomerName);

    Integer EditCustomerPassWord(CustomerInfo ci);

    List<CustomerInfo> GetCustomerByCondition(@Param("CustomerID") String CustomerID,@Param("realName") String realName);
}
