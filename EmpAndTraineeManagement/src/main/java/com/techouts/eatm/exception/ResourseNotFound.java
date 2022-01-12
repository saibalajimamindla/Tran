package com.techouts.eatm.exception;

@SuppressWarnings("serial")
public class ResourseNotFound extends RuntimeException{

	public ResourseNotFound(String message, Throwable cause) {
		super(message, cause);
	}

	public ResourseNotFound(String message) {
		super(message);
	}

	public ResourseNotFound(Throwable cause) {
		super(cause);	
	}

}
