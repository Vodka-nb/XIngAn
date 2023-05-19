package com.vodka.Utility;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Vodka
 * @date 2023/02//10:40
 */
public class TimeUtility {
    //    整合日期和具体时间
    public String IntegrateDateAndTime(String Date,String Time){
        return Date + " " + Time;
    }

    public TimeUtility() {
    }

    public String DateToString(Date time){
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(time);
    }

    //获取当前具体时间
    public Timestamp GetNowTime(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return Timestamp.valueOf(simpleDateFormat.format(calendar.getTime()));
    }

    //计算两个时间戳之间的天数
    public int CalculateDays(Timestamp One , Timestamp Two){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Timestamp NowTime =Timestamp.valueOf(simpleDateFormat.format(Two)); //当前时间
        Timestamp UsedTime = Timestamp.valueOf(simpleDateFormat.format(One)); //过去时间

        int YearTwo = Integer.parseInt(NowTime.toString().substring(0,4));
        int MonthTwo = Integer.parseInt(NowTime.toString().substring(5,7));
        int DayTwo = Integer.parseInt( NowTime.toString().substring(8,10));
        int YearOne = Integer.parseInt(UsedTime.toString().substring(0,4));
        int MonthOne = Integer.parseInt(UsedTime.toString().substring(5,7));
        int DayOne = Integer.parseInt(UsedTime.toString().substring(8,10));

        int DeltaTime = 0;
        int OneFebruary = 28;
        int TwoFebruary = 28;
        int MonthDaysOne = 30;
        int MonthDaysTwo = 30;
        int RunFlag = 0;


//        判断两个年份是否是闰年，是的话，二月份有29天
        if(YearOne % 400 == 0 ){

            RunFlag = 1;
            OneFebruary = 29;
        } else if( YearOne % 4 == 0 && YearOne % 100 != 0){
            OneFebruary = 29;
            RunFlag = 1;
        }
        if(YearTwo % 400 == 0 ){
            TwoFebruary = 29;
            RunFlag = 1;
        } else if( YearTwo % 4 == 0 && YearTwo % 100 != 0){
            TwoFebruary = 29;
            RunFlag = 1;
        }
//       同年同月
        if(YearTwo == YearOne && MonthTwo == MonthOne){

            DeltaTime = DayTwo - DayOne + 1;
        }
//       同年不同月
        else if(MonthTwo > MonthOne){

            DeltaTime += GetMonthDays(MonthOne) - DayOne;
            System.out.println("MonthDays: " + DeltaTime);
            for (int begin = MonthOne + 1; begin < MonthTwo; begin++) {
                DeltaTime += GetMonthDays(begin);
            }
            DeltaTime += DayTwo;

        }
//       不同年
        if(YearTwo > YearOne ) {
            //           不同月
            if (MonthOne != MonthTwo) {

                DeltaTime += GetMonthDays(MonthOne) - DayOne;

                for (int begin = MonthOne + 1; begin <= 12; begin++) {
                    DeltaTime += GetMonthDays(begin);
                }
                for (int begin = 1; begin < MonthTwo; begin++) {
                    DeltaTime += GetMonthDays(begin);
                }
                DeltaTime += DayTwo + (YearTwo - YearOne-1) * 365;
            } else {

//           同月
                MonthDaysOne = GetMonthDays(MonthOne);
                MonthDaysTwo = GetMonthDays(MonthTwo);
                if (RunFlag == 1) {
                    DeltaTime += 365 * (YearTwo - YearOne) + DayTwo - DayOne + 1;

                }
                if (RunFlag == 0) {
                    DeltaTime += 366 * (YearTwo - YearOne) +DayTwo - DayOne + 1;

                }

            }
        }
        return DeltaTime;
    }



    //    字符串转换成时间类型,设置转换格式，先转换成java.util.Date，再转换成Timestamp
    public  Timestamp ConvertStringToDate(String Time){
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
    public  String ConvertDateToString(Date str){
        DateFormat DFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return DFormat.format(str);
    }

    //返回某月的具体天数，二月除外
    public   int GetMonthDays(int Month){
        int MonthDays = 30;
        switch (Month){
            case 1 : MonthDays = 31;break;
            case 3 : MonthDays = 31;break;
            case 5 : MonthDays = 31;break;
            case 7 : MonthDays = 31;break;
            case 8 : MonthDays = 31;break;
            case 10 : MonthDays = 31;break;
            case 12 : MonthDays = 31;break;
        };
        return  MonthDays;
    }
}
