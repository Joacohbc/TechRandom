insert into persona values(null,'65431234','Gonzales','Fernandes','Brunos','','17/03/98','bruno.gonzalez.f@gmail.com','deveeloper',(select id_rol from rol where rol.nombre='Administrador'));
insert into persona values(null,'12345678','Genova','Guerendiain','Joaquin','','08/09/03','joaquin.genova@gmail.com','developer',2);
insert into persona values(null,'87654321','Machado','Moreira','William','Federico','05/02/83','william.machado@gmail.com','developer',3);
insert into persona values(null,'12385473','Fontes','Aguirre','Cristopher','','12/11/95','cristopher.fontes@gmail.com','developer',1);

insert into rol values(NULL,'Administrador','Administra el sistema');
insert into rol values(NULL,'Jefe de seccion','Jefe de sector');
insert into rol values(NULL,'Operador','Visualiza areas');

insert into funcionalidad values(1,'Control de Inventario','Controla el inventario');
insert into funcionalidad values(2,'Ventas','Visualiza las ventas');
insert into funcionalidad values(3,'Compras','Visualiza las compras');
insert into funcionalidad values(4,'Cuentas corrientes','Visualiza las cuentas');
insert into funcionalidad values(5,'Sueldos','Visualiza los sueldos');

insert into rol_funcionalidad values (NULL,1,1);
insert into rol_funcionalidad values (NULL,1,2);
insert into rol_funcionalidad values (NULL,1,3);
insert into rol_funcionalidad values (NULL,1,4);
insert into rol_funcionalidad values (NULL,1,5);

insert into rol_funcionalidad values (NULL,2,1);
insert into rol_funcionalidad values (NULL,2,2);
insert into rol_funcionalidad values (NULL,2,3);
insert into rol_funcionalidad values (NULL,2,4);
insert into rol_funcionalidad values (NULL,2,5);

insert into rol_funcionalidad values (NULL,3,1);
insert into rol_funcionalidad values (NULL,3,2);
insert into rol_funcionalidad values (NULL,3,3);
insert into rol_funcionalidad values (NULL,3,4);
insert into rol_funcionalidad values (NULL,3,5);