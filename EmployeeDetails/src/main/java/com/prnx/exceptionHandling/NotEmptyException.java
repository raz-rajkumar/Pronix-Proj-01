package com.prnx.exceptionHandling;

public class NotEmptyException extends RuntimeException{

	public NotEmptyException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NotEmptyException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NotEmptyException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
