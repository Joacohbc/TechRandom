package primera.logica;

import java.sql.SQLException;

import primera.Funcionalidad;
import primera.Retorno;
import primera.basededatos.BDUtils;

public class FuncionalidadesUtils {
    
    public static Retorno AltaDescripcion(Funcionalidad r) {

        Object[] values = new Object[1];
        values[0] = r.getNombre();
        values[1] = r.getDescripcion();
        
        try {
            BDUtils.ExecuteDML(
                    "insert into funcionalidad values(null,?, ?))",
                    values);
            return new Retorno("Rol dado de alta con exito", null);
        } catch (ClassNotFoundException e) {
            return new Retorno("no se pudo acceder a los driver de conexion", e);
        } catch (SQLException e) {
            return new Retorno("no se pudo dar de alta a el rol", e);
        }
    }

}
