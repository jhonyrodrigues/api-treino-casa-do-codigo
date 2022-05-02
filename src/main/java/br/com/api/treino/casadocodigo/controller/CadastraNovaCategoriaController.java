package br.com.api.treino.casadocodigo.controller;

import br.com.api.treino.casadocodigo.controller.model.CadastraNovaCategoriaRequest;
import br.com.api.treino.casadocodigo.controller.model.CadastraNovaCategoriaResponse;
import br.com.api.treino.casadocodigo.gateway.exception.GatewayException;
import br.com.api.treino.casadocodigo.useCase.CadastraNovaCategoriaUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CadastraNovaCategoriaController implements CadastraNovaCategoriaApi {

    private final CadastraNovaCategoriaUseCase cadastraNovaCategoriaUseCase;

    @Override
    public CadastraNovaCategoriaResponse cadastra(CadastraNovaCategoriaRequest request) throws GatewayException {
        return cadastraNovaCategoriaUseCase.cadastra(request);
    }
}