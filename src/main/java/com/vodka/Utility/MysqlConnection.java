package com.vodka.Utility;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * @author Vodka
 * @date 2023/02//10:21
 */
public class MysqlConnection {
    private static SqlSessionFactory ssf ;
    //创建一个数据连接工厂
    static {
        try{
            String resource = "MybatisConnect.xml";
            InputStream ioStream  = Resources.getResourceAsStream(resource);
            ssf = new SqlSessionFactoryBuilder().build(ioStream);
        } catch ( Exception e) {
            e.printStackTrace();
        }
    }
    //    获取SqlSession方法的连接
    public static SqlSession SqlSessionStartUp(){
        return  ssf.openSession(true);
    }   //自动提交已修改的数据，以便其他事务及时获取

}
