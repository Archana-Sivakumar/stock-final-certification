package com.cognizant.signup.exception;

public class StatusPendingException extends Exception {
	public String msg;

	public StatusPendingException(String msg) {
		super();
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
