package com.vodka.Mapper;

import com.vodka.PJO.CarInfo;
import com.vodka.PJO.OrderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Vodka
 * @date 2023/02//23:51
 */
public interface OrderInfoMapper {
    //获取订单信息表
    List<OrderInfo> GetAllOrder();

    //修改订单信息表
    int EditOrderInfo(OrderInfo OrderInfo);

    //根据订单账号，删除订单信息表某一类型
    int DeleteOrderInfo(@Param("OrderId") Integer OrderId);

    //增加订单
    int AddOrderInfo(OrderInfo OrderInfo);

    //查询订单信息
     OrderInfo SearchOrderInfo(@Param("OrderId") String OrderId);

    //通过具体账单号，查询总订单信息
    OrderInfo SearchOrderInfoByOtherId(@Param("OtherId") String OtherId);

    List<OrderInfo> SearchOrderInfoByCondition(String orderId, String rentOrReturnId, String moneyStatus);

    //渲染指定用户的账单
    OrderInfo getOrderInfoByReturnId(Integer returnId);
}
