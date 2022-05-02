package br.com.api.treino.casadocodigo.gateway.exception;

public class GatewayException extends RuntimeException {
    public GatewayException(String message, Exception error) {
        super(message, error);
    }
}