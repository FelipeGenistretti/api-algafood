ALTER TABLE pedido
ADD COLUMN forma_pagamento_id BIGINT NOT NULL;

ALTER TABLE pedido
ADD CONSTRAINT fk_pedido_forma_pagamento
FOREIGN KEY (forma_pagamento_id)
REFERENCES forma_de_pagamento (id);