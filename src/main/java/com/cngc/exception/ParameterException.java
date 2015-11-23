package com.cngc.exception;

/**
 * 自定义异常处理,描述类..throw new ParameterException("XXXX")
 * @author HP
 * @date 2015-11-19
 */
public class ParameterException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -19256970703722182L;

	public ParameterException() {
		super();
	}

	public ParameterException(String message) {
		super(message);
	}

	public ParameterException(Throwable cause) {
		super(cause);
	}

	public ParameterException(String message, Throwable cause) {
		super(message, cause);
	}
}
