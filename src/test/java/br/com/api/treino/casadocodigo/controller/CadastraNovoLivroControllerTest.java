package br.com.api.treino.casadocodigo.controller;

import br.com.api.treino.casadocodigo.controller.model.CadastraNovoLivroRequest;
import br.com.api.treino.casadocodigo.controller.model.CadastraNovoLivroResponse;
import br.com.api.treino.casadocodigo.useCase.CadastraNovoLivroUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

@ExtendWith(MockitoExtension.class)
class CadastraNovoLivroControllerTest {

    private CadastraNovoLivroRequest novoLivroRequest;

    private CadastraNovoLivroResponse novoLivroResponse;

    private final LocalDate dataLancamento = LocalDate.now().plusDays(1);

    @InjectMocks
    private CadastraNovoLivroController controller;

    @Mock
    private CadastraNovoLivroUseCase useCase;

    @BeforeEach
    void setup() {
        iniciaLivro();
    }

    @Test
    void deveCadastrarNovoLivroComRetornoCreated() {

        when(useCase.cadastra(any())).thenReturn(novoLivroResponse);

        var response = controller.cadastra(novoLivroRequest);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(CadastraNovoLivroResponse.class, response.getClass());
        Assertions.assertEquals("Jhony", response.getAutor());
        Assertions.assertEquals("Java", response.getTitulo());
        Assertions.assertEquals(dataLancamento, response.getDataLancamento());
    }

    private void iniciaLivro() {
        novoLivroRequest = new CadastraNovoLivroRequest("Java", "Desenvolvimento","Desenvolvimento Backend com Java", 120, 100, "xpto2022", dataLancamento, 1, 1);
        novoLivroResponse = new CadastraNovoLivroResponse("Java", "Jhony", dataLancamento);
    }
}