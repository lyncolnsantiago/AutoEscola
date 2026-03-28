package br.com.senai.s042.autoescolas042.adapter.out.repository.mapper;

import br.com.senai.s042.autoescolas042.adapter.out.repository.entity.UsuarioEntity;
import br.com.senai.s042.autoescolas042.application.core.domain.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioEntityMapper {
    public UsuarioEntity toEntity(Usuario usuario) {
        return new UsuarioEntity(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getSenha(),
                usuario.getAtivo(),
                usuario.getPerfil()
        );
    }

    public Usuario toDomain(UsuarioEntity entity) {
        return new Usuario(
                entity.getId(),
                entity.getLogin(),
                entity.getSenha(),
                entity.getAtivo(),
                entity.getPerfil()
        );
    }
}