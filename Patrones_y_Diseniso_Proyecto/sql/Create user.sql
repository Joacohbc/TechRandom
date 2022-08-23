alter session set "_ORACLE_SCRIPT"=true;

CREATE USER developer IDENTIFIED BY "developer"
DEFAULT TABLESPACE "USERS";

GRANT ALL PRIVILEGES TO developer; 