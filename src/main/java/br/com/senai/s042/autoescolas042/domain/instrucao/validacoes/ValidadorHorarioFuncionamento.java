package br.com.senai.s042.autoescolas042.domain.instrucao.validacoes;

import br.com.senai.s042.autoescolas042.domain.instrucao.DadosAgendamentoInstrucao;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioFuncionamento implements ValidadorAgendamento{
    @Override
    public void validar(DadosAgendamentoInstrucao dados) {
        LocalDateTime dataAgendamento = dados.data();

        Boolean ehDomingo = dataAgendamento.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        Boolean preAbertura = dataAgendamento.getHour() < 6;
        Boolean posFechamento = dataAgendamento.getHour() > 21 - 1;

        if(ehDomingo || preAbertura || posFechamento) {
            throw new ValidacaoException("Tentativa de agendamento fora do horário de funcionamento!");
        }
    }
}