ALTER TABLE pedido
ADD COLUMN restaurante_id BIGINT NOT NULL;

ALTER TABLE pedido
ADD CONSTRAINT fk_pedido_restaurante
FOREIGN KEY (restaurante_id)
REFERENCES restaurante (id);