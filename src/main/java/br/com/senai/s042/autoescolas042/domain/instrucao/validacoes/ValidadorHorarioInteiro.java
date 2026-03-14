package br.com.senai.s042.autoescolas042.domain.instrucao.validacoes;

import br.com.senai.s042.autoescolas042.domain.instrucao.DadosAgendamentoInstrucao;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidadorHorarioInteiro implements ValidadorAgendamento{
    @Override
    public void validar(DadosAgendamentoInstrucao dados) {
        LocalDateTime dataAgendamento = dados.data();

        if(dataAgendamento.getMinute() != 0 || dataAgendamento.getSecond() != 0) {
            throw new ValidacaoException("O horário deve ser preenchido em horas inteiras (ex: 08:00, 13:00)");
        }
    }
}