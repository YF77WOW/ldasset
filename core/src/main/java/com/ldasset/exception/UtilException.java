package com.ldasset.exception;

public class UtilException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8186020004557350500L;
	
	private String errorMsg;
	
	public UtilException(String errorMsg){
		super(errorMsg);
		this.setErrorMsg(errorMsg);
	}
	
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
}
