package com.springboot.model;

public class ReverseWord {

private String value;
	
	public ReverseWord() {
        value = "";
    }
	public ReverseWord(final String s) {
        value = s;
    }
	public String getValue() {
        return value;
    }
	
	public void setValue(final String s) {
        value = s;
    }
	public String getReverseString(String s) {
		
		return new StringBuffer(s).reverse().toString();
		
	}
}
