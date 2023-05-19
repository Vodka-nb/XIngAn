package com.vodka.Mapper;

import com.vodka.PJO.ServerInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Vodka
 * @date 2023/02//23:51
 */
public interface ServerInfoMapper {
    //获取服务员信息表
    List<ServerInfo> GetAllServer();

    //修改服务员信息表
    int EditServerInfo(ServerInfo serverInfo);

    //根据服务员账号，删除服务员信息表某一类型
    int DeleteServerInfo(@Param("ServerID") String ServerID);

    //增加服务员
    int AddServerInfo(ServerInfo ServerInfo);

    //查询服务员信息
    ServerInfo SearchServerInfo(String ServerNum);

    Integer EditServerPassWord(ServerInfo si);
}
