package br.com.senai.s042.autoescolas042.adapter.out.repository.persistence;

import br.com.senai.s042.autoescolas042.adapter.out.repository.entity.InstrucaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface JpaInstrucaoRepository extends JpaRepository<InstrucaoEntity, Long> {
    Boolean existsByAlunoIdAndDataBetween(Long id, LocalDateTime inicioExpediente, LocalDateTime fimExpediente);
    Boolean existsByInstrutorIdAndData(Long id, LocalDateTime data);
}
