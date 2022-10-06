package com.services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class SinEstadoBean
 */

@Stateless
@LocalBean
public class SinEstadoBean implements SinEstadoBeanRemote {

    /**
     * Default constructor. 
     */
    public SinEstadoBean() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public String saludar(String nombre) {
    	return "Hola, "+ nombre;
    }
}
