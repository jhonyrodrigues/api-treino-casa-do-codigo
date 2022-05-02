package br.com.api.treino.casadocodigo.useCase;

import br.com.api.treino.casadocodigo.controller.model.CadastraNovoAutorRequest;
import br.com.api.treino.casadocodigo.controller.model.CadastraNovoAutorResponse;
import br.com.api.treino.casadocodigo.gateway.CadastraNovoAutorGateway;
import br.com.api.treino.casadocodigo.gateway.exception.GatewayException;
import br.com.api.treino.casadocodigo.model.AutorDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CadastraNovoAutorUseCaseTest {

    @InjectMocks
    private CadastraNovoAutorUseCase useCase;

    @Mock
    private CadastraNovoAutorGateway gateway;

    private CadastraNovoAutorRequest request;

    private AutorDomain autorDomain;


    @BeforeEach
    void setup() {
        iniciaAutor();
    }

    @Test
    void deveCadastrarNovoAutorComRetornoDeSucesso() throws GatewayException {

        when(gateway.cadastra(any())).thenReturn(autorDomain);

        CadastraNovoAutorResponse response = useCase.cadastra(request);

        assertNotNull(response);
        assertEquals(CadastraNovoAutorResponse.class, response.getClass());
        assertEquals("Jhony", response.getNome());
        assertEquals("teste@teste.com", response.getEmail());
        assertEquals("Programador Backend", response.getDescricao());
    }

    private void iniciaAutor() {
        request = new CadastraNovoAutorRequest("Jhony", "teste@teste.com", "Programador Backend");
        autorDomain = new AutorDomain("Jhony", "teste@teste.com", "Programador Backend", LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
    }
}