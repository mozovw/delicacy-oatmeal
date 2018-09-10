package com.delicacy.oatmeal.txmail.service;

import com.alibaba.fastjson.JSON;
import com.delicacy.oatmeal.txmail.constant.TxMailConstant;
import com.delicacy.oatmeal.txmail.dto.BaseResDto;
import com.delicacy.oatmeal.txmail.dto.user.UserCreateDto;
import com.delicacy.oatmeal.txmail.dto.user.UserUpdateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
public class MailUserService {

    @PostMapping("createUser")
    public BaseResDto createUser(@RequestBody UserCreateDto groupCreateDto) {
        log.info("入参:{}", JSON.toJSONString(groupCreateDto));
        return getBaseResDto(TxMailConstant.User.CREATE, groupCreateDto);
    }

    public BaseResDto deleteUser(String userid) {
        log.info("入参:{}", JSON.toJSONString(userid));
        Map<String, String> map = new HashMap<>();
        String accessToken = getAccessToken();
        map.put("access_token", accessToken);
        map.put("userid", userid);
        return getBaseResDto(map, TxMailConstant.User.DELETE);
    }
    @PostMapping("updateUser")
    public BaseResDto updateUser(@RequestBody UserUpdateDto userUpdateDto) {
        log.info("入参:{}", JSON.toJSONString(userUpdateDto));
        return getBaseResDto(TxMailConstant.User.UPDATE, userUpdateDto);
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
    }


    private String getAccessToken() {
        String corpid = TxMailConstant.CORPID;
        String corpsecret = TxMailConstant.ATTRLIST_CORPSECRET;
        Map<String, String> map = new HashMap<>();
        map.put("corpid", corpid);
        map.put("corpsecret", corpsecret);
        ResponseEntity<TokenDto> tokenResponse = RestClientUtil.getRestTemplate().getForEntity(TxMailConstant.GETTOKEN, TokenDto.class, map);
        return tokenResponse.getBody().getAccess_token();
    }*/

}
