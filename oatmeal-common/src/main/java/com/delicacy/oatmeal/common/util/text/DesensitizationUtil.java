package com.delicacy.oatmeal.common.util.text;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * 脱敏信息的帮助类
 * @author yangzhilong
 *
 */
public class DesensitizationUtil {
	/**
     * 脱敏用户名
     * 
     * @param acctName
     * @return
     */
    public static String desensitizationUsername(String acctName){
    	if(StringUtils.isNotBlank(acctName)){
    		if(acctName.length() > 2){
    			StringBuffer sb = new StringBuffer();
    			sb.append(acctName.substring(0, 1));
    			for (int i = 0; i < acctName.length()-2; i++) {
					sb.append("*");
				}
    			sb.append(acctName.substring(acctName.length()-1, acctName.length()));
    			return sb.toString();
    		}
    		if(acctName.length() == 2){
    			return acctName.substring(0, 1) + "*";
    		}
    	}
    	return null;
    }
    
    /**
     * 脱敏手机号
     * 
     * @param mobileNo
     * @return
     */
    public static String desensitizationMobile(String mobileNo){
    	if(StringUtils.isNotBlank(mobileNo)){
    		StringBuffer sb = new StringBuffer();
    		sb.append(mobileNo.substring(0, 3));
    		sb.append("****");
    		sb.append(mobileNo.substring(mobileNo.length()-4, mobileNo.length()));
    		
    		return sb.toString();
    	}
    	return null;
    }
    
    /**
     * 脱敏电话号码（在座机和手机号不确定的情况调用）
     * 
     * @param phone
     * @return
     */
    public static String desensitizationPhone(String phone){
    	if(StringUtils.isNotBlank(phone) && phone.length()>4){
    		StringBuffer sb = new StringBuffer();
    		sb.append(phone.substring(0, phone.length()-4));
    		sb.append("****");
    		
    		return sb.toString();
    	}
    	return null;
    }
    
    /**
     * 脱敏银行卡号
     * 
     * @param cardNo
     * @return
     */
    public static String desensitizationCardNo(String cardNo){
    	if(StringUtils.isNotBlank(cardNo)){
    		StringBuffer sb = new StringBuffer();
    		sb.append(cardNo.substring(0, 4));
    		sb.append(" **** **** ");
    		sb.append(cardNo.substring(cardNo.length()-4, cardNo.length()));
    		
    		return sb.toString();
    	}
    	return null;
    }
    
    /**
     * 脱敏身份证号
     * 
     * @param idcardNo
     * @return
     */
    public static String desensitizationIdcardNo(String idcardNo){
    	if(StringUtils.isNotBlank(idcardNo)){
    		StringBuffer sb = new StringBuffer();
    		sb.append(idcardNo.substring(0, 3));
    		if(idcardNo.length() == 18){
    			sb.append("***");
    		}
    		sb.append("********");
    		sb.append(idcardNo.substring(idcardNo.length()-4, idcardNo.length()));
    		
    		return sb.toString();
    	}
    	return null;
    }
}
