ALTER TABLE pedido
ADD COLUMN usuario_cliente_id BIGINT NOT NULL;

ALTER TABLE pedido
ADD CONSTRAINT fk_pedido_usuario_cliente
FOREIGN KEY (usuario_cliente_id) REFERENCES usuario (id);