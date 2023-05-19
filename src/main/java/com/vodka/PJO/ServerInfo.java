package com.vodka.PJO;

public class ServerInfo {
    private String ServerID;
    private String ServerName;
    private String ServerPhone;
    private String ServerSex;
    private String ServerRemark;
    private String PassWord;

    @Override
    public String toString() {
        return "ServerInfoMapper{" +
                "ServerID='" + ServerID + '\'' +
                ", ServerName='" + ServerName + '\'' +
                ", ServerPhone='" + ServerPhone + '\'' +
                ", ServerSex='" + ServerSex + '\'' +
                ", ServerRemark='" + ServerRemark + '\'' +
                ", PassWord='" + PassWord + '\'' +
                '}';
    }

    public ServerInfo() {
    }

    public String getServerID() {
        return ServerID;
    }

    public void setServerID(String serverID) {
        ServerID = serverID;
    }

    public String getServerName() {
        return ServerName;
    }

    public void setServerName(String serverName) {
        ServerName = serverName;
    }

    public String getServerPhone() {
        return ServerPhone;
    }

    public void setServerPhone(String serverPhone) {
        ServerPhone = serverPhone;
    }

    public String getServerSex() {
        return ServerSex;
    }

    public void setServerSex(String serverSex) {
        ServerSex = serverSex;
    }

    public String getServerRemark() {
        return ServerRemark;
    }

    public void setServerRemark(String serverRemark) {
        ServerRemark = serverRemark;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    public ServerInfo(String serverID, String serverName, String serverPhone, String serverSex, String serverRemark, String passWord) {
        ServerID = serverID;
        ServerName = serverName;
        ServerPhone = serverPhone;
        ServerSex = serverSex;
        ServerRemark = serverRemark;
        PassWord = passWord;
    }
}
