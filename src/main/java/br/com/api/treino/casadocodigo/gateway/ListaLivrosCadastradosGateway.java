package br.com.api.treino.casadocodigo.gateway;

import br.com.api.treino.casadocodigo.model.LivroDomain;

import java.util.List;

public interface ListaLivrosCadastradosGateway {
    List<LivroDomain> lista();
}