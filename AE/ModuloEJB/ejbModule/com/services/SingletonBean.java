package com.services;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

/**
 * Session Bean implementation class SingletonBean
 */

@Singleton
@LocalBean
public class SingletonBean implements SingletonBeanRemote {

    /**
     * Default constructor. 
     */
	
	int valorCompartido;
    public SingletonBean() {
    	valorCompartido = 0;
    }
    
    @Override
    public int verValorCompartido() {
    	return ++valorCompartido;
    }
}
