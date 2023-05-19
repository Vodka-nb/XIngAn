package com.vodka.PJO;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class RentCarInfo {
    private  Integer RentId;
    private  String CustomerAccount;
    private String RentCarDate;
    private String PredictReturnDate;
    private  String Operator;
    private  String Remark;
    private  String RentCarAddress;
    private  double Deposit;
    private  double RentMoney;
    private  String CarSign;

    public Integer getRentId() {
        return RentId;
    }

    @Override
    public String toString() {
        return "RentCarInfo{" +
                "RentId=" + RentId +
                ", CustomerAccount='" + CustomerAccount + '\'' +
                ", RentCarDate='" + RentCarDate + '\'' +
                ", PredictReturnDate='" + PredictReturnDate + '\'' +
                ", Operator='" + Operator + '\'' +
                ", Remark='" + Remark + '\'' +
                ", RentCarAddress='" + RentCarAddress + '\'' +
                ", Deposit=" + Deposit +
                ", RentMoney=" + RentMoney +
                ", CarSign='" + CarSign + '\'' +
                '}';
    }

    public void setRentId(Integer rentId) {
        RentId = rentId;
    }

    public String getCustomerAccount() {
        return CustomerAccount;
    }

    public void setCustomerAccount(String customerAccount) {
        CustomerAccount = customerAccount;
    }

    public String getRentCarDate() {
        return RentCarDate;
    }

    public void setRentCarDate(String rentCarDate) {
        RentCarDate = rentCarDate;
    }

    public String getPredictReturnDate() {
        return PredictReturnDate;
    }

    public void setPredictReturnDate(String predictReturnDate) {
        PredictReturnDate = predictReturnDate;
    }

    public String getOperator() {
        return Operator;
    }

    public void setOperator(String operator) {
        Operator = operator;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getRentCarAddress() {
        return RentCarAddress;
    }

    public void setRentCarAddress(String rentCarAddress) {
        RentCarAddress = rentCarAddress;
    }

    public double getDeposit() {
        return Deposit;
    }

    public void setDeposit(double deposit) {
        Deposit = deposit;
    }

    public double getRentMoney() {
        return RentMoney;
    }

    public void setRentMoney(double rentMoney) {
        RentMoney = rentMoney;
    }

    public String getCarSign() {
        return CarSign;
    }

    public void setCarSign(String carSign) {
        CarSign = carSign;
    }

    public RentCarInfo(Integer rentId, String customerAccount, String rentCarDate, String predictReturnDate, String operator, String remark, String rentCarAddress, double deposit, double rentMoney, String carSign) {
        RentId = rentId;
        CustomerAccount = customerAccount;
        RentCarDate = rentCarDate;
        PredictReturnDate = predictReturnDate;
        Operator = operator;
        Remark = remark;
        RentCarAddress = rentCarAddress;
        Deposit = deposit;
        RentMoney = rentMoney;
        CarSign = carSign;
    }

    public RentCarInfo() {
    }
}
