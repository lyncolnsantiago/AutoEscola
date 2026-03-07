package br.com.senai.s042.autoescolas042.domain.alunos;

import br.com.senai.s042.autoescolas042.domain.endereco.DadosEndereco;
import br.com.senai.s042.autoescolas042.domain.instrutor.Especialidade;

public record DadosCadastroAluno(
        String nome,
        String email,
        String telefone,
        String cpf,
        Especialidade especialidade,
        DadosEndereco endereco
) {
}
