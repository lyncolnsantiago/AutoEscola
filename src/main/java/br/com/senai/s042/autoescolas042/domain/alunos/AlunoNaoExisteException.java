package br.com.senai.s042.autoescolas042.domain.alunos;

public class AlunoNaoExisteException extends RuntimeException {
    public AlunoNaoExisteException(String message) {
        super(message);
    }
}
