CREATE TABLE item_pedido (
    id BIGINT NOT NULL AUTO_INCREMENT,

    pedido_id BIGINT NOT NULL,
    quantidade INT NOT NULL,

    preco_unitario DECIMAL(10,2) NOT NULL,
    preco_total DECIMAL(10,2) NOT NULL,

    observacao VARCHAR(80),

    PRIMARY KEY (id),

    CONSTRAINT fk_item_pedido_pedido
        FOREIGN KEY (pedido_id)
        REFERENCES pedido (id)
        ON DELETE CASCADE
) ENGINE=InnoDB;
