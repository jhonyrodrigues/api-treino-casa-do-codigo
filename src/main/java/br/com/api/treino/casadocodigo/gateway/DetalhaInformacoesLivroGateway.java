package br.com.api.treino.casadocodigo.gateway;

import br.com.api.treino.casadocodigo.model.LivroDomain;

public interface DetalhaInformacoesLivroGateway {
    LivroDomain detalha(Long id);
}