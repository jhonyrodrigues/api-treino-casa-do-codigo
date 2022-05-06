package br.com.api.treino.casadocodigo.controller;

import br.com.api.treino.casadocodigo.controller.model.CadastraEstadoRequest;
import br.com.api.treino.casadocodigo.useCase.CadastraEstadoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CadastraEstadoController implements CadastraEstadoApi {

    private final CadastraEstadoUseCase cadastraEstadoUseCase;

    @Override
    public void cadastra(CadastraEstadoRequest request) {
        cadastraEstadoUseCase.cadastra(request);
    }
}