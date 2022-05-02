package br.com.api.treino.casadocodigo.gateway.database;

import br.com.api.treino.casadocodigo.gateway.CadastraNovaCategoriaGateway;
import br.com.api.treino.casadocodigo.gateway.database.model.CategoriaDatabase;
import br.com.api.treino.casadocodigo.gateway.database.repository.CategoriaRepository;
import br.com.api.treino.casadocodigo.gateway.exception.GatewayException;
import br.com.api.treino.casadocodigo.model.CategoriaDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CadastraNovaCategoriaGatewayImpl implements CadastraNovaCategoriaGateway {

    private final CategoriaRepository categoriaRepository;

    @Override
    public CategoriaDomain cadastra(CategoriaDomain categoriaDomain) throws GatewayException {
        try {
            CategoriaDatabase categoriaDatabase = categoriaRepository.save(CategoriaDatabase.builder().categoria(categoriaDomain.getCategoria()).build());

            return CategoriaDomain.builder().categoria(categoriaDatabase.getCategoria()).build();

        } catch (DataIntegrityViolationException e) {
            throw new DuplicateKeyException("[GATEWAY] - Categoria já foi cadastrada!");
        } catch (Exception e) {
            throw new GatewayException("[GATEWAY] - Problema ao cadastrar nova categoria", e);
        }
    }
}