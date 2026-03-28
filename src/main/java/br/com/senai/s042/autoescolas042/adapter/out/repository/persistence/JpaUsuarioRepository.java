package br.com.senai.s042.autoescolas042.adapter.out.repository.persistence;

import br.com.senai.s042.autoescolas042.adapter.out.repository.entity.UsuarioEntity;
import br.com.senai.s042.autoescolas042.application.core.domain.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    UsuarioEntity findByLogin(String login);
    Page<UsuarioEntity> findAllByAtivoTrue(Pageable paginacao);
}