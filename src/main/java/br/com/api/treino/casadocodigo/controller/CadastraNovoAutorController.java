package br.com.api.treino.casadocodigo.controller;

import br.com.api.treino.casadocodigo.controller.model.CadastraNovoAutorRequest;
import br.com.api.treino.casadocodigo.controller.model.CadastraNovoAutorResponse;
import br.com.api.treino.casadocodigo.gateway.exception.CadastraNovoAutorGatewayException;
import br.com.api.treino.casadocodigo.useCase.CadastraNovoAutorUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CadastraNovoAutorController implements CadastraNovoAutorApi {

    private final CadastraNovoAutorUseCase cadastraNovoAutorUseCase;

    @Override
    public CadastraNovoAutorResponse cadastra(CadastraNovoAutorRequest request) throws CadastraNovoAutorGatewayException {
        return cadastraNovoAutorUseCase.cadastra(request);
    }
}