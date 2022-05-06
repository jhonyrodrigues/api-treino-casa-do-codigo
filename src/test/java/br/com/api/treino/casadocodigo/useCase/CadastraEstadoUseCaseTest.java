package br.com.api.treino.casadocodigo.useCase;

import br.com.api.treino.casadocodigo.controller.model.CadastraEstadoRequest;
import br.com.api.treino.casadocodigo.gateway.CadastraEstadoGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith({MockitoExtension.class})
class CadastraEstadoUseCaseTest {

    @InjectMocks
    private CadastraEstadoUseCase useCase;

    @Mock
    private CadastraEstadoGateway gateway;

    private CadastraEstadoRequest estadoRequest;

    @BeforeEach
    void setUp() {
        iniciaEstado();
    }

    @Test
    void deveCadastrarNovoEstadoComSucesso() {

        assertDoesNotThrow(() -> useCase.cadastra(estadoRequest));
    }

    private void iniciaEstado() {
        estadoRequest = CadastraEstadoRequest.builder().estado("São Paulo").paisId(1).build();
    }
}