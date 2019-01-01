
INSERT INTO roles(id,nome)
VALUES(1,'ROLE_ADMIN'),(2,'ROLE_USER');

INSERT INTO usuario(id,nome,password,username)
VALUES(1,'Braian','admin','admin');

INSERT INTO usuario_roles(usuario_id,role_id)
VALUES(1,1),(1,2);

SELECT * FROM usuario;