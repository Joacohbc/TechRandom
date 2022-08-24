package test;

import model.database.BDUtils;

public class Test {

	public static void main(String[] args) {

		try {
			System.out.println(BDUtils.QueryList("SELECT * FROM persona ORDER BY id_persona"));
			System.out.println(BDUtils.QueryMap("SELECT * FROM persona ORDER BY id_persona"));
			System.out.println(BDUtils.QueryOneList("SELECT * FROM persona WHERE id_persona = ?", 4));
			System.out.println(BDUtils.QueryOneMap("SELECT * FROM persona WHERE id_persona = ?", 4));
			System.out.println(BDUtils.QueryExists("SELECT * FROM persona WHERE id_persona = ?", 4));

			// ResultSet rs = SQLExecuter.make("SELECT * FROM persona WHERE id_persona = ?")
			// 		.add(4)
			// 		.queryResultSet();

			// if (rs.next()) {
			// 	System.out.println(rs.getString(3));
			// }

			// SQLExecuter.make("INSERT INTO empleados (cedula, nombre, apellido) values(?, ?, ?)")
			// 		.add("12345678")
			// 		.add("Paola")
			// 		.add("Perez")
			// 		.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

}
