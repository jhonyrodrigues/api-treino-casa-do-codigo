package br.com.api.treino.casadocodigo.gateway.database;

import br.com.api.treino.casadocodigo.gateway.CadastraPaisGateway;
import br.com.api.treino.casadocodigo.gateway.database.model.PaisDatabase;
import br.com.api.treino.casadocodigo.gateway.database.repository.PaisRepository;
import br.com.api.treino.casadocodigo.gateway.exception.GatewayException;
import br.com.api.treino.casadocodigo.model.PaisDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CadastraPaisGatewayImpl implements CadastraPaisGateway {

    private final PaisRepository paisRepository;

    @Override
    public void cadastra(PaisDomain paisDomain) {
        try {
            paisRepository.save(PaisDatabase.builder().pais(paisDomain.getPais()).build());
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateKeyException("[GATEWAY] - País já foi cadastrado!");
        } catch (Exception e) {
            throw new GatewayException("[GATEWAY] - Problema ao cadastrar país!", e);
        }
    }
}