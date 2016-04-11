package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateParser {

	/**
	 * 
	 * @param str a date with format "YYYY-MM-DD-hh-mm-ss"
	 * @return java.util.Date
	 */
	public static Date string2Date(String str){
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		
		Date date = null;
		
		try {
			date = format.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	public static String stamp2String(long timestamp){
		Date date = new Date(timestamp);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		
		String str = format.format(date);
		
		return str;
	}
	
}
