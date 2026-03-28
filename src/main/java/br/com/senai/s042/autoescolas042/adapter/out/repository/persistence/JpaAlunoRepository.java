package br.com.senai.s042.autoescolas042.adapter.out.repository.persistence;

import br.com.senai.s042.autoescolas042.adapter.out.repository.entity.AlunoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAlunoRepository extends JpaRepository<AlunoEntity, Long> {
    Page<AlunoEntity> findAllByAtivoTrue(Pageable paginacao);

    @Query("""
        SELECT e.ativo
        FROM Aluno e
        WHERE
        e.id = :id
    """)
    Boolean findByIdAndAtivoTrue(Long id);
}