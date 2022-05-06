package br.com.api.treino.casadocodigo.gateway.database;

import br.com.api.treino.casadocodigo.gateway.CadastraEstadoGateway;
import br.com.api.treino.casadocodigo.gateway.database.model.EstadoDatabase;
import br.com.api.treino.casadocodigo.gateway.database.model.PaisDatabase;
import br.com.api.treino.casadocodigo.gateway.database.repository.EstadoRepository;
import br.com.api.treino.casadocodigo.gateway.database.repository.PaisRepository;
import br.com.api.treino.casadocodigo.gateway.exception.GatewayException;
import br.com.api.treino.casadocodigo.gateway.exception.NotFoundGatewayException;
import br.com.api.treino.casadocodigo.model.EstadoDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CadastraEstadoGatewayImpl implements CadastraEstadoGateway {

    private final PaisRepository paisRepository;

    private final EstadoRepository estadoRepository;

    @Override
    public void cadastra(EstadoDomain estadoDomain, int paisId) {

        PaisDatabase paisDatabase = paisRepository.findById((long) paisId)
                .orElseThrow(() -> new NotFoundGatewayException("[GATEWAY] - País não cadastrado"));

        try {
            estadoRepository.save(EstadoDatabase.builder().estado(estadoDomain.getEstado()).paisDatabase(paisDatabase).build());
        }catch (DataIntegrityViolationException e){
            throw new DuplicateKeyException("[GATEWAY] - Estado já foi cadastrado!");
        }catch (Exception e) {
            throw new GatewayException("[GATEWAY] - Problema ao cadastrar novo estado!", e);
        }
    }
}