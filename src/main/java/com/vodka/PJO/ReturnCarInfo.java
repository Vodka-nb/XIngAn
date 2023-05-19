package com.vodka.PJO;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class ReturnCarInfo {
    private  Integer ReturnId;
    private  Integer RentId;
    private  String EvaluateCar;
    private  String AcctuallyReturnCarTime;
    private  String ReturnCarAddress;
    private  String RentMoney;
    private  double PenaltyMoney;
    private  String OperatorId;

    public ReturnCarInfo() {
    }

    @Override
    public String toString() {
        return "ReturnCarInfo{" +
                "ReturnId=" + ReturnId +
                ", RentId=" + RentId +
                ", EvaluateCar='" + EvaluateCar + '\'' +
                ", AcctuallyReturnCarTime='" + AcctuallyReturnCarTime + '\'' +
                ", ReturnCarAddress='" + ReturnCarAddress + '\'' +
                ", RentMoney='" + RentMoney + '\'' +
                ", PenaltyMoney='" + PenaltyMoney + '\'' +
                ", OperatorId='" + OperatorId + '\'' +
                '}';
    }

    public Integer getReturnId() {
        return ReturnId;
    }

    public void setReturnId(Integer returnId) {
        ReturnId = returnId;
    }

    public Integer getRentId() {
        return RentId;
    }

    public void setRentId(Integer rentId) {
        RentId = rentId;
    }

    public String getEvaluateCar() {
        return EvaluateCar;
    }

    public void setEvaluateCar(String evaluateCar) {
        EvaluateCar = evaluateCar;
    }

    public String getAcctuallyReturnCarTime() {
        return AcctuallyReturnCarTime;
    }

    public void setAcctuallyReturnCarTime(String acctuallyReturnCarTime) {
        AcctuallyReturnCarTime = acctuallyReturnCarTime;
    }

    public String getReturnCarAddress() {
        return ReturnCarAddress;
    }

    public void setReturnCarAddress(String returnCarAddress) {
        ReturnCarAddress = returnCarAddress;
    }

    public String getRentMoney() {
        return RentMoney;
    }

    public void setRentMoney(String rentMoney) {
        RentMoney = rentMoney;
    }

    public Double getPenaltyMoney() {
        return PenaltyMoney;
    }

    public void setPenaltyMoney(Double penaltyMoney) {
        PenaltyMoney = penaltyMoney;
    }

    public String getOperatorId() {
        return OperatorId;
    }

    public void setOperatorId(String operatorId) {
        OperatorId = operatorId;
    }

    public void setPenaltyMoney(double penaltyMoney) {
        PenaltyMoney = penaltyMoney;
    }

    public ReturnCarInfo(Integer returnId, Integer rentId, String evaluateCar, String acctuallyReturnCarTime, String returnCarAddress, String rentMoney, double penaltyMoney, String operatorId) {
        ReturnId = returnId;
        RentId = rentId;
        EvaluateCar = evaluateCar;
        AcctuallyReturnCarTime = acctuallyReturnCarTime;
        ReturnCarAddress = returnCarAddress;
        RentMoney = rentMoney;
        PenaltyMoney = penaltyMoney;
        OperatorId = operatorId;
    }


}
