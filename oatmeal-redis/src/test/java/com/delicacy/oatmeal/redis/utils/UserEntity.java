package com.delicacy.oatmeal.redis.utils;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class UserEntity implements Serializable  {

	private static final long serialVersionUID = 1L;
	//用户id
	private String userId;
	//用户账号
	private String EmpCode;
	//用户名称
	private String EmpName;
	//用户角色
	private String role;
	//职位
	private String title;

}