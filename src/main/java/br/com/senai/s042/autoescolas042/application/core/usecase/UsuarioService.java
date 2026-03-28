package br.com.senai.s042.autoescolas042.application.core.usecase;

import br.com.senai.s042.autoescolas042.adapter.in.controller.request.usuario.DadosAtualizacaoSenha;
import br.com.senai.s042.autoescolas042.adapter.in.controller.request.usuario.DadosAtualizacaoUsuario;
import br.com.senai.s042.autoescolas042.adapter.in.controller.request.usuario.DadosCadastroUsuario;
import br.com.senai.s042.autoescolas042.adapter.in.controller.response.usuario.DadosDetalhamentoUsuario;
import br.com.senai.s042.autoescolas042.adapter.in.controller.response.usuario.DadosListagemUsuario;
import br.com.senai.s042.autoescolas042.application.core.domain.model.Usuario;
import br.com.senai.s042.autoescolas042.application.port.out.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final PasswordEncoder encoder;
    private final UsuarioRepository repository;

    public UsuarioService(
            PasswordEncoder encoder,
            UsuarioRepository repository) {
        this.encoder = encoder;
        this.repository = repository;
    }

    @Transactional
    public DadosDetalhamentoUsuario cadastrar(DadosCadastroUsuario dados) {
        String senhaCriptografada = encoder.encode(dados.senha());
        Usuario usuario = new Usuario(
                null,
                dados.login(),
                senhaCriptografada,
                true,
                dados.perfil()
        );
        repository.save(usuario);
        return new DadosDetalhamentoUsuario(usuario);
    }

    public Page<DadosListagemUsuario> listar(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao)
                .map(DadosListagemUsuario::new);
    }

    public DadosDetalhamentoUsuario detalhar(Long id) {
        Usuario usuario = repository.getReferenceById(id);
        return new DadosDetalhamentoUsuario(usuario);
    }

    @Transactional
    public DadosDetalhamentoUsuario atualizar(DadosAtualizacaoUsuario dados) {
        Usuario usuario = repository.getReferenceById(dados.id());
        usuario.atualizarInformacoes(dados);
        repository.save(usuario);
        return new DadosDetalhamentoUsuario(usuario);
    }

    @Transactional
    public void atualizarSenha(DadosAtualizacaoSenha dados) {
        String senhaCriptografada = encoder.encode(dados.senha());
        Usuario usuario = repository.getReferenceById(dados.id());
        usuario.atualizarSenha(senhaCriptografada);
        repository.save(usuario);
    }

    @Transactional
    public void excluir(Long id) {
        Usuario usuario = repository.getReferenceById(id);
        usuario.excluir(id);
        repository.save(usuario);
    }
}