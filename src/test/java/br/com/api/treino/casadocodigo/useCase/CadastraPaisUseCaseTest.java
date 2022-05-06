package br.com.api.treino.casadocodigo.useCase;

import br.com.api.treino.casadocodigo.controller.model.CadastraPaisRequest;
import br.com.api.treino.casadocodigo.gateway.CadastraPaisGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class CadastraPaisUseCaseTest {

    @InjectMocks
    private CadastraPaisUseCase useCase;

    @Mock
    private CadastraPaisGateway gateway;

    private CadastraPaisRequest paisRequest;

    @BeforeEach
    void setUp() {
        iniciaPais();
    }

    @Test
    void deveCadastrarNovoPaisComSucesso() {
        assertDoesNotThrow(()-> useCase.cadastra(paisRequest));
    }

    private void iniciaPais() {
        paisRequest = CadastraPaisRequest.builder().pais("Brasil").build();
    }
}