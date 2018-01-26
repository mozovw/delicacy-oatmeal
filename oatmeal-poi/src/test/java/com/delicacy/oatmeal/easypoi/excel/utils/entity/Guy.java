package com.delicacy.oatmeal.easypoi.excel.utils.entity;

import org.jeecgframework.poi.excel.annotation.Excel;

public class Guy {
	@Excel(name = "电话号码")
	private String tel;
	@Excel(name = "姓名")
	private String name;
	@Excel(name = "分组")
	private String group;
	@Excel(name = "备注")
	private String remark;
	@Excel(name = "出生日期")
	private String birthday;

	public String getTel() {

		return tel;
	}

	public void setTel(String tel) {

		this.tel = tel;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getGroup() {

		return group;
	}

	public void setGroup(String group) {

		this.group = group;
	}

	public String getRemark() {

		return remark;
	}

	public void setRemark(String remark) {

		this.remark = remark;
	}

	public String getBirthday() {

		return birthday;
	}

	public void setBirthday(String birthday) {

		this.birthday = birthday;
	}

}
