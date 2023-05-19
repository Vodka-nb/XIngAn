package com.vodka.PJO;

import java.util.Date;

public class OrderInfo {
    private Integer OrderId;
    private Integer ReturnId;
    private String PayMethod;
    private String PayTime;
    private double OrderMoney;

    public OrderInfo(Integer orderId, Integer returnId, String payMethod, String payTime, double orderMoney) {
        OrderId = orderId;
        ReturnId = returnId;
        PayMethod = payMethod;
        PayTime = payTime;
        OrderMoney = orderMoney;
    }

    @Override
    public String toString() {
        return "OrderInfo{" +
                "OrderId=" + OrderId +
                ", ReturnId=" + ReturnId +
                ", PayMethod='" + PayMethod + '\'' +
                ", PayTime=" + PayTime +
                ", OrderMoney=" + OrderMoney +
                '}';
    }

    public Integer getOrderId() {
        return OrderId;
    }

    public void setOrderId(Integer orderId) {
        OrderId = orderId;
    }

    public Integer getReturnId() {
        return ReturnId;
    }

    public void setReturnId(Integer returnId) {
        ReturnId = returnId;
    }

    public String getPayMethod() {
        return PayMethod;
    }

    public void setPayMethod(String payMethod) {
        PayMethod = payMethod;
    }

    public String getPayTime() {
        return PayTime;
    }

    public void setPayTime(String payTime) {
        PayTime = payTime;
    }

    public double getOrderMoney() {
        return OrderMoney;
    }

    public void setOrderMoney(double orderMoney) {
        OrderMoney = orderMoney;
    }

    public OrderInfo() {
    }
}