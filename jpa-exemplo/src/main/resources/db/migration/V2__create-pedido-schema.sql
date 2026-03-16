CREATE TABLE pedido (
    id BIGINT NOT NULL AUTO_INCREMENT,

    subtotal DECIMAL(10,2),
    taxa_frete DECIMAL(10,2),
    valor_total DECIMAL(10,2),

    data_criacao DATETIME NOT NULL,
    data_confirmacao DATETIME,
    data_cancelamento DATETIME,
    data_entrega DATETIME,

    PRIMARY KEY (id)
) ENGINE=InnoDB;