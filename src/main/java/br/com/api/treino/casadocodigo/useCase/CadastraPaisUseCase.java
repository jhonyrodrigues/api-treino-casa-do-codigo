package br.com.api.treino.casadocodigo.useCase;

import br.com.api.treino.casadocodigo.controller.model.CadastraPaisRequest;
import br.com.api.treino.casadocodigo.gateway.CadastraPaisGateway;
import br.com.api.treino.casadocodigo.model.PaisDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CadastraPaisUseCase {

    private final CadastraPaisGateway cadastraPaisGateway;

    public void cadastra(CadastraPaisRequest request) {
        cadastraPaisGateway.cadastra(PaisDomain.builder().pais(request.getPais()).build());
    }
}