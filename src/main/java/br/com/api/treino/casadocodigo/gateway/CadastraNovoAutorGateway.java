package br.com.api.treino.casadocodigo.gateway;

import br.com.api.treino.casadocodigo.gateway.exception.GatewayException;
import br.com.api.treino.casadocodigo.model.AutorDomain;

public interface CadastraNovoAutorGateway {
    AutorDomain cadastra(AutorDomain autorDomain) throws GatewayException;
}