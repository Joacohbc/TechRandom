package com.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ServiceException(String message) {
		super(message);
	}
}
