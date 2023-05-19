package com.vodka.Mapper;

import com.vodka.PJO.CustomerCommentInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Vodka
 * @date 2023/02//23:51
 */
public interface CustomerCommentInfoMapper {
    //管理评论信息
    List<CustomerCommentInfo> getAllCommentInfo(@Param("Status") String Status);

    //修改评论账单信息表
    int EditCustomerCommentInfo(CustomerCommentInfo customerCommentInfo);

    //根据评论账单账号，删除评论账单信息表某一类型
    int DeleteCustomerCommentInfo(@Param("Content") String Content);

    //增加评论账单
    int AddCustomerCommentInfo(CustomerCommentInfo CustomerCommentInfo);

    //查询评论账单信息
    CustomerCommentInfo SearchCustomerCommentInfo(@Param("CustomerAccount") String CustomerAccount );

    //获取未审核的评论
    List<CustomerCommentInfo> UnThroughCommentInfo(@Param("Status") String Status);

    CustomerCommentInfo SearchCustomerCommentInfoByOrderId(@Param("OrderId") String OrderId);
}
