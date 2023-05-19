package com.vodka.PJO;

import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.Date;

public class CustomerCommentInfo {
    private String CustomerAccount;
    private int OrderId;
    private String Content;
    private String CommentTime;
    private String AdminAccount;
    private String Status;

    public CustomerCommentInfo(String customerAccount, int orderId, String content, String commentTime, String adminAccount, String status) {
        CustomerAccount = customerAccount;
        OrderId = orderId;
        Content = content;
        CommentTime = commentTime;
        AdminAccount = adminAccount;
        Status = status;
    }

    public CustomerCommentInfo() {
    }

    public String getCustomerAccount() {
        return CustomerAccount;
    }

    public void setCustomerAccount(String customerAccount) {
        CustomerAccount = customerAccount;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getCommentTime() {
        return CommentTime;
    }

    public void setCommentTime(String commentTime) {
        CommentTime = commentTime;
    }

    public String getAdminAccount() {
        return AdminAccount;
    }

    public void setAdminAccount(String adminAccount) {
        AdminAccount = adminAccount;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    @Override
    public String toString() {
        return "CustomerCommentInfo{" +
                "CustomerAccount='" + CustomerAccount + '\'' +
                ", OrderId=" + OrderId +
                ", Content='" + Content + '\'' +
                ", CommentTime='" + CommentTime + '\'' +
                ", AdminAccount='" + AdminAccount + '\'' +
                ", Status='" + Status + '\'' +
                '}';
    }
}
