package br.com.api.treino.casadocodigo.controller;

import br.com.api.treino.casadocodigo.controller.model.CadastraPaisRequest;
import br.com.api.treino.casadocodigo.useCase.CadastraPaisUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CadastraPaisController implements CadastraPaisApi {

    private final CadastraPaisUseCase cadastraPaisUseCase;

    @Override
    public void cadastra(CadastraPaisRequest request) {
        cadastraPaisUseCase.cadastra(request);
    }
}