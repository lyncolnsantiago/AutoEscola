package br.com.senai.s042.autoescolas042.adapter.out.repository;

import br.com.senai.s042.autoescolas042.adapter.out.repository.entity.InstrucaoEntity;
import br.com.senai.s042.autoescolas042.adapter.out.repository.mapper.InstrucaoEntityMapper;
import br.com.senai.s042.autoescolas042.adapter.out.repository.persistence.JpaInstrucaoRepository;
import br.com.senai.s042.autoescolas042.application.core.domain.model.Instrucao;
import br.com.senai.s042.autoescolas042.application.port.out.InstrucaoRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class InstrucaoRepositoryImpl implements InstrucaoRepository {
    private final JpaInstrucaoRepository repository;
    private final InstrucaoEntityMapper mapper;

    public InstrucaoRepositoryImpl(
            JpaInstrucaoRepository repository,
            InstrucaoEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Boolean existsByAlunoIdAndDataBetween(Long id, LocalDateTime inicioExpediente, LocalDateTime fimExpediente) {
        return repository.existsByAlunoIdAndDataBetween(id, inicioExpediente, fimExpediente);
    }

    @Override
    public Boolean existsByInstrutorIdAndData(Long id, LocalDateTime data) {
        return repository.existsByInstrutorIdAndData(id, data);
    }

    @Override
    public Instrucao save(Instrucao instrucao) {
        InstrucaoEntity entity = mapper.toEntity(instrucao);
        InstrucaoEntity salvo = repository.save(entity);
        return mapper.toDomain(salvo);
    }
}