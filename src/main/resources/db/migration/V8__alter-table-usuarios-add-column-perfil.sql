ALTER TABLE usuarios ADD perfil VARCHAR(20) NOT NULL;
UPDATE usuarios SET perfil = "USER";