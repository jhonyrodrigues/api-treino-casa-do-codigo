package br.com.api.treino.casadocodigo.useCase;

import br.com.api.treino.casadocodigo.controller.model.CadastraNovaCategoriaRequest;
import br.com.api.treino.casadocodigo.controller.model.CadastraNovaCategoriaResponse;
import br.com.api.treino.casadocodigo.gateway.CadastraNovaCategoriaGateway;
import br.com.api.treino.casadocodigo.gateway.exception.GatewayException;
import br.com.api.treino.casadocodigo.model.CategoriaDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CadastraNovaCategoriaUseCase {

    private final CadastraNovaCategoriaGateway cadastraNovaCategoriaGateway;

    public CadastraNovaCategoriaResponse cadastra(CadastraNovaCategoriaRequest request) throws GatewayException {

        CategoriaDomain categoriaDomain = cadastraNovaCategoriaGateway.cadastra(CategoriaDomain.builder().categoria(request.getCategoria()).build());

        return CadastraNovaCategoriaResponse.builder().categoria(categoriaDomain.getCategoria()).build();
    }
}