CREATE TABLE museo (
    id_museo   NUMBER
        GENERATED BY DEFAULT ON NULL AS IDENTITY START WITH 1 INCREMENT BY 1,
    nombre     VARCHAR(50) NOT NULL,
    direccion  VARCHAR(50) NOT NULL,
    CONSTRAINT pk_museo PRIMARY KEY ( id_museo ),
    CONSTRAINT un_museo_nom UNIQUE ( nombre ),
    CONSTRAINT un_museo_dir UNIQUE ( direccion )
);

CREATE TABLE cuadro (
    id_cuadro  NUMBER
        GENERATED BY DEFAULT ON NULL AS IDENTITY START WITH 1 INCREMENT BY 1,
    nombre     VARCHAR(50) NOT NULL,
    autor      VARCHAR(50) NOT NULL,
    id_museo   NUMBER NOT NULL,
    CONSTRAINT pk_cuadro PRIMARY KEY ( id_cuadro ),
    CONSTRAINT un_cuadro_nom UNIQUE ( nombre )
);

ALTER TABLE cuadro
    ADD CONSTRAINT fk_cuadro_museo FOREIGN KEY ( id_museo )
        REFERENCES museo ( id_museo );