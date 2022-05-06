package br.com.api.treino.casadocodigo.controller;

import br.com.api.treino.casadocodigo.controller.model.DetalhaInformacoesLivroResponse;
import br.com.api.treino.casadocodigo.useCase.DetalhaInformacoesLivroUseCase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class DetalhaInformacoesLivroController implements DetalhaInformacoesLivroApi {

    private DetalhaInformacoesLivroUseCase detalhaInformacoesLivroUseCase;

    @Override
    public DetalhaInformacoesLivroResponse detalha(Long id) {
        return detalhaInformacoesLivroUseCase.detalha(id);
    }
}