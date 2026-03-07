package br.com.senai.s042.autoescolas042.domain.alunos;

public record DadosListagemAluno(
        Long id,
        String nome,
        String email,
        String cpf) {

    public DadosListagemAluno(Aluno aluno) {

        this(
                aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getCpf()
        );
    }
}
