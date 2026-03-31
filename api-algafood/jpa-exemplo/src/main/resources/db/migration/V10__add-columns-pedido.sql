ALTER TABLE pedido
ADD COLUMN endereco_cep VARCHAR(20),
ADD COLUMN endereco_logradouro VARCHAR(100),
ADD COLUMN endereco_numero VARCHAR(20),
ADD COLUMN endereco_complemento VARCHAR(60),
ADD COLUMN endereco_bairro VARCHAR(60),
ADD COLUMN endereco_cidade_id BIGINT;

ALTER TABLE pedido
ADD CONSTRAINT fk_pedido_endereco_cidade
FOREIGN KEY (endereco_cidade_id) REFERENCES cidade (id);