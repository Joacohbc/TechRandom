package com.services;

import javax.ejb.Remote;

@Remote
public interface SingletonBeanRemote {
	int verValorCompartido();
}
