package com.hcl.service.exception;


@SuppressWarnings("serial")
public class ServiceException extends RuntimeException {

	String msg = null;

	public ServiceException(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String toString() {
		return msg;
	}

	@Override
	public String getMessage() {
		return msg;
	}
}
