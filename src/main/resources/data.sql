-- Insert roles if not exist
INSERT IGNORE INTO roles (id, nome) VALUES (1, 'ROLE_ADMIN'), (2, 'ROLE_USER');

-- Insert user with id 1 and associated roles if not exist
INSERT IGNORE INTO usuario (id, nome, email, criado_em, atualizado_em, username, password, criado_por, atualizado_por)
VALUES (1, 'Admin', 'admin@admin.com', '2019-01-15 11:35:38', '2019-01-15 11:35:38', 'admin', '$2a$10$t1zFPnqiDiROXrrCVlizruv4RlGXjnRFCPbaSFWBooOtFYA1wmlxa', 'anonymousUser', 'anonymousUser');

-- Insert user roles
INSERT IGNORE INTO usuario_roles (usuario_id, role_id) VALUES (1, 1), (1, 2);
