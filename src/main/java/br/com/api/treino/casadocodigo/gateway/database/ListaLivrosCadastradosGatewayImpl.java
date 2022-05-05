package br.com.api.treino.casadocodigo.gateway.database;

import br.com.api.treino.casadocodigo.gateway.ListaLivrosCadastradosGateway;
import br.com.api.treino.casadocodigo.gateway.database.adapter.LivroAdapter;
import br.com.api.treino.casadocodigo.gateway.database.repository.LivroRepository;
import br.com.api.treino.casadocodigo.gateway.exception.GatewayException;
import br.com.api.treino.casadocodigo.model.LivroDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ListaLivrosCadastradosGatewayImpl implements ListaLivrosCadastradosGateway {

    private final LivroRepository livroRepository;

    private final LivroAdapter livroAdapter;

    public List<LivroDomain> lista() {
        try {
            return livroRepository.findAll().stream()
                    .map(livroAdapter::converteLivroDatabaseParaLivroDomain)
                    .collect(Collectors.toList());
        }catch (Exception e){
            throw new GatewayException("[GATEWAY] - Problema ao listar os livros", e);
        }
    }
}