package br.com.senai.s042.autoescolas042.domain.instrucao.validacoes;

import br.com.senai.s042.autoescolas042.domain.instrucao.DadosAgendamentoInstrucao;
import br.com.senai.s042.autoescolas042.domain.instrucao.InstrucaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorDisponibilidadeInstrutor implements ValidadorAgendamento {
    @Autowired
    private InstrucaoRepository repository;

    @Override
    public void validar(DadosAgendamentoInstrucao dados) {
        Boolean instrutorOcupado = repository.existsByInstrutorIdAndData(
                dados.idInstrutor(),
                dados.data()
        );

        if(instrutorOcupado) {
            throw new ValidacaoException("Instrutor ocupado na data e horário solicitados!");
        }
    }
}