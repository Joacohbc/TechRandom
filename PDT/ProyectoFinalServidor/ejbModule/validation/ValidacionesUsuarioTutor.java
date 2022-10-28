package validation;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.entities.enums.TipoTutor;

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
    
    public static ValidationObject ValidarTipo (TipoTutor tipo) {
		if (tipo != null) {
			return ValidationObject.VALID;
		} else {
			return new ValidationObject("El tipo de Tutor es obligatorio");
		}
    }
    
    public static ValidationObject ValidarArea (String area) {
    	
    	if (Validaciones.ValidarNumerosYLetras(area, false)) {
    		if (area.length() <= 50) {
    			return ValidationObject.VALID;
			}else {
				return new ValidationObject("El Area deben tener un maximo de 50 caracteres");						
			}
		}else {
			return new ValidationObject("El Area solo puede contener letras y numeros");
		}
    	
    }

}
