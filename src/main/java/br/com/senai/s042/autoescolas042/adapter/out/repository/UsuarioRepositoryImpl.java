package br.com.senai.s042.autoescolas042.adapter.out.repository;

import br.com.senai.s042.autoescolas042.adapter.out.repository.entity.UsuarioEntity;
import br.com.senai.s042.autoescolas042.adapter.out.repository.mapper.UsuarioEntityMapper;
import br.com.senai.s042.autoescolas042.adapter.out.repository.persistence.JpaUsuarioRepository;
import br.com.senai.s042.autoescolas042.application.core.domain.model.Usuario;
import br.com.senai.s042.autoescolas042.application.port.out.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UsuarioRepositoryImpl implements UsuarioRepository {
    private JpaUsuarioRepository repository;
    private UsuarioEntityMapper mapper;

    public UsuarioRepositoryImpl(
            JpaUsuarioRepository repository,
            UsuarioEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public UserDetails findByLogin(String login) {
        UsuarioEntity entity = repository.findByLogin(login);
        return mapper.toDomain(entity);
    }

    @Override
    public Page<Usuario> findAllByAtivoTrue(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(mapper::toDomain);
    }

    @Override
    public Usuario save(Usuario usuario) {
        UsuarioEntity entity = mapper.toEntity(usuario);
        UsuarioEntity salvo = repository.save(entity);
        return mapper.toDomain(salvo);
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }
}