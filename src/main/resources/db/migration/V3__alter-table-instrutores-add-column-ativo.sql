ALTER TABLE instrutores ADD ativo TINYINT NOT NULL;
UPDATE instrutores SET ativo = 1;