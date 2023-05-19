package com.vodka.PJO;
import org.springframework.stereotype.Component;

@Component
public class SuperInfo {
    private String SuperAccount;
    private String NickName;
    private String Phone;
    private String Gender;
    private String Remark;
    private  String PassWord;

    public SuperInfo() {

    }

    @Override
    public String toString() {
        return "SuperInfoMapper{" +
                "SuperAccount='" + SuperAccount + '\'' +
                ", NickName='" + NickName + '\'' +
                ", Phone='" + Phone + '\'' +
                ", Gender='" + Gender + '\'' +
                ", Remark='" + Remark + '\'' +
                ", PassWord='" + PassWord + '\'' +
                '}';
    }

    public SuperInfo(String superAccount, String nickName, String phone, String gender, String remark, String passWord) {
        SuperAccount = superAccount;
        NickName = nickName;
        Phone = phone;
        Gender = gender;
        Remark = remark;
        PassWord = passWord;
    }

    public String getSuperAccount() {
        return SuperAccount;
    }

    public void setSuperAccount(String superAccount) {
        SuperAccount = superAccount;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }
}
