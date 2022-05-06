package br.com.api.treino.casadocodigo.useCase;

import br.com.api.treino.casadocodigo.controller.model.DetalhaInformacoesLivroResponse;
import br.com.api.treino.casadocodigo.gateway.DetalhaInformacoesLivroGateway;
import br.com.api.treino.casadocodigo.gateway.database.adapter.LivroAdapter;
import br.com.api.treino.casadocodigo.model.LivroDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DetalhaInformacoesLivroUseCase {

    private final DetalhaInformacoesLivroGateway detalhaInformacoesLivroGateway;

    private final LivroAdapter livroAdapter;

    public DetalhaInformacoesLivroResponse detalha(Long id) {
        LivroDomain livroDomain = detalhaInformacoesLivroGateway.detalha(id);
        return livroAdapter.converteLivroDomainParaDetalhaInformacoesLivroResponse(livroDomain);
    }
}