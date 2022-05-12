package br.com.api.treino.casadocodigo.useCase;

import br.com.api.treino.casadocodigo.controller.model.CadastraNovoClienteRequest;
import br.com.api.treino.casadocodigo.controller.model.CadastraNovoClienteResponse;
import br.com.api.treino.casadocodigo.gateway.CadastraNovoClienteGateway;
import br.com.api.treino.casadocodigo.gateway.database.adapter.ClienteAdapter;
import br.com.api.treino.casadocodigo.model.ClienteDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CadastraNovoClienteUseCase {

    private final CadastraNovoClienteGateway cadastraNovoClienteGateway;

    private final ClienteAdapter clienteAdapter;

    public CadastraNovoClienteResponse cadastra(CadastraNovoClienteRequest request) {

        ClienteDomain clienteDomain = cadastraNovoClienteGateway.cadastra(clienteAdapter.converteCadastraNovoClienteRequestParaClienteDomain(request), request);

        return clienteAdapter.converteClienteDomainParaClienteResponse(clienteDomain);
    }
}