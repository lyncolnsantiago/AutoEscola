package br.com.senai.s042.autoescolas042.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroUsuario(

        @NotBlank
        String login,

        @NotBlank
        String senha,

        @NotNull
        Role perfil) {
}