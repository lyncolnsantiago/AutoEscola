package br.com.senai.s042.autoescolas042.domain.usuario;

public record DadosDetalhamentoUsuario(
        Long id,
        String login,
        Boolean ativo,
        Role perfil) {
    public DadosDetalhamentoUsuario(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getAtivo(),
                usuario.getPerfil());
    }
}
