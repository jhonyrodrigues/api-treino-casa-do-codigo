package br.com.api.treino.casadocodigo.gateway.exception;

public class CadastraNovoAutorGatewayException extends RuntimeException {
    public CadastraNovoAutorGatewayException(String message, Exception error) {
        super(message, error);
    }
}