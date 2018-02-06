package com.delicacy.oatmeal.test.example;

import java.text.DecimalFormat;

public class DemacialFormatDemo {
    public static void main(String args[]){
        DecimalFormat f1,f2;
    
        f1=new DecimalFormat("0.000");
        f2=new DecimalFormat("#.000");
        
        System.out.println("带0号的"+f1.format(0.20));
        System.out.println("带#的"+f2.format(0.20));
        
        f1=new DecimalFormat("0.00");
        f2=new DecimalFormat("0.##");
        
        System.out.println("小数点00："+f1.format(0.2));
        System.out.println("小数点##："+f2.format(0.2));
        

        f1=new DecimalFormat("0.00");
        f2=new DecimalFormat("##.00");
        
        System.out.println("00的整数："+f1.format(22));
        System.out.println("##的整数："+f2.format(22));
        
    }

}