package com.delicacy.oatmeal.easypoi.word.util;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.delicacy.oatmeal.easypoi.excel.entity.CourseEntity;
import com.delicacy.oatmeal.easypoi.excel.entity.StudentEntity;
import com.delicacy.oatmeal.easypoi.excel.entity.TeacherEntity;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.word.WordExportUtil;
import org.jeecgframework.poi.word.entity.WordImageEntity;
import org.jeecgframework.poi.word.entity.params.ExcelListEntity;
import org.junit.Test;




public class WordExportUtilTest  {

	@Test
	public void test_total() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String curTime = format.format(new Date());

		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("type", "个人所得税");
		map1.put("presum", "1580");
		map1.put("thissum", "1750");
		map1.put("curmonth", "1-11月");
		map1.put("now", curTime);
		mapList.add(map1);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("type", "增值税");
		map2.put("presum", "1080");
		map2.put("thissum", "1650");
		map2.put("curmonth", "1-11月");
		map2.put("now", curTime);
		mapList.add(map2);
		map.put("taxlist", mapList);
		map.put("totalpreyear", "2660");
		map.put("totalthisyear", "3400");
		Map<String, String> total = new HashMap<String, String>();
		total.put("orderId", "2660");
		total.put("orderCreateTime", "3400");
		total.put("supplyName", "3410");
		map.put("order", total);
		try {
			XWPFDocument doc = WordExportUtil
					.exportWord07(
							"com/delicacy/oatmeal/easypoi/word/util/wordexport_contract.docx",
							map);
			FileOutputStream fos = new FileOutputStream(
					"src/test/resources/com/delicacy/oatmeal/easypoi/word/util/wordexport_contract_2.docx");
			doc.write(fos);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd");

	@Test
	public void test_excel() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd");
		List<CourseEntity> list = new ArrayList<CourseEntity>();
		CourseEntity courseEntity = new CourseEntity();
		courseEntity.setId("1131");
		courseEntity.setName("小白");

		TeacherEntity teacherEntity = new TeacherEntity();
		teacherEntity.setId("12131231");
		teacherEntity.setName("你们");
		courseEntity.setTeacher(teacherEntity);

		teacherEntity = new TeacherEntity();
		teacherEntity.setId("121312314312421131");
		teacherEntity.setName("老王");
		courseEntity.setShuxueteacher(teacherEntity);

		StudentEntity studentEntity = new StudentEntity();
		studentEntity.setId("11231");
		studentEntity.setName("撒旦法司法局");
		studentEntity.setBirthday(new Date());
		studentEntity.setSex(1);
		List<StudentEntity> studentList = new ArrayList<StudentEntity>();
		studentList.add(studentEntity);
		studentList.add(studentEntity);
		courseEntity.setStudents(studentList);

		for (int i = 0; i < 3; i++) {
			list.add(courseEntity);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("department", "Jeecg");
		map.put("person", "JueYue");
		map.put("auditPerson", "JueYue");
		map.put("time", format.format(new Date()));
		map.put("cs", new ExcelListEntity(list, CourseEntity.class));
		try {
			XWPFDocument doc = WordExportUtil
					.exportWord07(
							"com/delicacy/oatmeal/easypoi/word/util/wordexport_excel.docx",
							map);
			FileOutputStream fos = new FileOutputStream(
					"src/test/resources/com/delicacy/oatmeal/easypoi/word/util/wordexport_excel_2.docx");
			doc.write(fos);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 简单导出包含图片
	 */
	@Test
	public void test_image() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("department", "Jeecg");
		map.put("time", format.format(new Date()));
		Person person = new Person();
		person.setName("JueYue");
		map.put("p", person);
		WordImageEntity image = new WordImageEntity();
		image.setHeight(200);
		image.setWidth(500);
		image.setUrl("com/delicacy/oatmeal/easypoi/word/util/testCode.png");
		image.setType(WordImageEntity.URL);
		map.put("testCode", image);
		try {
			XWPFDocument doc = WordExportUtil
					.exportWord07(
							"com/delicacy/oatmeal/easypoi/word/util/wordexport_image.docx",
							map);
			FileOutputStream fos = new FileOutputStream(
					"src/test/resources/com/delicacy/oatmeal/easypoi/word/util/wordexport_image_2.docx");
			doc.write(fos);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class Person {

		@Excel(name = "姓名")
		private String name;
		@Excel(name = "手机")
		private String tel;
		@Excel(name = "Email")
		private String email;

		public String getEmail() {
			return email;
		}

		public String getName() {
			return name;
		}

		public String getTel() {
			return tel;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setTel(String tel) {
			this.tel = tel;
		}

	}

	/**
	 * 简单导出没有图片和Excel
	 */
	@Test
	public void test_map() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("department", "Jee\r\ncg");
		map.put("person", "Jue\r\nYue");
		map.put("auditPerson", "JueYue");
		map.put("time", format.format(new Date()));
		map.put("date", new Date());
		List<Person> list = new ArrayList<Person>();
		Person p = new Person();
		p.setName("小明");
		p.setTel("18711111111");
		p.setEmail("18711111111@\\r\\n139.com");
		list.add(p);
		p = new Person();
		p.setName("小红");
		p.setTel("18711111112");
		p.setEmail("18711111112@\r\n139.com");
		list.add(p);
		map.put("pList", list);
		try {
			XWPFDocument doc = WordExportUtil.exportWord07(
					"com/delicacy/oatmeal/easypoi/word/util/workexport_map.docx",
					map);
			FileOutputStream fos = new FileOutputStream(
					"src/test/resources/com/delicacy/oatmeal/easypoi/word/util/workexport_map_2.docx");
			doc.write(fos);
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
