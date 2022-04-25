package br.com.api.treino.casadocodigo.gateway.database;

import br.com.api.treino.casadocodigo.gateway.CadastraNovoAutorGateway;
import br.com.api.treino.casadocodigo.gateway.database.model.AutorDatabase;
import br.com.api.treino.casadocodigo.gateway.database.repository.AutorRepository;
import br.com.api.treino.casadocodigo.gateway.exception.CadastraNovoAutorGatewayException;
import br.com.api.treino.casadocodigo.model.AutorDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CadastraNovoAutorGatewayImpl implements CadastraNovoAutorGateway {

    private final AutorRepository autorRepository;

    @Override
    public AutorDomain cadastra(AutorDomain autorDomain) throws CadastraNovoAutorGatewayException {
        try {
            AutorDatabase autorDatabase = autorRepository.save(AutorDatabase.builder().nome(autorDomain.getNome()).email(autorDomain.getEmail()).descricao(autorDomain.getDescricao())
                    .registradoEm(autorDomain.getRegistradoEm()).build());

            return AutorDomain.builder().nome(autorDatabase.getNome()).email(autorDatabase.getEmail()).descricao(autorDatabase.getDescricao()).registradoEm(autorDatabase.getRegistradoEm()).build();

        } catch (DataIntegrityViolationException e) {
            throw new DuplicateKeyException("[GATEWAY] - E-mail cadastrado!");
        } catch (Exception e) {
            throw new CadastraNovoAutorGatewayException("[GATEWAY] - Problema ao cadastrar novo autor", e);
        }
    }
}