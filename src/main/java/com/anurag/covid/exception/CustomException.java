package com.anurag.covid.exception;

import java.util.Date;

public class CustomException
{
	private Date timestamp;
	private String errorMessage;
	private String details;

	public CustomException(String msg, String details)
	{
		this.errorMessage = msg;
		this.timestamp = new Date();
		this.details = details;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getDetails() {
		return details;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

}
