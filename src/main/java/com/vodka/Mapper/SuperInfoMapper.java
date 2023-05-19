package com.vodka.Mapper;

import com.vodka.PJO.CustomerInfo;
import com.vodka.PJO.SuperInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Vodka
 * @date 2023/02//23:51
 */
public interface SuperInfoMapper {

    //获取管理员信息表
    List<SuperInfo> GetAllSuper();

    //修改管理员信息表
     int EditAdminInfo(@Param("nickName") String nickName , @Param("gender") String gender , @Param("remark") String remark ,@Param("phone") String phone,@Param("account")String account);

     //删除管理员信息表某一项
    int DeleteAdminInfo(@Param("phone") String phone,@Param("superAccount") String superAccount);

    //增加管理员信息表某一项
    int AddAdminInfo(SuperInfo superInfo);

    //根据手机号查询管理员信息
    SuperInfo SearchAdminInfo(String Num);

    //修改管理员密码
    Integer EditAdminPassWord(SuperInfo si);


}
