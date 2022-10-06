package main;

import view.login.LoginView;

public class App {
	public static void main(String[] args) {
		new LoginView().setVisible(true);

		//Insertar todas las funcionalidades
//		for (Funcionalidad f : Funcionalidad.values()) {
//			DAOFuncionalidad.insert(f);
//		}
//		insert into rol values(NULL,'Administrador','Administra el sistema');
//		insert into persona values(null,'65431234','Gonzales','Fernandes','Brunos','','17/03/98','admin@admin.com','developer',(select id_rol from rol where rol.nombre='Administrador'));
//		insert into rol_funcionalidad values (NULL,1,1);
//		insert into rol_funcionalidad values (NULL,1,2);
//		insert into rol_funcionalidad values (NULL,1,3);
//		insert into rol_funcionalidad values (NULL,1,4);
//		insert into rol_funcionalidad values (NULL,1,5);
//		insert into rol_funcionalidad values (NULL,1,6);
//		insert into rol_funcionalidad values (NULL,1,7);
//		insert into rol_funcionalidad values (NULL,1,8);
//		insert into rol_funcionalidad values (NULL,1,9);
	}
}
