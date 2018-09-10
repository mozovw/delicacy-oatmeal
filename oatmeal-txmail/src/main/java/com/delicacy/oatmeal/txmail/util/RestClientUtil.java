package com.delicacy.oatmeal.txmail.util;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;

/**
 * HTTP请求帮助类
 * @author yangzhilong
 *
 */
public class RestClientUtil {

    private static RestTemplate restTemplate;

    /**
     * 注入实现类
     * @param client
     */
    public static void setRestTemplate(RestTemplate client) {
    	restTemplate = client;
    }

    /**
     * 获取restTemple
     * @return
     */
    public static RestTemplate getRestTemplate(){
        return  restTemplate;
    }


}
