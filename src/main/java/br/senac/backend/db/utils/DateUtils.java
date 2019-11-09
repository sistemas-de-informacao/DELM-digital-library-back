package br.senac.backend.db.utils;

public class DateUtils {

	static String datePattern = "^([0-2][0-9]|(3)[0-1])(\\/)(((0)[0-9])|((1)[0-2]))(\\/)\\d{4}$";
	
	public static Boolean isValidDate(String data) {
		if (data.matches(datePattern))
			return true;

		return false;
	}

}
