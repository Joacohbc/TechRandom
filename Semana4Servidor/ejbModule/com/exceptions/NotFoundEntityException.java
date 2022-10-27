package com.exceptions;

import javax.ejb.ApplicationException;

@ApplicationException
public class NotFoundEntityException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NotFoundEntityException(String message) {
		super(message);
	}
}
