package com.lentux.calciatore.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Utils {
	
	public static List<String> getNazioni(){
	    List<String> listaNazioni = new ArrayList<String>();
	    String[] locales = Locale.getISOCountries();
	    for (String countryCode : locales) {
	        Locale obj = new Locale("", countryCode);
	        listaNazioni.add(obj.getDisplayCountry());
	    }
	    return listaNazioni;
	}

}
