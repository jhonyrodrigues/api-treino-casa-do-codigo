package br.com.api.treino.casadocodigo.gateway.database.adapter;

import br.com.api.treino.casadocodigo.controller.model.CadastraNovoClienteRequest;
import br.com.api.treino.casadocodigo.controller.model.CadastraNovoClienteResponse;
import br.com.api.treino.casadocodigo.gateway.database.model.ClienteDatabase;
import br.com.api.treino.casadocodigo.gateway.database.model.EstadoDatabase;
import br.com.api.treino.casadocodigo.gateway.database.model.PaisDatabase;
import br.com.api.treino.casadocodigo.model.ClienteDomain;
import br.com.api.treino.casadocodigo.model.EstadoDomain;
import br.com.api.treino.casadocodigo.model.PaisDomain;
import org.springframework.stereotype.Service;

@Service
public class ClienteAdapter {
    public ClienteDomain converteCadastraNovoClienteRequestParaClienteDomain(CadastraNovoClienteRequest request) {
        return ClienteDomain.builder().email(request.getEmail()).nome(request.getNome()).sobrenome(request.getSobrenome())
                .documento(request.getDocumento()).endereco(request.getEndereco()).complemento(request.getComplemento())
                .cidade(request.getCidade()).telefone(request.getTelefone())
                .cep(request.getCep()).build();
    }

    public CadastraNovoClienteResponse converteClienteDomainParaClienteResponse(ClienteDomain clienteDomain) {
        return CadastraNovoClienteResponse.builder().id(clienteDomain.getId()).build();
    }

    public ClienteDatabase converteClienteDomainParaClienteDatabase(ClienteDomain clienteDomain, EstadoDatabase estadoDatabase, PaisDatabase paisDatabase) {
        return ClienteDatabase.builder().email(clienteDomain.getEmail()).nome(clienteDomain.getNome()).sobrenome(clienteDomain.getSobrenome())
                .documento(clienteDomain.getDocumento()).endereco(clienteDomain.getEndereco()).complemento(clienteDomain.getComplemento())
                .cidade(clienteDomain.getCidade()).pais(paisDatabase).estado(estadoDatabase).telefone(clienteDomain.getTelefone())
                .cep(clienteDomain.getCep()).build();
    }

    public ClienteDomain converteClienteDatabaseParaClienteDomain(ClienteDatabase clienteDatabase) {
        return ClienteDomain.builder().id(clienteDatabase.getId()).email(clienteDatabase.getEmail()).nome(clienteDatabase.getNome())
                .sobrenome(clienteDatabase.getSobrenome()).documento(clienteDatabase.getDocumento()).endereco(clienteDatabase.getEndereco())
                .complemento(clienteDatabase.getComplemento()).cidade(clienteDatabase.getCidade())
                .pais(PaisDomain.builder().pais(clienteDatabase.getPais().getPais()).build())
                .estado(EstadoDomain.builder().estado(clienteDatabase.getEstado().getEstado())
                        .paisDomain(PaisDomain.builder().pais(clienteDatabase.getPais().getPais()).build()).build())
                .telefone(clienteDatabase.getTelefone()).cep(clienteDatabase.getCep()).build();
    }
}