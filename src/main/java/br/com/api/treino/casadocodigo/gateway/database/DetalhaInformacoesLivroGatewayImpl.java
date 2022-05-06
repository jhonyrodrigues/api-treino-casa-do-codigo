package br.com.api.treino.casadocodigo.gateway.database;

import br.com.api.treino.casadocodigo.gateway.DetalhaInformacoesLivroGateway;
import br.com.api.treino.casadocodigo.gateway.database.adapter.LivroAdapter;
import br.com.api.treino.casadocodigo.gateway.database.repository.LivroRepository;
import br.com.api.treino.casadocodigo.gateway.exception.NotFoundGatewayException;
import br.com.api.treino.casadocodigo.model.LivroDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DetalhaInformacoesLivroGatewayImpl implements DetalhaInformacoesLivroGateway {

    private final LivroRepository livroRepository;

    private final LivroAdapter livroAdapter;

    @Override
    public LivroDomain detalha(Long id) {
        return livroRepository.findById(id)
                .map(livroAdapter::converteLivroDatabaseParaLivroDomain)
                .orElseThrow(() -> new NotFoundGatewayException("[GATEWAY] - Livro não encontrado"));
    }
}