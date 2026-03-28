package br.com.senai.s042.autoescolas042.application.core.usecase;

import br.com.senai.s042.autoescolas042.adapter.in.controller.request.aluno.DadosAtualizacaoAluno;
import br.com.senai.s042.autoescolas042.adapter.in.controller.request.aluno.DadosCadastroAluno;
import br.com.senai.s042.autoescolas042.adapter.in.controller.response.aluno.DadosDetalhamentoAluno;
import br.com.senai.s042.autoescolas042.adapter.in.controller.response.aluno.DadosListagemAluno;
import br.com.senai.s042.autoescolas042.application.core.domain.model.Aluno;
import br.com.senai.s042.autoescolas042.application.port.out.AlunoRepository;
import jakarta.transaction.Transactional;
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
    public DadosDetalhamentoAluno cadastrar(DadosCadastroAluno dados) {
        Aluno aluno = new Aluno(dados);
        repository.save(aluno);
        return new DadosDetalhamentoAluno(aluno);
    }

    public Page<DadosListagemAluno> listar(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao)
                .map(DadosListagemAluno::new);
    }

    public DadosDetalhamentoAluno detalhar(Long id) {
        Aluno aluno = repository.getReferenceById(id);
        return new DadosDetalhamentoAluno(aluno);
    }

    @Transactional
    public DadosDetalhamentoAluno atualizar(DadosAtualizacaoAluno dados) {
        Aluno aluno = repository.getReferenceById(dados.id());
        aluno.atualizarInformacoes(dados);
        repository.save(aluno);
        return new DadosDetalhamentoAluno(aluno);
    }

    @Transactional
    public void excluir(Long id) {
        Aluno aluno = repository.getReferenceById(id);
        aluno.excluir();
        repository.save(aluno);
    }
}