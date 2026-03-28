package br.com.senai.s042.autoescolas042.adapter.out.repository;

import br.com.senai.s042.autoescolas042.adapter.out.repository.entity.AlunoEntity;
import br.com.senai.s042.autoescolas042.adapter.out.repository.mapper.AlunoEntityMapper;
import br.com.senai.s042.autoescolas042.adapter.out.repository.persistence.JpaAlunoRepository;
import br.com.senai.s042.autoescolas042.application.core.domain.model.Aluno;
import br.com.senai.s042.autoescolas042.application.port.out.AlunoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AlunoRepositoryImpl implements AlunoRepository {
    private final JpaAlunoRepository repository;
    private final AlunoEntityMapper mapper;

    public AlunoRepositoryImpl(
            JpaAlunoRepository repository,
            AlunoEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    @Override
    public Page<Aluno> findAllByAtivoTrue(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(mapper::toDomain);
    }

    @Override
    public Boolean findByIdAndAtivoTrue(Long id) {
        return repository.findByIdAndAtivoTrue(id);
    }

    @Override
    public Aluno save(Aluno aluno) {
        AlunoEntity entity = mapper.toEntity(aluno);
        AlunoEntity salvo = repository.save(entity);
        return mapper.toDomain(salvo);
    }

    @Override
    public Optional<Aluno> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Aluno getReferenceById(Long id) {
        AlunoEntity entity = repository.getReferenceById(id);
        return mapper.toDomain(entity);
    }
}