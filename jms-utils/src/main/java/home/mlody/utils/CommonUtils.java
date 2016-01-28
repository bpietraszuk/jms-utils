package home.mlody.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {

	public static Date getDateFromString(String dateString, String format) {

		try {
			DateFormat formatter = new SimpleDateFormat(format);
			return formatter.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
	}

	public static boolean getBooleanValueOfString(String boolValue) {
		return boolValue.equals("true");
	}
}
