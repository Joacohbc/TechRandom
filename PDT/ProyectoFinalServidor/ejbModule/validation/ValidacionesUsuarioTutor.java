package validation;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class ValidacionesUsuarioTutor
 */
@Stateless
@LocalBean
public class ValidacionesUsuarioTutor {

    /**
     * Default constructor. 
     */
    public ValidacionesUsuarioTutor() {
        // TODO Auto-generated constructor stub
    }
    
    public static ValidationObject ValidarTipo (String tipo) {
    		
    		if (tipo != null) {
				
    			return ValidationObject.VALID;
			}else {
				return new ValidationObject("El tipo de Tutor es obligatorio");
			}
			
    }
    
    
    public static ValidationObject ValidarArea (String area) {
    	
    	if (Validaciones.ValidarNumerosYLetras(area, false)) {
			
    		if (area.length() <= 50) {
    			
    			return ValidationObject.VALID;
				
			}else {
				
				return new ValidationObject("Area deben tener un maximo de 100 caracteres");						
			}
    		
		}else {
			
			return new ValidationObject("El Area solo puede contener letras y numeros");
		}
    	
    }

}
