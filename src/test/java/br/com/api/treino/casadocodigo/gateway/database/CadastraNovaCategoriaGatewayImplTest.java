package br.com.api.treino.casadocodigo.gateway.database;

import br.com.api.treino.casadocodigo.gateway.database.model.CategoriaDatabase;
import br.com.api.treino.casadocodigo.gateway.database.repository.CategoriaRepository;
import br.com.api.treino.casadocodigo.gateway.exception.CadastraNovaCategoriaGatewayException;
import br.com.api.treino.casadocodigo.model.CategoriaDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CadastraNovaCategoriaGatewayImplTest {

    private CategoriaDatabase categoriaDatabase = new CategoriaDatabase();

    private CategoriaDomain categoriaDomain = new CategoriaDomain();

    @InjectMocks
    private CadastraNovaCategoriaGatewayImpl gateway;

    @Mock
    private CategoriaRepository repository;

    @BeforeEach
    void setup(){
        iniciaCategoria();
    }

    @Test
    void deveCadastrarNovaCategoriaComRetornoDeSucesso() throws CadastraNovaCategoriaGatewayException {

        when(repository.save(any())).thenReturn(categoriaDatabase);

        var response = gateway.cadastra(categoriaDomain);

        assertNotNull(response);
        assertEquals(CategoriaDomain.class, response.getClass());
        assertEquals("Programação", response.getCategoria());
    }

    @Test
    void deveRetornarDuplicateKeyException(){

        when(repository.save(any())).thenThrow(new DataIntegrityViolationException("[GATEWAY] - Categoria já foi cadastrada!"));

        try {
            gateway.cadastra(categoriaDomain);
        }catch (Exception e){
            assertEquals(DuplicateKeyException.class, e.getClass());
            assertEquals("[GATEWAY] - Categoria já foi cadastrada!", e.getMessage());
        }
    }

    @Test
    void deveRetornarCadastraNovaCategoriaGatewayException(){

        when(repository.save(any())).thenThrow(new CadastraNovaCategoriaGatewayException("[GATEWAY] - Problema ao cadastrar nova categoria", new Exception()));

        try {
            gateway.cadastra(categoriaDomain);
        }catch (Exception e) {
            assertEquals(CadastraNovaCategoriaGatewayException.class, e.getClass());
            assertEquals("[GATEWAY] - Problema ao cadastrar nova categoria", e.getMessage());
        }
    }

    private void iniciaCategoria() {
        categoriaDatabase = new CategoriaDatabase(1L, "Programação");
        categoriaDomain = new CategoriaDomain("Programação");
    }
}