package br.com.senai.s042.autoescolas042.domain.instrucao.validacoes;

import br.com.senai.s042.autoescolas042.domain.instrucao.DadosAgendamentoInstrucao;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamento {
    @Override
    public void validar(DadosAgendamentoInstrucao dados) {
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime horaAgendamento = dados.data();

        Long antecedencia = Duration.between(agora, horaAgendamento).toMinutes();

        if(antecedencia < 30) {
            throw new ValidacaoException("Instrução deve ser agendada com antecedência mínima de 30 minutos!");
        }
    }
}