package br.com.senai.s042.autoescolas042.domain.instrucao;

import br.com.senai.s042.autoescolas042.domain.alunos.Aluno;
import br.com.senai.s042.autoescolas042.domain.alunos.AlunoNaoExisteException;
import br.com.senai.s042.autoescolas042.domain.alunos.AlunoRepository;
import br.com.senai.s042.autoescolas042.domain.instrucao.validacoes.ValidadorAgendamento;
import br.com.senai.s042.autoescolas042.domain.instrutor.Instrutor;
import br.com.senai.s042.autoescolas042.domain.instrutor.InstrutorNaoExisteException;
import br.com.senai.s042.autoescolas042.domain.instrutor.InstrutorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeInstrucoes {
    private final InstrucaoRepository repository;
    private final AlunoRepository alunoRepository;
    private final InstrutorRepository instrutorRepository;
    private final List<ValidadorAgendamento> validadores;

    public AgendaDeInstrucoes(
            InstrucaoRepository repository,
            AlunoRepository alunoRepository,
            InstrutorRepository instrutorRepository,
            List<ValidadorAgendamento> validadores) {
        this.repository = repository;
        this.alunoRepository = alunoRepository;
        this.instrutorRepository = instrutorRepository;
        this.validadores = validadores;
    }

    @Transactional
    public DadosDetalhamentoInstrucao agendarInstrucao(DadosAgendamentoInstrucao dados) {
        if(!alunoRepository.existsById(dados.idAluno())) {
            throw new AlunoNaoExisteException("ID do aluno informado não existe!");
        }
        if(dados.idInstrutor() != null && !instrutorRepository.existsById(dados.idInstrutor())) {
            throw new InstrutorNaoExisteException("ID do instrutor informado não existe!");
        }

        validadores.forEach(v -> v.validar(dados));

        Aluno aluno = alunoRepository.getReferenceById(dados.idAluno());
        Instrutor instrutor = escolherInstrutor(dados);

        if (instrutor == null) {
            throw new InstrutorIndisponivelException("Não há instrutor dísponivel para a data e hora escolhida!");
        }

        Instrucao instrucao = new Instrucao(
                null,
                aluno,
                instrutor,
                dados.data()
        );
        repository.save(instrucao);
        return new DadosDetalhamentoInstrucao(instrucao);
    }

    private Instrutor escolherInstrutor(DadosAgendamentoInstrucao dados) {
        if(dados.idInstrutor() != null) {
            return instrutorRepository.getReferenceById(dados.idInstrutor());
        }
        if(dados.especialidade() == null) {
            throw new EspecialidadeNaoInformada("Especialidade é obrigatória quando o instrutor não for informado!");
        }
        return instrutorRepository.escolherInstrutorAleatorioDisponivel(dados.especialidade(), dados.data());
    }
}