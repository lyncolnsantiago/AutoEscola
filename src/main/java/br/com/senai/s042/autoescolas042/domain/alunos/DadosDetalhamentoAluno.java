package br.com.senai.s042.autoescolas042.domain.alunos;

import br.com.senai.s042.autoescolas042.domain.instrutor.Especialidade;

public record DadosDetalhamentoAluno(
        Long id,
        String nome,
        String email,
        String cpf,
        Boolean ativo,
        Especialidade especialidade) {

    public DadosDetalhamentoAluno(Aluno aluno) {

        this(   aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getCpf(),
                aluno.getAtivo(),
                aluno.getEspecialidade()
        );
    }
}
