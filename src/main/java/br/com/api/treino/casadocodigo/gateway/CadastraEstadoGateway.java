package br.com.api.treino.casadocodigo.gateway;

import br.com.api.treino.casadocodigo.model.EstadoDomain;

public interface CadastraEstadoGateway {
    void cadastra(EstadoDomain estadoDomain, int paisId);
}