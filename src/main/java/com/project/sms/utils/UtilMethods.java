package com.project.sms.utils;

import java.util.Calendar;
import java.util.Date;

import com.project.sms.enums.Month;

public class UtilMethods {

	public static Month getMonthFromDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int num = cal.get(Calendar.MONTH);

		Month[] months = { Month.JANUARY, Month.FEBRUARY, Month.MARCH, Month.APRIL, Month.MAY, Month.JUNE, Month.JULY,
				Month.AUGUST, Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER };

		return months[num];
	}

}
