INSERT INTO permissao (nome, descricao) VALUES
('CONSULTAR_RESTAURANTES','Permite consultar restaurantes'),
('EDITAR_RESTAURANTES','Permite editar restaurantes')
ON DUPLICATE KEY UPDATE descricao = VALUES(descricao);