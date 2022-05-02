package br.com.api.treino.casadocodigo.controller;

import br.com.api.treino.casadocodigo.controller.model.CadastraNovoLivroRequest;
import br.com.api.treino.casadocodigo.controller.model.CadastraNovoLivroResponse;
import br.com.api.treino.casadocodigo.useCase.CadastraNovoLivroUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CadastraNovoLivroController implements CadastraNovoLivroApi {
    private final CadastraNovoLivroUseCase cadastraNovoLivroUseCase;

    @Override
    public CadastraNovoLivroResponse cadastra(CadastraNovoLivroRequest request) {
        return cadastraNovoLivroUseCase.cadastra(request);
    }
}