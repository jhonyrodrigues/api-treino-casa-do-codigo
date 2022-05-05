package br.com.api.treino.casadocodigo.useCase;

import br.com.api.treino.casadocodigo.controller.model.ListaLivrosCadastradosResponse;
import br.com.api.treino.casadocodigo.gateway.ListaLivrosCadastradosGateway;
import br.com.api.treino.casadocodigo.gateway.database.adapter.LivroAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ListaLivrosCadastradosUseCase {

    private final ListaLivrosCadastradosGateway listaLivrosCadastradosGateway;

    private final LivroAdapter livroAdapter;

    public List<ListaLivrosCadastradosResponse> lista() {
        return listaLivrosCadastradosGateway.lista().stream()
                .map(livroAdapter::converteLivroDomainParaListaDeLivrosCadastradosResponse)
                .collect(Collectors.toList());
    }
}