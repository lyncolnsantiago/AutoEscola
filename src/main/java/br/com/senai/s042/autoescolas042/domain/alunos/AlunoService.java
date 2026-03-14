package br.com.senai.s042.autoescolas042.domain.alunos;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public DadosDetalhamentoAluno cadastrar(@Valid DadosCadastroAluno dados) {
        Aluno aluno = new Aluno();
        repository.save(aluno);
        return new  DadosDetalhamentoAluno(aluno);
    }

    public Page<DadosListagemAluno> listar(Pageable paginacao) {

    }
}
