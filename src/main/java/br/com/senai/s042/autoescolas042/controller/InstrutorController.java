package br.com.senai.s042.autoescolas042.controller;

import br.com.senai.s042.autoescolas042.domain.instrutor.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;

@RestController
@RequestMapping("/instrutores")
public class InstrutorController {
    private final InstrutorService service;

    public InstrutorController(InstrutorService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<DadosDetalhamentoInstrutor> cadastrarInstrutor(
            @RequestBody @Valid DadosCadastroInstrutor dados,
            UriComponentsBuilder uriBuilder) {
        DadosDetalhamentoInstrutor dto = service.cadastrar(dados);
        URI uri = uriBuilder.path("/intrutores/{id}")
                .buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri)
                .body(dto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Page<DadosListagemInstrutor>> listarInstrutores(
            @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return ResponseEntity.ok(service.listar(paginacao));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<DadosDetalhamentoInstrutor> detalharInstrutor(
            @PathVariable Long id) {
        DadosDetalhamentoInstrutor dto = service.detalhar(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<DadosDetalhamentoInstrutor> atualizarInstrutor(
            @RequestBody DadosAtualizacaoInstrutor dados) {
        DadosDetalhamentoInstrutor dto = service.atualizar(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}") //Padrão de mercado
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Void> excluirInstrutor(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    /*@DeleteMapping("/{id}") //Útil para algumas estratégias de front-end.
    @Transactional
    public ResponseEntity<DadosDetalhamentoInstrutor> excluirInstrutor(@PathVariable Long id) {
        Instrutor instrutor = repository.getReferenceById(id);
        instrutor.excluir();
        repository.save(instrutor);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new DadosDetalhamentoInstrutor(instrutor));
    }*/
}