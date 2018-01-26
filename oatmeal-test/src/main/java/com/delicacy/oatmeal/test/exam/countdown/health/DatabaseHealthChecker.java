package com.delicacy.oatmeal.test.exam.countdown.health;

import java.util.concurrent.CountDownLatch;


public class DatabaseHealthChecker extends BaseHealthChecker
{
	
    public DatabaseHealthChecker (CountDownLatch latch)  {
        super("Network Service", latch);
        
    }
 
    @Override
    public void verifyService()
    {
        System.out.println("Checking " + this.getServiceName());
        try
        {
            Thread.sleep(2000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(this.getServiceName() + " is UP");
    }
}