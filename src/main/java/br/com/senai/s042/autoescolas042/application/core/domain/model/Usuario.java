package br.com.senai.s042.autoescolas042.application.core.domain.model;

import br.com.senai.s042.autoescolas042.application.core.domain.enums.Role;
import jakarta.persistence.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class Usuario implements UserDetails {
    private Long id;
    private String login;
    private String senha;
    private Boolean ativo = true;
    private Role perfil;

    public Usuario() {}

    public Usuario(
            Long id,
            String login,
            String senha,
            Boolean ativo,
            Role perfil) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.ativo = ativo;
        this.perfil = perfil;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public Role getPerfil() {
        return perfil;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + perfil.name()));
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

    public void atualizarInformacoes(String login, Boolean ativo, Role perfil) {
        if(login != null) {
            this.login = login;
        }
        if(ativo != null) {
            this.ativo = ativo;
        }
        if(perfil != null) {
            this.perfil = perfil;
        }
    }

    public void excluir(Long id) {
        this.ativo = false;
    }

    public void atualizarSenha(String senha) {
        this.senha = senha;
    }
}