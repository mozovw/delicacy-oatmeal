package com.delicacy.oatmeal.test.example;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


public class Test {
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		 String ss =  new String("\u5feb\u901f\u5f00\u53d1\u5e73\u53f0"
		 .getBytes(),"utf-8");
	        System.out.println(ss);
	        System.out.println("\u5b9a\u4e49\u6700\u5927\u8fde\u63a5\u6570");
	        //2的3次方
	        int i = 1<<3;
	        System.out.println(i);
	        //64/(2*2)
	         i = 64>>3;
	        System.out.println(i);
	        //i*(2*2*2*2)
	        i = i<<=4;
	        System.out.println(i);
	        System.out.println(64>>4==64>>>4);
	        
	        i = 32;
	        if (i==32&(i+=1)==33) {
				System.out.println(i);
			}
	        if (i==32&&(i+=1)==33) {
	        	System.out.println(i);
	        }
	        
	        System.out.println("😁");
	        System.out.println(Integer.MAX_VALUE);
	        
	        String one = "someString";  
	        String two = "someString"; 
	        
	        System.out.println(one == two);
	        //ConcurrentLinkedQueue
	        System.out.println(1 << 29);
	}
}
