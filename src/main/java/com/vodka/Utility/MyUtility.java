package com.vodka.Utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

/**
 * @author Vodka
 * @date 2023/02//10:43
 */
public class MyUtility {
    //    整合日期和具体时间
    public String IntegrateDateAndTime(String Date,String Time){
        return Date + " " + Time;
    }

    //无参构造
    public MyUtility() {
    }

    //转换Base64格式的字节数据集成为图片，存到服务器指定路径
    public void TransformBase64DataToPhoto(String UploadPath,String DataName,String Base64Photo){
        Base64.Decoder  base64Decoder = Base64.getDecoder();    //Base64解码器
        byte [] ImgContainer = null;    //图片二进制流接收器
        FileOutputStream fileOutputStream = null;  //文件输出流
        String RealBaseData = Base64Photo.split(",")[1];   //去除掉字节信息，获取字节数据集
        //如果数据流为空，则返回提示
        if(Base64Photo.equals("")){
            System.out.println("图像数据流为空!");
        }else{
            //解码
            String NoWrapData = RealBaseData.replaceAll("\n","");  //去除掉换行符
            String NoSpaceData = NoWrapData.trim();   //去掉空格
            ImgContainer = base64Decoder.decode(NoSpaceData); //解码为字节数组
            //字节流转文件
            try {
                fileOutputStream = new FileOutputStream(UploadPath+DataName+".jpg");   //文件输出的保存路径及名称
                fileOutputStream.write(ImgContainer);      //将Base64字节流写进指定文件路径

            }catch (Exception e){
                e.printStackTrace();
            } finally {
                if(fileOutputStream != null){
                    try{
                        fileOutputStream.close();  //关闭文件输出流
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    //将图片转换成Base64格式的数据集
    public String TransformPhotoToBase64Data(String LoadPath,String DataName){
        Base64.Encoder encoder= Base64.getEncoder();  //获取Base64编码器
        byte [] ImgContainer = null ;    //数据集缓存器
        FileInputStream fileInputStream = null; //文件输入流
        try {
            fileInputStream = new FileInputStream(LoadPath+DataName);    //到指定路径寻找文件
            ImgContainer = new byte[fileInputStream.available()];          //设置图片字节数据缓冲区大小
            fileInputStream.read(ImgContainer);           //将数据流中的图片数据读进缓冲区
            String Base64ImgData =encoder.encodeToString(ImgContainer);  //将图片编码转换成Base64格式的数据集
            fileInputStream.close();      //关闭数据流
            return Base64ImgData;  //将缓冲区数据转换成字符数据返回
        } catch (FileNotFoundException e) {
            return "找不到指定文件!";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "null";
    }

    //    字符串转换成时间类型,设置转换格式，先转换成java.util.Date，再转换成Timestamp
    public static Timestamp ConvertStringToDate(String Time){
        DateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   //声明要转换成什么格式的时间类型
        java.util.Date day = null;
        Timestamp time = null;

        //使用parse()转换
        try {
            day =  format.parse(Time);  //字符串转java.sql.Date
            //java.sql.Date 转Timestamp
            time = new Timestamp(day.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }


    //   时间类型转换城字符串
    public static String ConvertDateToString(Date str){
        DateFormat DFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return DFormat.format(str);
    }
}
