package br.com.senai.s042.autoescolas042.domain.instrucao.validacoes;

import br.com.senai.s042.autoescolas042.domain.instrucao.DadosAgendamentoInstrucao;
import br.com.senai.s042.autoescolas042.domain.instrutor.InstrutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorInstrutorAtivo implements ValidadorAgendamento {
    @Autowired
    private InstrutorRepository repository;

    @Override
    public void validar(DadosAgendamentoInstrucao dados) {
        Boolean instrutorAtivo = repository.findAtivoById(dados.idInstrutor());

        if(!instrutorAtivo) {
            throw new ValidacaoException("Instrução não pode ser agendada com instrutor inativo!");
        }
    }
}