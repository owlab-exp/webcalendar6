package edu.cmu.tsp6.server.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class DateUtil {
	
	public static java.util.Date sql2java(java.sql.Date date){
		
		java.util.Date jDate = null;
		
		if(TimeZone.getDefault().getID().equals("America/New_York")) {
			System.out.println("Current Server Time Zone = America/New_York");
			jDate = date;
			return jDate;
		}
		System.out.println("Current Server Time Zone != America/New_York");
		System.out.println("But, I'll think of the Server Time Zone as 'America/New_York'");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("America/New_York"));
		
		try {
			jDate = sdf.parse(date.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jDate;
	}

}