package br.com.api.treino.casadocodigo.gateway.database;

import br.com.api.treino.casadocodigo.controller.model.CadastraNovoLivroRequest;
import br.com.api.treino.casadocodigo.gateway.CadastraNovoLivroGateway;
import br.com.api.treino.casadocodigo.gateway.database.adapter.LivroAdapter;
import br.com.api.treino.casadocodigo.gateway.database.model.LivroDatabase;
import br.com.api.treino.casadocodigo.gateway.database.repository.AutorRepository;
import br.com.api.treino.casadocodigo.gateway.database.repository.CategoriaRepository;
import br.com.api.treino.casadocodigo.gateway.database.repository.LivroRepository;
import br.com.api.treino.casadocodigo.gateway.exception.GatewayException;
import br.com.api.treino.casadocodigo.gateway.exception.NotFoundGatewayException;
import br.com.api.treino.casadocodigo.model.LivroDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CadastraNovoLivroGatewayImpl implements CadastraNovoLivroGateway {

    private final CategoriaRepository categoriaRepository;

    private final AutorRepository autorRepository;

    private final LivroRepository livroRepository;

    private final LivroAdapter livroAdapter;

    @Override
    public LivroDomain cadastra(LivroDomain livroDomain, CadastraNovoLivroRequest request) {

        var categoriaDatabase = categoriaRepository.findById((long) request.getIdCategoria())
                .orElseThrow(() -> new NotFoundGatewayException("[GATEWAY] - Categoria não encontrada!"));

        var autorDatabase = autorRepository.findById((long) request.getIdAutor())
                .orElseThrow(() -> new NotFoundGatewayException("[GATEWAY] - Autor não encontrado!"));

        try {
            LivroDatabase livroDatabase = livroRepository.save(livroAdapter.converteLivroDomainParaLivroDatabase(livroDomain, categoriaDatabase, autorDatabase));
            return livroAdapter.converteLivroDatabaseParaLivroDomain(livroDatabase);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateKeyException("[GATEWAY] - Título: ["+ livroDomain.getTitulo() + "] já cadastrado!");
        } catch (Exception e) {
            throw new GatewayException("[GATEWAY] - Problema ao cadastrar livro!", e);
        }
    }
}