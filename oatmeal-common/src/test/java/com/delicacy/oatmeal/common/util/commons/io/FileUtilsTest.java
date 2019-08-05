package com.delicacy.oatmeal.common.util.commons.io;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;


public class FileUtilsTest{
	String str  = "src/exam/resources/com/delicacy/oatmeal/commons/io/tt.txt";
	@Test
	public void test() throws IOException{
		File file = FileUtils.getUserDirectory();
		System.out.println(file.getAbsolutePath());
		String readFileToString = FileUtils.readFileToString(new File(str));
		System.out.println(readFileToString);
		FileUtils.write(new File(str), readFileToString, false);
	}
}
