package br.com.api.treino.casadocodigo.useCase;

import br.com.api.treino.casadocodigo.controller.model.CadastraNovoAutorRequest;
import br.com.api.treino.casadocodigo.controller.model.CadastraNovoAutorResponse;
import br.com.api.treino.casadocodigo.gateway.CadastraNovoAutorGateway;
import br.com.api.treino.casadocodigo.gateway.exception.GatewayException;
import br.com.api.treino.casadocodigo.model.AutorDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
@RequiredArgsConstructor
public class CadastraNovoAutorUseCase {

    private final CadastraNovoAutorGateway cadastraNovoAutorGateway;

    public CadastraNovoAutorResponse cadastra(CadastraNovoAutorRequest request) throws GatewayException {

        AutorDomain autorDomain = cadastraNovoAutorGateway.cadastra(AutorDomain.builder().nome(request.getNome()).email(request.getEmail()).descricao(request.getDescricao())
                .registradoEm(LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))).build());

        return CadastraNovoAutorResponse.builder().nome(autorDomain.getNome()).email(autorDomain.getEmail()).descricao(autorDomain.getDescricao())
                .registradoEm(autorDomain.getRegistradoEm()).build();
    }
}