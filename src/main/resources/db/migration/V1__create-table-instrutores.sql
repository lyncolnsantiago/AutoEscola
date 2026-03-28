CREATE TABLE instrutores(
    id BIGINT NOT NULL auto_increment,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    cnh VARCHAR(11) NOT NULL UNIQUE,
    especialidade VARCHAR(20) NOT NULL,
    logradouro VARCHAR(100)  NOT NULL,
    numero VARCHAR(10),
    complemento VARCHAR(100),
    bairro VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    uf VARCHAR(2) NOT NULL,
    cep VARCHAR(8) NOT NULL,

    PRIMARY KEY(id)
);