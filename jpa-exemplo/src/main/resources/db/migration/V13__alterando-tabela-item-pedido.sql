ALTER TABLE item_pedido
ADD COLUMN produto_id BIGINT;

ALTER TABLE item_pedido
ADD CONSTRAINT fk_item_pedido_produto
FOREIGN KEY (produto_id)
REFERENCES produto (id);