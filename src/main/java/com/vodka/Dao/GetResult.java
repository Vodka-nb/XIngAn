package com.vodka.Dao;

import com.alibaba.fastjson.JSON;
import com.vodka.PJO.RentCarInfo;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GetResult {
    private  Integer code = 0;
    private  String  msg = "";
    private  Map<String,Object> InfoList ;

    public GetResult(Integer code, String msg, Map<String, Object> infoList) {
        this.code = code;
        this.msg = msg;
        InfoList = infoList;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", InfoList=" + InfoList +
                '}';
    }

    public GetResult() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getInfoList() {
        return InfoList;
    }

    public void setInfoList(Map<String, Object> infoList) {
        InfoList = infoList;
    }

    //返回layui标准表格数据格式，用于渲染表格
     public String TableResultProcessing(List InfoArr){
         InfoList= new HashMap<>();

         InfoList.put("code",code);
         InfoList.put("msg",msg);
         InfoList.put("count",InfoArr.size());
         InfoList.put("data",InfoArr);

         return JSON.toJSONString(InfoList);
     }
}
