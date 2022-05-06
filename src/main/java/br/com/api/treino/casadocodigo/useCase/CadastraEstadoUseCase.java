package br.com.api.treino.casadocodigo.useCase;

import br.com.api.treino.casadocodigo.controller.model.CadastraEstadoRequest;
import br.com.api.treino.casadocodigo.gateway.CadastraEstadoGateway;
import br.com.api.treino.casadocodigo.model.EstadoDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CadastraEstadoUseCase {

    private final CadastraEstadoGateway cadastraEstadoGateway;

    public void cadastra(CadastraEstadoRequest request) {
        cadastraEstadoGateway.cadastra(EstadoDomain.builder().estado(request.getEstado()).build(), request.getPaisId());
    }
}