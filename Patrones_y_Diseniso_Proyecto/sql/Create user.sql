alter session set "_ORACLE_SCRIPT"=true;

CREATE USER miniproyecto
IDENTIFIED BY miniproyecto
DEFAULT TABLESPACE USERS;

GRANT 
CREATE SESSION, RESOURCE, SELECT_CATALOG_ROLE
TO miniproyecto;

ALTER USER miniproyecto quota unlimited on USERS;