package br.com.api.treino.casadocodigo.gateway.exception;

public class CadastraNovaCategoriaGatewayException extends RuntimeException {
    public CadastraNovaCategoriaGatewayException(String message, Exception error) {
        super(message, error);
    }
}