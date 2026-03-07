package br.com.senai.s042.autoescolas042.domain.usuario;

import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "Usuario")
@Table(name = "usuarios")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    @Setter
    private String senha;
    private Boolean ativo;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public Usuario(DadosCadastroUsuario dadosUsuario) {
        this.login = dadosUsuario.login();
        this.senha = dadosUsuario.senha();
        this.ativo = true;
    }

    @Override
    public @Nullable String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void atualizarInformacoes(DadosAtualizacaoUsuario dadosAtualizacaoUsuario) {

        if(dadosAtualizacaoUsuario.login() != null && !dadosAtualizacaoUsuario.login().isBlank()){
            this.login = dadosAtualizacaoUsuario.login();
        }

        if(dadosAtualizacaoUsuario.senha() != null && !dadosAtualizacaoUsuario.senha().isBlank()){
            this.senha = dadosAtualizacaoUsuario.senha();
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}