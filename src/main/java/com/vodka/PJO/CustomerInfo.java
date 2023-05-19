package com.vodka.PJO;

import org.springframework.stereotype.Component;

@Component
public class CustomerInfo {
    private String CustomerID;
    private String RealName;
    private String IDCardType;
    private String IDCardNum;
    private String Phone;
    private  String Address;
    private  String PassWord;

    public CustomerInfo() {
    }

    @Override
    public String toString() {
        return "CustomerInfoMapper{" +
                "CustomerID='" + CustomerID + '\'' +
                ", RealName='" + RealName + '\'' +
                ", IDCardType='" + IDCardType + '\'' +
                ", IDCardNum='" + IDCardNum + '\'' +
                ", Phone='" + Phone + '\'' +
                ", Address='" + Address + '\'' +
                ", PassWord='" + PassWord + '\'' +
                '}';
    }

    public CustomerInfo(String CustomerID, String RealName, String IDCardType, String IDCardNum, String Phone, String Address, String PassWord) {
        this.CustomerID = CustomerID;
        this.RealName = RealName;
        this.IDCardType = IDCardType;
        this.IDCardNum = IDCardNum;
        this.Phone = Phone;
        this.Address = Address;
        this.PassWord = PassWord;
    }

    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String realName) {
        RealName = realName;
    }

    public String getIDCardType() {
        return IDCardType;
    }

    public void setIDCardType(String IDCardType) {
        this.IDCardType = IDCardType;
    }

    public String getIDCardNum() {
        return IDCardNum;
    }

    public void setIDCardNum(String IDCardNum) {
        this.IDCardNum = IDCardNum;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }
}
