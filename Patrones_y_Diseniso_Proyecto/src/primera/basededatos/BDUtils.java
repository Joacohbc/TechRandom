package primera.basededatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BDUtils {
	// Usaba esto para hacer debug de los errores (por eso esta en false)
		public static boolean ShowErrors = false;

		// Metodo para cerrar la Conexion,(para hacer mas clean el Finally)
		public static void Close(Object obj) {
			try {
				if (obj == null) {
					return;
				}

				if (obj instanceof Connection) {
					Connection conn = ((Connection) obj);
					if (!conn.isClosed())
						conn.close();
				} else if (obj instanceof PreparedStatement) {
					PreparedStatement ps = ((PreparedStatement) obj);
					if (!ps.isClosed())
						ps.close();
				} else if (obj instanceof ResultSet) {
					ResultSet rs = ((ResultSet) obj);
					if (!rs.isClosed())
						rs.close();
				} else if (obj instanceof Statement) {
					Statement s = ((Statement) obj);
					if (!s.isClosed())
						s.close();
				}

			} catch (Exception e) {
				if (ShowErrors) {
					System.out.println("> Error: al cerrar el objeto");
					e.printStackTrace();
				}
			}
		}

		// Este metodo ejecuta el DML (insert, update y delete)
		public static int ExecuteDML(String sql, Object[] values) throws ClassNotFoundException, SQLException {
			Connection conn = null;
			PreparedStatement ps = null;

			try {
				conn = Conexion.getConnection();
				ps = conn.prepareStatement(sql);

				for (int i = 1; i <= values.length; i++) {
					System.out.println(values[i -1 ]+ " " + i);
					ps.setObject(i, values[i - 1]);
				}

				int n = ps.executeUpdate();
				String rows = n > 0 ? n + " rows afectadas" : "Ninguna afectadas";
				System.out.println("La accion se ejecuto con exito (" + rows + ")");
				return n;
				
			} finally {
				Close(conn);
				Close(ps);
			}

		}

		// Este metodo ejecuta los SELECTs
		public static boolean ExecuteQuery(String sql, Object[] values, int countRows) throws ClassNotFoundException, SQLException {
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				conn = Conexion.getConnection();

				ps = conn.prepareStatement(sql);
				for (int i = 1; i <= values.length; i++) {
					System.out.println("-> " + values[i - 1] + " " + i);
					ps.setObject(i, values[i - 1]);
				}
				rs = ps.executeQuery();

				while (rs.next()) {
					for (int i = 1; i <= countRows; i++) {
						System.out.print(" " + rs.getObject(i).toString());
					}
					System.out.println("\n");
				}
				return true;

			} finally {
				Close(conn);
				Close(ps);
				Close(rs);
			}
		}

}
