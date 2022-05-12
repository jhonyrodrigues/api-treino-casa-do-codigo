package br.com.api.treino.casadocodigo.controller;

import br.com.api.treino.casadocodigo.controller.model.CadastraNovoClienteRequest;
import br.com.api.treino.casadocodigo.controller.model.CadastraNovoClienteResponse;
import br.com.api.treino.casadocodigo.useCase.CadastraNovoClienteUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CadastraNovoClienteController implements CadastraNovoClienteApi {

    private final CadastraNovoClienteUseCase cadastraNovoClienteUseCase;

    @Override
    public CadastraNovoClienteResponse cadastra(CadastraNovoClienteRequest request) {
        return cadastraNovoClienteUseCase.cadastra(request);
    }
}