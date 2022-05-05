package br.com.api.treino.casadocodigo.controller;

import br.com.api.treino.casadocodigo.controller.model.ListaLivrosCadastradosResponse;
import br.com.api.treino.casadocodigo.useCase.ListaLivrosCadastradosUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ListaLivrosCadastradosController implements  ListaLivrosCadastradosApi {

    private final ListaLivrosCadastradosUseCase listaLivrosCadastradosUseCase;
    @Override
    public List<ListaLivrosCadastradosResponse> lista() {
        return listaLivrosCadastradosUseCase.lista();
    }
}