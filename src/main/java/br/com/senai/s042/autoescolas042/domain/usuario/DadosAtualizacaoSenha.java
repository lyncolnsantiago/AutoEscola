package br.com.senai.s042.autoescolas042.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoSenha(

        @NotNull
        Long id,

        @NotBlank
        String senha) {
}