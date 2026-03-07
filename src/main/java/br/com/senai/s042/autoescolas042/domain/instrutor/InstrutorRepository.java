package br.com.senai.s042.autoescolas042.domain.instrutor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface InstrutorRepository extends JpaRepository<Instrutor, Long> {
    Page<Instrutor> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
                SELECT i FROM Instrutor i
                WHERE
                i.ativo = TRUE
                AND
                i.especialidade = :especialidade
                AND
                i.id NOT IN(
                    select a.instrutor.id FROM Instrucao a
                    WHERE
                    a.data = :data
                )
                ORDER BY rand()
                LIMIT 1
""")
    Instrutor escolherInstrutorAleatorioDisponivel(Especialidade especialidade, LocalDateTime data);
}