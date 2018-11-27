
INSERT INTO usuario(id,nome,password,username)
VALUES(1,'Braian','admin','admin');

INSERT INTO usuario_roles(usuario_id,roles)
VALUES(1,'ADMIN'),(1,'USER');

SELECT * FROM usuario;