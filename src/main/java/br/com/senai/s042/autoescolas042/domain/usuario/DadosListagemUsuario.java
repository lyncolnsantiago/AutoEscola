package br.com.senai.s042.autoescolas042.domain.usuario;

public record DadosListagemUsuario(
        Long id,
        String login) {
    public DadosListagemUsuario(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getLogin()
        );
    }
}