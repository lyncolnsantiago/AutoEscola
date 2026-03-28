package br.com.senai.s042.autoescolas042.exception.types.usuario;

public class UsuarioNaoExisteException extends RuntimeException {
    public UsuarioNaoExisteException(String message) {
        super(message);
    }
}