package br.com.api.treino.casadocodigo.controller;

import br.com.api.treino.casadocodigo.controller.model.CadastraNovoAutorRequest;
import br.com.api.treino.casadocodigo.controller.model.CadastraNovoAutorResponse;
import br.com.api.treino.casadocodigo.gateway.exception.CadastraNovoAutorGatewayException;
import br.com.api.treino.casadocodigo.useCase.CadastraNovoAutorUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CadastraNovoAutorControllerTest {

    private CadastraNovoAutorRequest autorRequest = new CadastraNovoAutorRequest();

    private CadastraNovoAutorResponse autorResponse = new CadastraNovoAutorResponse();

    @InjectMocks
    private CadastraNovoAutorController controller;

    @Mock
    CadastraNovoAutorUseCase useCase;

    @BeforeEach
    void setup() {
        iniciaAutor();
    }

    @Test
    void deveCadastrarNovoAutorComRetornoCreated() throws CadastraNovoAutorGatewayException {

        when(useCase.cadastra(any())).thenReturn(autorResponse);

        CadastraNovoAutorResponse response = controller.cadastra(autorRequest);

        assertEquals(CadastraNovoAutorResponse.class, response.getClass());
    }

    private void iniciaAutor() {
        autorRequest = new CadastraNovoAutorRequest("Jhony", "jhony.o_rodrigues@hotmail.com", "Programador Backend");
        autorResponse = new CadastraNovoAutorResponse("Jhony", "jhony.o_rodrigues@hotmail.com", "Programador Backend", LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
    }
}