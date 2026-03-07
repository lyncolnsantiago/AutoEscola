package br.com.senai.s042.autoescolas042.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroUsuario(

        @NotBlank
        String login,

        @NotBlank
        String senha) {
}