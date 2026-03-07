package br.com.senai.s042.autoescolas042.domain.instrucao;

import br.com.senai.s042.autoescolas042.domain.alunos.Aluno;
import br.com.senai.s042.autoescolas042.domain.instrutor.Especialidade;
import br.com.senai.s042.autoescolas042.domain.instrutor.Instrutor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Instrucao")
@Table(name = "instrucoes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Instrucao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id")
    Aluno aluno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instrutor_id")
    Instrutor instrutor;

    @Enumerated
    private Especialidade especialidade;
    private LocalDateTime data;
}
