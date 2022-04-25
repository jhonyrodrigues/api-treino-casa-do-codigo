package br.com.api.treino.casadocodigo.useCase;

import br.com.api.treino.casadocodigo.controller.model.CadastraNovaCategoriaRequest;
import br.com.api.treino.casadocodigo.controller.model.CadastraNovaCategoriaResponse;
import br.com.api.treino.casadocodigo.gateway.CadastraNovaCategoriaGateway;
import br.com.api.treino.casadocodigo.gateway.exception.CadastraNovaCategoriaGatewayException;
import br.com.api.treino.casadocodigo.model.CategoriaDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CadastraNovaCategoriaUseCaseTest {

    private CadastraNovaCategoriaRequest categoriaRequest = new CadastraNovaCategoriaRequest();

    private CategoriaDomain categoriaDomain = new CategoriaDomain();

    @InjectMocks
    private CadastraNovaCategoriaUseCase useCase;

    @Mock
    private CadastraNovaCategoriaGateway gateway;

    @BeforeEach
    void setup() {
        iniciaCategoria();
    }

    @Test
    void deveCadastrarNovaCategoriaComRetornoDeSucesso() throws CadastraNovaCategoriaGatewayException {

        when(gateway.cadastra(any())).thenReturn(categoriaDomain);

        CadastraNovaCategoriaResponse response = useCase.cadastra(categoriaRequest);

        assertNotNull(response);
        assertEquals(CadastraNovaCategoriaResponse.class, response.getClass());
        assertEquals("Programação", response.getCategoria());
    }

    private void iniciaCategoria() {
        categoriaRequest = new CadastraNovaCategoriaRequest("Programaçã0");
        categoriaDomain = new CategoriaDomain("Programação");
    }
}