package com.vodka.PJO;

import java.util.Date;

public class CarInfo {
    private String CarType;
    private String CarBrand;
    private String CarSign;
    private int CarSize;
    private String PurchaseDate;
    private double PurchasePrice;
    private String CarDetail;
    private String Remark;
    private String ImgUrl;
    private String RentWay;
    private double RentPrice;


    @Override
    public String toString() {
        return "CarInfo{" +
                "CarType='" + CarType + '\'' +
                ", CarBrand='" + CarBrand + '\'' +
                ", CarSign='" + CarSign + '\'' +
                ", CarSize=" + CarSize +
                ", PurchaseDate=" + PurchaseDate +
                ", PurchasePrice=" + PurchasePrice +
                ", CarDetail='" + CarDetail + '\'' +
                ", Remark='" + Remark + '\'' +
                ", ImgUrl='" + ImgUrl + '\'' +
                ", RentWay='" + RentWay + '\'' +
                ", RentPrice=" + RentPrice +
                '}';
    }

    public CarInfo() {
    }

    public CarInfo(String carType, String carBrand, String carSign, int carSize, String purchaseDate, double purchasePrice, String carDetail, String remark, String imgUrl, String rentWay, double rentPrice) {
        CarType = carType;
        CarBrand = carBrand;
        CarSign = carSign;
        CarSize = carSize;
        PurchaseDate = purchaseDate;
        PurchasePrice = purchasePrice;
        CarDetail = carDetail;
        Remark = remark;
        ImgUrl = imgUrl;
        RentWay = rentWay;
        RentPrice = rentPrice;
    }

    public String getCarType() {
        return CarType;
    }

    public void setCarType(String carType) {
        CarType = carType;
    }

    public String getCarBrand() {
        return CarBrand;
    }

    public void setCarBrand(String carBrand) {
        CarBrand = carBrand;
    }

    public String getCarSign() {
        return CarSign;
    }

    public void setCarSign(String carSign) {
        CarSign = carSign;
    }

    public int getCarSize() {
        return CarSize;
    }

    public void setCarSize(int carSize) {
        CarSize = carSize;
    }

    public String getPurchaseDate() {
        return PurchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        PurchaseDate = purchaseDate;
    }

    public double getPurchasePrice() {
        return PurchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        PurchasePrice = purchasePrice;
    }

    public String getCarDetail() {
        return CarDetail;
    }

    public void setCarDetail(String carDetail) {
        CarDetail = carDetail;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String imgUrl) {
        ImgUrl = imgUrl;
    }

    public String getRentWay() {
        return RentWay;
    }

    public void setRentWay(String rentWay) {
        RentWay = rentWay;
    }

    public double getRentPrice() {
        return RentPrice;
    }

    public void setRentPrice(double rentPrice) {
        RentPrice = rentPrice;
    }
}
