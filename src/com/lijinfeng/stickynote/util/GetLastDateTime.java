package com.lijinfeng.stickynote.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetLastDateTime {
 
 //��õ��������
 public String lastDay(){
     String dateString=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
     return dateString;
  }

 //���һ��ǰ������
 public String lastWeek(){
     Date date = new Date();
   
     int year=Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
     int month=Integer.parseInt(new SimpleDateFormat("MM").format(date));
     int day=Integer.parseInt(new SimpleDateFormat("dd").format(date))-6;
   
     if(day<1){
      month-=1;
      if(month==0){
       year-=1;month=12;
      }
      if(month==4||month==6||month==9||month==11){
       day=30+day;
      }else if(month==1||month==3||month==5||month==7||month==8||month==10||month==12)
      {
       day=31+day;
      }else if(month==2){
       if(year%400==0||(year %4==0&&year%100!=0))day=29+day;
       else day=28+day;
      }    
     }
     String y = year+"";String m ="";String d ="";
     if(month<10) m = "0"+month;
     else m=month+"";
     if(day<10) d = "0"+day;
     else d = day+"";
   
     return y+"-"+m+"-"+d;
  }
 
 //���һ��ǰ������
 public String lastMonth(){
    Date date = new Date();
   
    int year=Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
    int month=Integer.parseInt(new SimpleDateFormat("MM").format(date))-1;
    int day=Integer.parseInt(new SimpleDateFormat("dd").format(date));

    if(month==0){
     year-=1;month=12;
    }
    else if(day>28){
     if(month==2){
      if(year%400==0||(year %4==0&&year%100!=0)){
       day=29;
      }else day=28;
     }else if((month==4||month==6||month==9||month==11)&&day==31)
     {
      day=30;
     }
    }
    String y = year+"";String m ="";String d ="";
    if(month<10) m = "0"+month;
    else m=month+"";
    if(day<10) d = "0"+day;
    else d = day+"";
  
    return y+"-"+m+"-"+d;
 }
 
 //���һ��ǰ������
 public String lastYear(){
    Date date = new Date();
    int year=Integer.parseInt(new SimpleDateFormat("yyyy").format(date))-1;
    int month=Integer.parseInt(new SimpleDateFormat("MM").format(date));
    int day=Integer.parseInt(new SimpleDateFormat("dd").format(date));

    if(month==0){
     year-=1;month=12;
    }
    else if(day>28){
     if(month==2){
       if(year%400==0||(year %4==0&&year%100!=0)){
       day=29;
      }else day=28;
     }
    }
    String y = year+"";String m ="";String d ="";
    if(month<10) m = "0"+month;
    else m=month+"";
    if(day<10) d = "0"+day;
    else d = day+"";
  
    return y+"-"+m+"-"+d;
 }

}

