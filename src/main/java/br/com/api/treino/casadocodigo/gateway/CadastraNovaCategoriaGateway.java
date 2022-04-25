package br.com.api.treino.casadocodigo.gateway;

import br.com.api.treino.casadocodigo.gateway.exception.CadastraNovaCategoriaGatewayException;
import br.com.api.treino.casadocodigo.model.CategoriaDomain;

public interface CadastraNovaCategoriaGateway {
    CategoriaDomain cadastra(CategoriaDomain categoriaDomain) throws CadastraNovaCategoriaGatewayException;
}