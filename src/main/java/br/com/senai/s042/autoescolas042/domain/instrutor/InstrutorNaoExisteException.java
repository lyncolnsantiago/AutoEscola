package br.com.senai.s042.autoescolas042.domain.instrutor;

public class InstrutorNaoExisteException extends RuntimeException {
    public InstrutorNaoExisteException(String message) {
        super(message);
    }
}
