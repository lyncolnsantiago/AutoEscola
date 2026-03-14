package br.com.senai.s042.autoescolas042.controller;

import br.com.senai.s042.autoescolas042.domain.instrucao.AgendaDeInstrucoes;
import br.com.senai.s042.autoescolas042.domain.instrucao.DadosAgendamentoInstrucao;
import br.com.senai.s042.autoescolas042.domain.instrucao.DadosDetalhamentoInstrucao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/instrucoes")
public class InstrucaoController {

    @Autowired
    private AgendaDeInstrucoes agenda;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<DadosDetalhamentoInstrucao> agendarInstrucao(
            @RequestBody @Valid DadosAgendamentoInstrucao dados) {
        DadosDetalhamentoInstrucao dto = agenda.agendarInstrucao(dados);
        return ResponseEntity.ok(dto);
    }
}