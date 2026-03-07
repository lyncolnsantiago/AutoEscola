package br.com.senai.s042.autoescolas042.domain.instrucao;

import br.com.senai.s042.autoescolas042.domain.instrutor.Especialidade;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamentoInstrucao(

        @NotNull
        Long idAluno,
        Long idInstrutor,

        @NotNull
        @Future
        @JsonFormat(pattern = "dd/MM/yyyy - HH:mm")
        LocalDateTime data,

        Especialidade especialidade) {
}
