package com.delicacy.oatmeal.redis.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class Country implements Serializable {
	
	
	private static final long serialVersionUID = 7081085638230983457L;

	private int countryId;

	private String countryName;

}
