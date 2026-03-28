package br.com.senai.s042.autoescolas042.adapter.out.repository.mapper;

import br.com.senai.s042.autoescolas042.adapter.out.repository.entity.AlunoEntity;
import br.com.senai.s042.autoescolas042.application.core.domain.model.Aluno;
import org.springframework.stereotype.Component;

@Component
public class AlunoEntityMapper {
    public AlunoEntity toEntity(Aluno aluno) {
        return new AlunoEntity(
                aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getTelefone(),
                aluno.getCpf(),
                aluno.getEndereco(),
                aluno.getAtivo()
        );
    }

    public Aluno toDomain(AlunoEntity entity) {
        return new Aluno(
                entity.getId(),
                entity.getNome(),
                entity.getEmail(),
                entity.getTelefone(),
                entity.getCpf(),
                entity.getEndereco(),
                entity.getAtivo()
        );
    }
}