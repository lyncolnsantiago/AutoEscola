package br.com.senai.s042.autoescolas042.controller;

import br.com.senai.s042.autoescolas042.domain.usuario.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoUsuario> cadastrarUsuario(
            @RequestBody @Valid DadosCadastroUsuario dados,
            UriComponentsBuilder uriBuilder) {
        String senhaCriptografada = encoder.encode(dados.senha());
        Usuario usuario = new Usuario(
                null,
                dados.login(),
                senhaCriptografada,
                true
        );
        repository.save(usuario);
        URI uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemUsuario>> listarUsuarios(
            Pageable paginacao) {
        Page page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemUsuario::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoUsuario> detalharUsuario(
            @PathVariable Long id) {
        Usuario usuario = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoUsuario> atualizarUsuario(
            @RequestBody @Valid DadosAtualizacaoUsuario dados) {
        Usuario usuario = repository.getReferenceById(dados.id());
        usuario.atualizarInformacoes(dados);
        repository.save(usuario);
        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }

    @PatchMapping
    @Transactional
    public ResponseEntity<DadosSuccess> atualizarSenha(
            @RequestBody DadosAtualizacaoSenha dados) {
        Usuario usuario = repository.getReferenceById(dados.id());
        usuario.atualizarSenha(dados.senha());
        return ResponseEntity.ok(new DadosSuccess("Senha atualizada com sucesso!"));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluirUsuario(@PathVariable Long id) {
        Usuario usuario = repository.getReferenceById(id);
        usuario.excluirUsuario(id);
        repository.save(usuario);
        return ResponseEntity.noContent().build();
    }
}