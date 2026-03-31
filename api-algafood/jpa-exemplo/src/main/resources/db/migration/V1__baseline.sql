-- =========================
-- TABELAS BASE (SEM FK)
-- =========================

CREATE TABLE estado (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE cidade (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255),
    estado_id BIGINT NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE cozinha (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE forma_de_pagamento (
    id BIGINT NOT NULL AUTO_INCREMENT,
    descricao VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE permissao (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255),
    descricao VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE grupo (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE usuario (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255),
    email VARCHAR(255),
    senha VARCHAR(255),
    data_cadastro DATETIME NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE restaurante (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255),
    taxa_frete DECIMAL(38,2),
    data_cadastro DATETIME NOT NULL,
    data_atualizacao DATETIME NOT NULL,
    cidade_id BIGINT,
    cozinha_id BIGINT NOT NULL,
    endereco_cidade_id BIGINT,
    endereco_logradouro VARCHAR(255),
    endereco_numero VARCHAR(255),
    endereco_bairro VARCHAR(255),
    endereco_cep VARCHAR(255),
    endereco_name VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE produto (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255),
    descricao VARCHAR(255),
    preco DECIMAL(38,2),
    ativo BIT NOT NULL,
    restaurante_id BIGINT,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

-- =========================
-- TABELAS DE JUNÇÃO
-- =========================

CREATE TABLE grupo_permissao (
    grupo_id BIGINT NOT NULL,
    permissao_id BIGINT NOT NULL
) ENGINE=InnoDB;

CREATE TABLE restaurante_forma_pagamento (
    restaurante_id BIGINT NOT NULL,
    forma_pagamento_id BIGINT NOT NULL
) ENGINE=InnoDB;

-- 🔥 CORRIGIDA AQUI
CREATE TABLE restaurante_grupos (
    usuario_id BIGINT NOT NULL,
    grupo_id BIGINT NOT NULL
) ENGINE=InnoDB;

-- =========================
-- FOREIGN KEYS
-- =========================

ALTER TABLE cidade
    ADD CONSTRAINT fk_cidade_estado
    FOREIGN KEY (estado_id) REFERENCES estado (id);

ALTER TABLE restaurante
    ADD CONSTRAINT fk_restaurante_cidade
    FOREIGN KEY (cidade_id) REFERENCES cidade (id);

ALTER TABLE restaurante
    ADD CONSTRAINT fk_restaurante_cozinha
    FOREIGN KEY (cozinha_id) REFERENCES cozinha (id);

ALTER TABLE restaurante
    ADD CONSTRAINT fk_restaurante_endereco_cidade
    FOREIGN KEY (endereco_cidade_id) REFERENCES cidade (id);

ALTER TABLE produto
    ADD CONSTRAINT fk_produto_restaurante
    FOREIGN KEY (restaurante_id) REFERENCES restaurante (id);

ALTER TABLE grupo_permissao
    ADD CONSTRAINT fk_grupo_permissao_grupo
    FOREIGN KEY (grupo_id) REFERENCES grupo (id);

ALTER TABLE grupo_permissao
    ADD CONSTRAINT fk_grupo_permissao_permissao
    FOREIGN KEY (permissao_id) REFERENCES permissao (id);

ALTER TABLE restaurante_forma_pagamento
    ADD CONSTRAINT fk_restaurante_fp_restaurante
    FOREIGN KEY (restaurante_id) REFERENCES restaurante (id);

ALTER TABLE restaurante_forma_pagamento
    ADD CONSTRAINT fk_restaurante_fp_forma_pagamento
    FOREIGN KEY (forma_pagamento_id) REFERENCES forma_de_pagamento (id);

-- 🔥 CORRIGIDAS AQUI
ALTER TABLE restaurante_grupos
    ADD CONSTRAINT fk_restaurante_grupos_usuario
    FOREIGN KEY (usuario_id) REFERENCES usuario (id);

ALTER TABLE restaurante_grupos
    ADD CONSTRAINT fk_restaurante_grupos_grupo
    FOREIGN KEY (grupo_id) REFERENCES grupo (id);
