package com.delicacy.oatmeal.txmail.service;

import com.alibaba.fastjson.JSON;

import com.delicacy.oatmeal.txmail.constant.TxMailConstant;
import com.delicacy.oatmeal.txmail.dto.BaseResDto;
import com.delicacy.oatmeal.txmail.dto.dept.DeptCreateDto;
import com.delicacy.oatmeal.txmail.dto.dept.DeptUpdateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.delicacy.oatmeal.txmail.util.TxMailUtil.getAccessToken;
import static com.delicacy.oatmeal.txmail.util.TxMailUtil.getBaseResDto;

/**
 * @author yutao
 * @create 2018-08-17 14:57
 **/
@RestController
@Slf4j
public class MailDeptService {


    public BaseResDto createDept(DeptCreateDto groupCreateDto) {
        log.info("入参:{}", JSON.toJSONString(groupCreateDto));
        return getBaseResDto(TxMailConstant.Dept.CREATE, groupCreateDto);
    }

    public BaseResDto deleteDept(String deptid) {
        log.info("入参:{}",deptid);
        Map<String, String> map = new HashMap<>();
        String accessToken = getAccessToken();
        map.put("access_token", accessToken);
        map.put("id", deptid);
        return getBaseResDto(map, TxMailConstant.Dept.DELETE);
    }

    public BaseResDto updateDept(DeptUpdateDto deptUpdateDto) {
        log.info("入参:{}", JSON.toJSONString(deptUpdateDto));
        return getBaseResDto(TxMailConstant.Dept.UPDATE, deptUpdateDto);
    }

    /*private <T> ResponseEntity<BaseResDto> getPostResult(String url, T t) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<Object> entity = new HttpEntity<>(JSON.toJSONString(t), headers);
        Map<String, String> map = new HashMap<>();
        String accessToken = getAccessToken();
        map.put("access_token", accessToken);
        return RestClientUtil.getRestTemplate().postForEntity(url, entity, BaseResDto.class, map);
    }*/


    /*private String getAccessToken() {
        String corpid = TxMailConstant.CORPID;
        String corpsecret = TxMailConstant.ATTRLIST_CORPSECRET;
        Map<String, String> map = new HashMap<>();
        map.put("corpid", corpid);
        map.put("corpsecret", corpsecret);
        ResponseEntity<TokenDto> tokenResponse = RestClientUtil.getRestTemplate().getForEntity(TxMailConstant.GETTOKEN, TokenDto.class, map);
        return tokenResponse.getBody().getAccess_token();
    }*/

}
