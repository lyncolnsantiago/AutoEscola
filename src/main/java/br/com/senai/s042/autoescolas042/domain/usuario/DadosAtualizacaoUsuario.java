package br.com.senai.s042.autoescolas042.domain.usuario;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoUsuario(

        @NotNull
        Long id,
        String login,
        Boolean ativo,
        Role perfil) {
}