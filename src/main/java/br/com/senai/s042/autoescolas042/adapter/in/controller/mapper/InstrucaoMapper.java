package br.com.senai.s042.autoescolas042.adapter.in.controller.mapper;

import br.com.senai.s042.autoescolas042.adapter.in.controller.response.instrucao.DadosDetalhamentoInstrucao;
import br.com.senai.s042.autoescolas042.application.core.domain.model.Instrucao;
import org.springframework.stereotype.Component;

@Component
public class InstrucaoMapper {
    public DadosDetalhamentoInstrucao toDetailsDTO(Instrucao instrucao) {
        return new DadosDetalhamentoInstrucao(
                instrucao.getId(),
                instrucao.getAluno().getNome(),
                instrucao.getInstrutor().getNome(),
                instrucao.getInstrutor().getEspecialidade(),
                instrucao.getData()
        );
    }
}