package br.com.senai.s042.autoescolas042.controller;

import br.com.senai.s042.autoescolas042.domain.alunos.*;
import br.com.senai.s042.autoescolas042.domain.instrutor.*;
import jakarta.transaction.Transactional;
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
@RequestMapping("/alunos")
public class AlunoController {
    private final AlunoService service;

    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<DadosDetalhamentoAluno> cadastrarAluno(
            @RequestBody @Valid DadosCadastroAluno dados,
            UriComponentsBuilder uriBuilder) {
        DadosDetalhamentoAluno dto = service.cadastrar(dados);
        URI uri = uriBuilder.path("alunos/{id}")
                .buildAndExpand(dto.id()).toUri();
        return ResponseEntity.created(uri)
                .body(dto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Page<DadosListagemAluno>> listarAlunos(
            @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return ResponseEntity.ok(service.listar(paginacao));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<DadosDetalhamentoAluno> detalharAluno(
            @PathVariable Long id) {
        Aluno aluno = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoAluno(aluno));
    }

    @PutMapping
    @Transactional
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<DadosDetalhamentoAluno> atualizarAluno(
            @RequestBody DadosAtualizacaoAluno dados) {
        Aluno aluno = repository.getReferenceById(dados.id());
        aluno.atualizarInformacoes(dados);
        repository.save(aluno);
        return ResponseEntity.ok(new DadosDetalhamentoAluno(aluno));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Void> excluirAluno(@PathVariable Long id) {
        Aluno aluno = repository.getReferenceById(id);
        aluno.excluir();
        repository.save(aluno);
        return ResponseEntity.noContent().build();
    }
}