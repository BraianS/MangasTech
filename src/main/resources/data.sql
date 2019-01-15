
INSERT INTO roles(id,nome)
VALUES(1,'ROLE_ADMIN'),(2,'ROLE_USER');

INSERT INTO usuario(id,nome,email,criado_em,atualizado_em,username,password)
VALUES(1,'Admin','admin@admin.com','2019-01-15 11:35:38','2019-01-15 11:35:38','admin',
'$2a$10$t1zFPnqiDiROXrrCVlizruv4RlGXjnRFCPbaSFWBooOtFYA1wmlxa');

INSERT INTO usuario_roles(usuario_id,role_id)
VALUES(1,1),(1,2);

SELECT * FROM usuario;