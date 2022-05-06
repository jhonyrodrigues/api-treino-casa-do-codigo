package br.com.api.treino.casadocodigo.controller;

import br.com.api.treino.casadocodigo.controller.model.CadastraPaisRequest;
import br.com.api.treino.casadocodigo.useCase.CadastraPaisUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class CadastraPaisControllerTest {

    @InjectMocks
    private CadastraPaisController controller;

    @Mock
    private CadastraPaisUseCase useCase;

    private CadastraPaisRequest paisRequest;

    @BeforeEach
    void setup(){
        iniciaPais();
    }

    @Test
    void deveCadastrarNovoPaisComSucesso() {
        assertDoesNotThrow(()-> controller.cadastra(paisRequest));
    }

    private void iniciaPais() {
        paisRequest = CadastraPaisRequest.builder().pais("Brasil").build();
    }
}