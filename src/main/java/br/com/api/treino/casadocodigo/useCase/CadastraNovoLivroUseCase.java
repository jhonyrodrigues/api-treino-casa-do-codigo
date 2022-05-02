package br.com.api.treino.casadocodigo.useCase;

import br.com.api.treino.casadocodigo.controller.model.CadastraNovoLivroRequest;
import br.com.api.treino.casadocodigo.controller.model.CadastraNovoLivroResponse;
import br.com.api.treino.casadocodigo.gateway.CadastraNovoLivroGateway;
import br.com.api.treino.casadocodigo.gateway.database.adapter.LivroAdapter;
import br.com.api.treino.casadocodigo.model.LivroDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CadastraNovoLivroUseCase {

    private final CadastraNovoLivroGateway cadastraNovoLivroGateway;

    private final LivroAdapter livroAdapter;

    public CadastraNovoLivroResponse cadastra(CadastraNovoLivroRequest request){

        LivroDomain livroDomain = cadastraNovoLivroGateway.cadastra(livroAdapter.converteLivroRequestParaLivroDomain(request), request);

        return livroAdapter.converteLivroDomainParaLivroResponse(livroDomain);
    }
}