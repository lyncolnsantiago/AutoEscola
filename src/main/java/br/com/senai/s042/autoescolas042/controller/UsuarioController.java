package br.com.senai.s042.autoescolas042.controller;

import br.com.senai.s042.autoescolas042.domain.usuario.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuariosRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<Page<DadosListagemUsuarios>> listarUsuarios(
            @PageableDefault(size = 10, sort = {"login"}) Pageable pageable){

        Page page = usuariosRepository.findAllByAtivoTrue(pageable).map(DadosListagemUsuarios::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoUsuario> detalharUsuario(@PathVariable Long id){
        Usuario usuario = usuariosRepository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoUsuario> cadastrarUsuarios(
            @RequestBody
            @Valid
            DadosCadastroUsuario dadosUsuario,
            UriComponentsBuilder uriBuilder) {

        Usuario usuario = new Usuario(dadosUsuario);
        usuario.setSenha(passwordEncoder.encode(dadosUsuario.senha()));
        usuariosRepository.save(usuario);

        URI uri = uriBuilder.path("/usuarios/{id}")
                .buildAndExpand(usuario.getId()).toUri();

        return  ResponseEntity.created(uri)
                .body(new DadosDetalhamentoUsuario(usuario));
    }

    @PutMapping
    @Transactional
    public void atualizarUsuarios(@RequestBody DadosAtualizacaoUsuario dadosAtualizacaoUsuario){
        Usuario usuario = usuariosRepository.getReferenceById(Long.valueOf(dadosAtualizacaoUsuario.id()));

        usuario.atualizarInformacoes(dadosAtualizacaoUsuario);
        usuariosRepository.save(usuario);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluirUsuario(@PathVariable Long id){

        Usuario usuario = usuariosRepository.getReferenceById(id);
        usuario.excluir();

        usuariosRepository.save(usuario);
        return ResponseEntity.noContent().build();
    }
}
