package com.delicacy.oatmeal.common.utils.commons.lang3.time;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

public class DateUtilsTest {
	
	@Test
	public void test() throws ParseException{
		Date parseDate = DateUtils.parseDate("2016-01-01 10:20:33", "yyyy-MM-dd hh:mm:ss");
		System.out.println(parseDate.toString());
		parseDate = DateUtils.addHours(parseDate, 2);
		System.out.println(parseDate.toString());
		parseDate = DateUtils.ceiling(parseDate, Calendar.DATE);
		System.out.println(parseDate.toString());
		boolean b = DateUtils.isSameDay(new Date(), new Date());
		System.out.println(b);
		parseDate = DateUtils.round(parseDate, Calendar.HOUR);
		System.out.println(parseDate);
		String string = DateFormatUtils.format(new Date(), DateFormatUtils.ISO_DATETIME_FORMAT.getPattern());
		System.out.println(string);
	}
}
