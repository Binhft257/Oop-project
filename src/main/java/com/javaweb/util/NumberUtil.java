package com.javaweb.util;

public class NumberUtil {
	public static boolean isNumber(String x) {
		try {
			Long number= Long.parseLong(x);
		}
		catch(NumberFormatException ex) {
			return false;	
		}
		return true;
	}
}

