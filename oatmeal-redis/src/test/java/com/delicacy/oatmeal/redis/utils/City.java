package com.delicacy.oatmeal.redis.utils;

import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class City implements Serializable {

	private static final long serialVersionUID = 4170924808606201713L;

	private int cityId;

	private String cityName;



}
