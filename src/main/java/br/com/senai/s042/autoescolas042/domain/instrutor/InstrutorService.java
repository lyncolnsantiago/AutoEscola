package br.com.senai.s042.autoescolas042.domain.instrutor;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InstrutorService {

    private final InstrutorRepository repository;

    public InstrutorService(InstrutorRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public DadosDetalhamentoInstrutor cadastrar(@Valid DadosCadastroInstrutor dados) {
        Instrutor instrutor = new Instrutor();
        repository.save(instrutor);
        return new DadosDetalhamentoInstrutor(instrutor);
    }

    public Page<DadosListagemInstrutor> listar(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao)
                .map(DadosListagemInstrutor::new);
    }

    public DadosDetalhamentoInstrutor detalhar(Long id) {
        Instrutor instrutor = repository.getReferenceById(id);
        return new DadosDetalhamentoInstrutor(instrutor);
    }

    @Transactional
    public DadosDetalhamentoInstrutor atualizar(DadosAtualizacaoInstrutor dados) {
        Instrutor instrutor = repository.getReferenceById(dados.id());
        instrutor.atualizarInformacoes(dados);
        repository.save(instrutor);
        return new DadosDetalhamentoInstrutor(instrutor);
    }

    @Transactional
    public void excluir(Long id) {
        Instrutor instrutor = repository.getReferenceById(id);
        instrutor.excluir();
        repository.save(instrutor);
    }
}
