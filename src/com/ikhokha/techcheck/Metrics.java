package com.ikhokha.techcheck;

public enum Metrics {
	
	MOVER("(?i).*Mover.*", "MOVER_MENTIONS"), 
	SHAKER("(?i).*Shaker.*", "SHAKER_MENTIONS"), 
	QUESTIONS("([^.?!]*)\\?", "QUESTIONS_MENTIONS"), 
	SPAM("((http:\\/\\/|https:\\/\\/)?(www.)?(([a-zA-Z0-9-]){2,}\\.){1,4}([a-zA-Z]){2,6}(\\/([a-zA-Z-_\\/\\.0-9#:?=&;,]*)?)?)", "SPAM_MENTIONS");
	
private String unit;
private String mention;
	
	private Metrics(String unit, String mention) {
	
		this.unit = unit;
		this.mention = mention;
	}
	public String getUnit() {
		
		return this.unit;
	}
	public String getMention() {
		return mention;
	}
}
