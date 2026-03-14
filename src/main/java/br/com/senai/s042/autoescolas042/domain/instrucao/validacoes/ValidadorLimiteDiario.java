package br.com.senai.s042.autoescolas042.domain.instrucao.validacoes;

import br.com.senai.s042.autoescolas042.domain.instrucao.DadosAgendamentoInstrucao;
import br.com.senai.s042.autoescolas042.domain.instrucao.InstrucaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidadorLimiteDiario implements ValidadorAgendamento {
    @Autowired
    private InstrucaoRepository repository;

    @Override
    public void validar(DadosAgendamentoInstrucao dados) {
        LocalDateTime inicioExpediente = dados.data().withHour(6);
        LocalDateTime fimExpediente = dados.data().withHour(21 - 1);

        Boolean reincidenciaDiaria = repository.existsByAlunoIdAndDataBetween(
                dados.idAluno(),
                inicioExpediente,
                fimExpediente);

        if(reincidenciaDiaria) {
            throw  new ValidacaoException("Permitido o agendamento de apenas uma instrução diária por aluno!");
        }
    }
}