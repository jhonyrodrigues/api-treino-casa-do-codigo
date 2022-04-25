package br.com.api.treino.casadocodigo.controller;

import br.com.api.treino.casadocodigo.controller.model.CadastraNovaCategoriaRequest;
import br.com.api.treino.casadocodigo.controller.model.CadastraNovaCategoriaResponse;
import br.com.api.treino.casadocodigo.gateway.exception.CadastraNovaCategoriaGatewayException;
import br.com.api.treino.casadocodigo.useCase.CadastraNovaCategoriaUseCase;
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
class CadastraNovaCategoriaControllerTest {

    private CadastraNovaCategoriaRequest categoriaRequest = new CadastraNovaCategoriaRequest();

    private CadastraNovaCategoriaResponse categoriaResponse = new CadastraNovaCategoriaResponse();

    @InjectMocks
    private CadastraNovaCategoriaController controller;

    @Mock
    private CadastraNovaCategoriaUseCase useCase;

    @BeforeEach
    void setup(){
        iniciaCategoria();
    }

    @Test
    void deveCadastrarNovaCategoriaComRetornoCreated() throws CadastraNovaCategoriaGatewayException {

        when(useCase.cadastra(any())).thenReturn(categoriaResponse);

        CadastraNovaCategoriaResponse response = controller.cadastra(categoriaRequest);

        assertNotNull(response);
        assertEquals(CadastraNovaCategoriaResponse.class, response.getClass());
        assertEquals("Programação", response.getCategoria());

    }

    private void iniciaCategoria() {
        categoriaRequest =  new CadastraNovaCategoriaRequest("Programação");
        categoriaResponse = new CadastraNovaCategoriaResponse("Programação");
    }
}