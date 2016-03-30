package com.ldasset.exception;

/**
 * throw exception so the transaction handler could catch it and then roll back
 * at flex request level
 * @author zy
 *
 */
public class ServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4299223530882660459L;

	private String errorMsg;
		
	public ServiceException(String errorMsg){
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
