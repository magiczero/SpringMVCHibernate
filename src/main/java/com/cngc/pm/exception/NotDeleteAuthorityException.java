package com.cngc.pm.exception;

public class NotDeleteAuthorityException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final Long id;
	private final String className;
	private final String username;

	public NotDeleteAuthorityException(Long id, String className, String username) {
		this.id = id;
		this.className = className;
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public String getClassName() {
		return className;
	}

	public String getUsername() {
		return username;
	}
	
	
}
