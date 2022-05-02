package br.com.api.treino.casadocodigo.gateway.exception;

public class NotFoundGatewayException extends RuntimeException {
    public NotFoundGatewayException(String message) {
        super(message);
    }
}
