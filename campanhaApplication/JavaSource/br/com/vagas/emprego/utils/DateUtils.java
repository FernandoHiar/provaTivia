package br.com.vagas.emprego.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	public static java.util.Date converterData(java.sql.Date dataSql) {
		 java.util.Date dataUtil = new java.util.Date(dataSql.getTime());
		 return dataUtil;
	}
	
	public static java.sql.Date converterData(java.util.Date data) {
		 java.sql.Date dataUtil = new java.sql.Date(data.getTime());
		 return dataUtil;
	}
	
	public static Date converterDataFormato(String data, String formato) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat(formato);
		Date date = dateFormat.parse(data);
		return date;
	}

}
