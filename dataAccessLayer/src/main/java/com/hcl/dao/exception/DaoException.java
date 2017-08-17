package com.hcl.dao.exception;

@SuppressWarnings("serial")
public class DaoException extends RuntimeException {

	String msg = null;

	public DaoException(String msg) {
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
