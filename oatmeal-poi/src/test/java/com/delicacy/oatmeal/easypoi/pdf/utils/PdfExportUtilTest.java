package com.delicacy.oatmeal.easypoi.pdf.utils;

import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.pdf.PdfExportUtil;
import org.jeecgframework.poi.pdf.entity.PdfExportParams;
import org.junit.Test;

import com.delicacy.oatmeal.easypoi.excel.utils.entity.*;
import com.delicacy.oatmeal.easypoi.pdf.utils.entity.MsgClient;
import com.delicacy.oatmeal.easypoi.pdf.utils.entity.MsgClientGroup;
import com.itextpdf.text.Document;


public class PdfExportUtilTest  {

	@Test
	public void test_export() {
		List<CourseEntity> list = new ArrayList<CourseEntity>();
		CourseEntity courseEntity;

		for (int i = 0; i < 50; i++) {
			courseEntity = new CourseEntity();
			courseEntity.setId("1131");
			courseEntity.setName("海贼王必修(" + (i + 1) + ")");

			TeacherEntity teacherEntity = new TeacherEntity();
			teacherEntity.setId("12131231");
			teacherEntity.setName("路飞");
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
			list.add(courseEntity);

			PdfExportParams params = new PdfExportParams("绝月的测试", "作者绝月");
			Document document = null;
			try {
				document = PdfExportUtil
						.exportPdf(
								params,
								CourseEntity.class,
								list,
								new FileOutputStream(
										"src/test/resources/com/delicacy/oatmeal/easypoi/pdf/utils/pdfexport.pdf"));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				document.close();
			}
		}
	}

	@Test
	public void test_msg() {
		Field[] fields = MsgClient.class.getFields();
		for (int i = 0; i < fields.length; i++) {
			Excel excel = fields[i].getAnnotation(Excel.class);
			System.out.println(excel);
		}
		List<MsgClient> list = new ArrayList<MsgClient>();
		for (int i = 0; i < 10; i++) {
			MsgClient client = new MsgClient();
			client.setBirthday(new Date());
			client.setClientName("小明" + i);
			client.setClientPhone("18797" + i);
			client.setCreateBy("JueYue");
			client.setId("1" + i);
			client.setRemark("测试" + i);
			MsgClientGroup group = new MsgClientGroup();
			group.setGroupName("测试" + i);
			client.setGroup(group);
			list.add(client);
		}
		PdfExportParams params = new PdfExportParams("2412312", null);
		try {
			Document document = PdfExportUtil
					.exportPdf(
							params,
							MsgClient.class,
							list,
							new FileOutputStream(
									"src/test/resources/com/delicacy/oatmeal/easypoi/pdf/utils/pdfexport_msg.pdf"));
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test_image() {
		List<StudentEntityImage> studentList = new ArrayList<StudentEntityImage>();
		StudentEntityImage StudentEntityImage = new StudentEntityImage();
		StudentEntityImage.setId("11231");
		StudentEntityImage.setName("撒旦法司法局");
		StudentEntityImage.setBirthday(new Date());
		StudentEntityImage.setSex(1);
		StudentEntityImage
				.setImage("com/delicacy/oatmeal/easypoi/pdf/utils/test.png");
		studentList.add(StudentEntityImage);
		studentList.add(StudentEntityImage);

		PdfExportParams params = new PdfExportParams("学生信息");
		try {
			PdfExportUtil
					.exportPdf(
							params,
							StudentEntityImage.class,
							studentList,
							new FileOutputStream(
									"src/test/resources/com/delicacy/oatmeal/easypoi/pdf/utils/pdfexport_image.pdf"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class StudentEntityImage extends StudentEntity {
		private static final long serialVersionUID = 1L;
		@Excel(height = 40, width = 60, type = 2, name = "相片")
		private String image;

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}
	}

}
