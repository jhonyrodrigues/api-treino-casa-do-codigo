package br.com.api.treino.casadocodigo.gateway;

import br.com.api.treino.casadocodigo.controller.model.CadastraNovoLivroRequest;
import br.com.api.treino.casadocodigo.model.LivroDomain;

public interface CadastraNovoLivroGateway {
    LivroDomain cadastra(LivroDomain livroDomain, CadastraNovoLivroRequest request);
}