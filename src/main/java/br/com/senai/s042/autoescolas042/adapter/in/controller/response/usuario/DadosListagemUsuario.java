package br.com.senai.s042.autoescolas042.adapter.in.controller.response.usuario;

import br.com.senai.s042.autoescolas042.application.core.domain.model.Usuario;

public record DadosListagemUsuario(
        Long id,
        String login) {
    public DadosListagemUsuario(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getLogin()
        );
    }
}