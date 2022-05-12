package br.com.api.treino.casadocodigo.gateway;

import br.com.api.treino.casadocodigo.controller.model.CadastraNovoClienteRequest;
import br.com.api.treino.casadocodigo.model.ClienteDomain;

public interface CadastraNovoClienteGateway {
    ClienteDomain cadastra(ClienteDomain clienteDomain, CadastraNovoClienteRequest request);
}