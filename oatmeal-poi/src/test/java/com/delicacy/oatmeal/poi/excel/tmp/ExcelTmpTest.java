package com.delicacy.oatmeal.poi.excel.tmp;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.delicacy.oatmeal.poi.excel.tmp.entity.*;
import com.delicacy.oatmeal.poi.excel.tmp.util.ExcelTmpUtil;



public class ExcelTmpTest {

	@Test
	public void testTeplaceModel() {

		List<ExcelData> datas = new ArrayList<ExcelData>();
		ExcelData vo1 = new ExcelData();
		vo1.setKey("dd");
		vo1.setValue("XXX有限公司");
		ExcelData vo2 = new ExcelData();
		vo2.setRow(1);
		vo2.setColumn(5);
		vo2.setValue("aa替换的内容aa");
		datas.add(vo1);
		datas.add(vo2);
		ExcelTmpUtil
				.replaceModel(	
						datas,
						"src/test/resources/com/delicacy/oatmeal/poi/excel/tmp/excel/2k7.xlsx",
						"src/test/resources/com/delicacy/oatmeal/poi/excel/tmp/excel/2k7-2.xlsx");
	}

	@Test
	public void testTeplaceModel2() {
		List<ExcelData> datas = new ArrayList<ExcelData>();
		ExcelData vo1 = new ExcelData();
		vo1.setKey("修订");
		vo1.setValue("新增");
		datas.add(vo1);
		ExcelTmpUtil
				.replaceModel(
						datas,
						"src/test/resources/com/delicacy/oatmeal/poi/excel/tmp/excel/aa.xlsx",
						"src/test/resources/com/delicacy/oatmeal/poi/excel/tmp/excel/bb.xlsx");
	}
}