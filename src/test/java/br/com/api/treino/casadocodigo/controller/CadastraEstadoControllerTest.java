package br.com.api.treino.casadocodigo.controller;

import br.com.api.treino.casadocodigo.controller.model.CadastraEstadoRequest;
import br.com.api.treino.casadocodigo.useCase.CadastraEstadoUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class CadastraEstadoControllerTest {

    @InjectMocks
    private CadastraEstadoController controller;

    @Mock
    private CadastraEstadoUseCase useCase;

    private CadastraEstadoRequest estadoRequest;

    @BeforeEach
    void setUp() {
        iniciaEstado();
    }

    @Test
    void deveCadastrarNovoEstadoComSucesso() {
        assertDoesNotThrow(()-> controller.cadastra(estadoRequest));
    }

    private void iniciaEstado() {
        estadoRequest = CadastraEstadoRequest.builder().estado("São Paulo").paisId(1).build();
    }
}