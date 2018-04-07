package com.delicacy.oatmeal.test.exam.countdown.health;
public class Main {
    public static void main(String[] args)
    {
        boolean result = false;
        try {
            result = ApplicationStartupUtil.checkExternalServices();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("External service validation completed !! Result was :: "+ result);
    }
}