package br.com.senai.s042.autoescolas042.domain.alunos;

import br.com.senai.s042.autoescolas042.domain.endereco.DadosEndereco;

public record DadosAtualizacaoAluno(
        String id,
        String nome,
        String telefone,
        DadosEndereco endereco
) {
}
