package com.ikhokha.techcheck;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MetricsProcessor {

	private static final String SHORTERTHAN15 = "SHORTER_THAN_15";
	private static final int FIFTEEN = 15;
	
	private final static Map<String, Integer> resultsMap = new HashMap<>();

	public static Map<String, Integer> process(String line) {

		if (line.length() < FIFTEEN) {
			incOccurrence(resultsMap, SHORTERTHAN15);
		}
		
		EnumSet.allOf(Metrics.class)
		  .forEach(unit -> checkOccurrence(line, unit));
		
		return resultsMap;
	}

	private static void checkOccurrence(String line, Metrics unit) {

		Pattern pattern = Pattern.compile(unit.getUnit());
		Matcher matcher = pattern.matcher(line);
		while (matcher.find()) {
			incOccurrence(resultsMap, unit.getMention());
		}

	}

	public static void incOccurrence(Map<String, Integer> countMap, String key) {

		countMap.putIfAbsent(key, 0);
		countMap.put(key, countMap.get(key) + 1);
	}
}
