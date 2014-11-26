package com.iwebirth.sxfj.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {
	/**
	 * @param d(java.util.Date)  需要转换的日期(注意：此类包括日期和时间)
	 * @param pattern(java.lang.String) 转换后的模式 eg: "yyyy-MM-dd HH:mm:ss.SSS"
	 * **/
	public static String getCurrentWholeDate(Date d,String pattern){
		DateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(d);
	}

}
