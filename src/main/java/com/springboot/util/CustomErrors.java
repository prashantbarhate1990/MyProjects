package com.springboot.util;

public class CustomErrors {
	
	private String errorMessage;

    public CustomErrors(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
