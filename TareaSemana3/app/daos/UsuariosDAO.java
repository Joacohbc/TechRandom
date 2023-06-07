package daos;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import entites.Departamento;
import entites.Genero;
import entites.Usuario;

/**
 * Session Bean implementation class UsuariosDAO
 */
@Singleton
@LocalBean
public class UsuariosDAO {

	@PersistenceContext
	private EntityManager em;

	public UsuariosDAO() {
	}

	// ACLARACION:
	// Implemenamos una simplificacion del DAO ya que seria muy complejo implementarlos 3 tipos de usuarios
	
	public Usuario insert(Usuario usuario) throws DAOException {
		try {
			em.persist(usuario);
			em.flush();
			return usuario;
		} catch (PersistenceException e) {
			throw new DAOException("Ocurrió un error al dar de alta al Usuario: " + e.getMessage());
		}
	}
	
	public List<Usuario> findAll() {
		return em.createQuery("Select u FROM Usuario  u", Usuario.class).getResultList();
	}


	public Long getUserID(String username, String password) {
		try {
			return em.createQuery("SELECT u.idUsuario FROM Usuario u WHERE u.nombreUsuario = ?1 AND u.contrasena = ?2",
					Long.class).setParameter(1, username).setParameter(2, password).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Usuario findById(Long id) {
		return em.find(Usuario.class, id);
	}

    private Usuario crearUsuario(String documento, String nombreUsuario, String nombres, String apellidos, Departamento departamento, Genero genero, String telefono, String emailPersonal, String emailUtec, String localidad, String contrasena) throws NoSuchAlgorithmException {
        Usuario u = new Usuario();
        u.setDocumento(documento);
        u.setNombreUsuario(nombreUsuario);
        u.setNombres(nombres);
        u.setApellidos(apellidos);
        u.setDepartamento(departamento);
        u.setGenero(genero);
        u.setTelefono(telefono);
        u.setEmailPersonal(emailPersonal);
        u.setEmailUtec(emailUtec);
        u.setLocalidad(localidad);
        u.setContrasena(Usuario.toMD5(contrasena));
        u.setFecNacimiento(LocalDate.now());
        return u;
    }
    
	@PostConstruct
	private void init() {
        try {
            Usuario u1 = crearUsuario("5.362.666-3", "joaquin.genova", "Joaquin", "Genova", Departamento.MONTEVIDEO, Genero.MASCULINO, "0941234323", "joaco@gmail.com", "joaquin.genova@utec.edu.uy", "Villa Española", "1234");
            Usuario u2 = crearUsuario("4.815.162-5", "maria.garcia", "Maria", "Garcia", Departamento.CANELONES, Genero.FEMENINO, "0912345678", "maria@gmail.com", "maria.garcia@utec.edu.uy", "Las Piedras", "5678");
            Usuario u3 = crearUsuario("1.234.567-8", "juan.lopez", "Juan", "Lopez", Departamento.SAN_JOSE, Genero.MASCULINO, "0987654321", "juan@gmail.com", "juan.lopez@utec.edu.uy", "San Jose", "9876");
            
            insert(u1);
            insert(u2);
            insert(u3);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error al generar el hash MD5 de la contraseña.");
            return;
        }
	}
	
//	/**
//	 * Retorna el ID de un usuario (de la Entidad Usuario) en base a su nombre de
//	 * usuario y contraseña (sin inscriptar).
//	 * 
//	 * @return Si no hay resultado retornara null
//	 */
//	public Long getUserID(String username, String password) {
//		try {
//			return em.createQuery("SELECT u.idUsuario FROM Usuario u WHERE u.nombreUsuario = ?1 AND u.contrasena = ?2",
//					Long.class).setParameter(1, username).setParameter(2, password).getSingleResult();
//		} catch (NoResultException e) {
//			return null;
//		}
//	}

//	/**
//	 * Retorna el Tipo de Usuario (Estudiante.class, Analista.class, Tutor.class) en
//	 * base a su ID.
//	 * 
//	 * @return Si no hay resultado retornara null
//	 */
//	public <T extends Usuario> T findById(Class<T> tipoUsu, Long id) {
//		return em.find(tipoUsu, id);
//	}
//
//	/**
//	 * Retorna el Tipo de Usuario (Estudiante.class, Analista.class, Tutor.class) en
//	 * base a su Nombre de Usuario.
//	 * 
//	 * @return Si no hay resultado retornara null
//	 */
//	public <T extends Usuario> T findByNombreUsuario(Class<T> tipoUsu, String nombreUsuario) {
//		try {
//			return em.createQuery("SELECT u FROM " + tipoUsu.getName() + " u WHERE u.nombreUsuario = ?1", tipoUsu)
//					.setParameter(1, nombreUsuario).getSingleResult();
//		} catch (NoResultException e) {
//			return null;
//		}
//	}
//
//	/**
//	 * Retorna el Tipo de Usuario (Estudiante.class, Analista.class, Tutor.class) en
//	 * base a su Email.
//	 * 
//	 * @return Si no hay resultado retornara null
//	 */
//	public <T extends Usuario> T findByEmailUtec(Class<T> tipoUsu, String email) {
//		try {
//			return em.createQuery("SELECT u FROM " + tipoUsu.getName() + " u WHERE u.emailUtec = ?1", tipoUsu)
//					.setParameter(1, email).getSingleResult();
//		} catch (NoResultException e) {
//			return null;
//		}
//	}
//	
//	/**
//	 * Retorna el Tipo de Usuario (Estudiante.class, Analista.class, Tutor.class) en
//	 * base a su Email.
//	 * 
//	 * @return Si no hay resultado retornara null
//	 */
//	public <T extends Usuario> T findByEmailPersonal(Class<T> tipoUsu, String email) {
//		try {
//			return em.createQuery("SELECT u FROM " + tipoUsu.getName() + " u WHERE u.emailPersonal = ?1", tipoUsu)
//					.setParameter(1, email).getSingleResult();
//		} catch (NoResultException e) {
//			return null;
//		}
//	}
//
//	/**
//	 * Retorna el Tipo de Usuario (Estudiante.class, Analista.class, Tutor.class) en
//	 * base a su Documento.
//	 * 
//	 * @return Si no hay resultado retornara null
//	 */
//	public <T extends Usuario> T findByDocumento(Class<T> tipoUsu, String documento) {
//		try {
//			return em.createQuery("SELECT u FROM " + tipoUsu.getName() + " u WHERE u.documento = ?1", tipoUsu)
//					.setParameter(1, documento).getSingleResult();
//		} catch (NoResultException e) {
//			return null;
//		}
//	}
//
//	
//	/**
//	 * Retorna una Lista de Usuarios del Tipo de Usuario que se le indique.
//	 * 
//	 * @return Si no hay resultados retorna una lista vacia.
//	 */
//	public <T extends Usuario> List<T> findAll(Class<T> tipoUsu) {
//		return em.createQuery("Select u FROM " + tipoUsu.getName() + " u", tipoUsu).getResultList();
//	}

//	/**
//	 * Retorna una Lista de Usuarios del Tipo de Usuario que se le indique, pudiendo
//	 * aplicar filtros por EstadoUsuario y ITRs.
//	 * 
//	 * @return Si no hay resultados retorna una lista vacia.
//	 */
//	public <T extends Usuario> List<T> findAll(Class<T> tipoUsu, EstadoUsuario estado, Itr itr) {
//		return em.createQuery(
//				"SELECT u FROM " + tipoUsu.getName() + " u WHERE u.estadoUsuario = ?1 AND u.itr.idItr = ?2", tipoUsu)
//				.setParameter(1, estado).setParameter(2, itr.getIdItr()).getResultList();
//	}
//
//	/**
//	 * Realiza un Updatede la Entidad con ese ID.
//	 * 
//	 * @exception DAOException Si ocurrio un error al realizar el Merge()
//	 */
//	public Usuario update(Usuario usuario) throws DAOException, NotFoundEntityException {
//		try {
//			usuario = em.merge(usuario);
//			em.flush();
//			return usuario;
//		} catch (PersistenceException e) {
//			throw new DAOException("Ocurrio un error al cambiar el estado del usuario:", e);
//		}
//	}
//	
//	public Estudiante update(Estudiante estudiante) throws DAOException, NotFoundEntityException {
//		try {
//			estudiante = em.merge(estudiante);
//			em.flush();
//			return estudiante;
//		} catch (PersistenceException e) {
//			throw new DAOException("Ocurrio un error al cambiar el estado del usuario:", e);
//		}
//	}
//	
//	public Analista update(Analista analista) throws DAOException, NotFoundEntityException {
//		try {
//			analista = em.merge(analista);
//			em.flush();
//			return analista;
//		} catch (PersistenceException e) {
//			throw new DAOException("Ocurrio un error al cambiar el estado del usuario:", e);
//		}
//	}
//	
//	public Tutor update(Tutor tutor) throws DAOException, NotFoundEntityException {
//		try {
//			tutor = em.merge(tutor);
//			em.flush();
//			return tutor;
//		} catch (PersistenceException e) {
//			throw new DAOException("Ocurrio un error al cambiar el estado del usuario:", e);
//		}
//	}
//
//	public List<Estudiante> findAllEstudiante() {
//		return em.createQuery("SELECT e FROM Estudiante e", Estudiante.class).getResultList();
//	}
}
