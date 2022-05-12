package br.com.api.treino.casadocodigo.gateway.database;

import br.com.api.treino.casadocodigo.controller.model.CadastraNovoClienteRequest;
import br.com.api.treino.casadocodigo.gateway.CadastraNovoClienteGateway;
import br.com.api.treino.casadocodigo.gateway.database.adapter.ClienteAdapter;
import br.com.api.treino.casadocodigo.gateway.database.model.ClienteDatabase;
import br.com.api.treino.casadocodigo.gateway.database.repository.ClienteRepository;
import br.com.api.treino.casadocodigo.gateway.database.repository.EstadoRepository;
import br.com.api.treino.casadocodigo.gateway.database.repository.PaisRepository;
import br.com.api.treino.casadocodigo.gateway.exception.GatewayException;
import br.com.api.treino.casadocodigo.gateway.exception.NotFoundGatewayException;
import br.com.api.treino.casadocodigo.model.ClienteDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CadastraNovoClienteGatewayImpl implements CadastraNovoClienteGateway {

    private final ClienteRepository clienteRepository;

    private final PaisRepository paisRepository;

    private final EstadoRepository estadoRepository;

    private final ClienteAdapter clienteAdapter;

    @Override
    public ClienteDomain cadastra(ClienteDomain clienteDomain, CadastraNovoClienteRequest request) {

        var pais = paisRepository.findById(Long.valueOf(request.getPaisId())).orElseThrow(() -> new NotFoundGatewayException("[GATEWAY] - País não encontrado!"));

        var estado = estadoRepository.findById(Long.valueOf(request.getEstadoId())).orElseThrow(() -> new NotFoundGatewayException("[GATEWAY] - Estado não encontrado!"));

        try {
            ClienteDatabase clienteDatabase = clienteRepository.save(clienteAdapter.converteClienteDomainParaClienteDatabase(clienteDomain, estado, pais));

            return clienteAdapter.converteClienteDatabaseParaClienteDomain(clienteDatabase);

        } catch (DataIntegrityViolationException e) {
            throw new DuplicateKeyException("[GATEWAY] - Email já cadastrado!");
        } catch (Exception e) {
            throw new GatewayException("[GATEWAY] - Problema ao cadastrar novo cliente", e);
        }
    }
}