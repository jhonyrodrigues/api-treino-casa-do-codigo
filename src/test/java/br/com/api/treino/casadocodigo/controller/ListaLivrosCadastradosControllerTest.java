package br.com.api.treino.casadocodigo.controller;

import br.com.api.treino.casadocodigo.controller.model.ListaLivrosCadastradosResponse;
import br.com.api.treino.casadocodigo.useCase.ListaLivrosCadastradosUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListaLivrosCadastradosControllerTest {

    private ListaLivrosCadastradosResponse livrosCadastradosResponse;

    @InjectMocks
    private ListaLivrosCadastradosController controller;

    @Mock
    private ListaLivrosCadastradosUseCase useCase;

    @BeforeEach
    void setUp() {
        iniciaLivro();
    }

    @Test
    void deveListarLivrosCadastradosComSucesso() {

        when(useCase.lista()).thenReturn(List.of(livrosCadastradosResponse));

        var response = controller.lista();

        assertNotNull(response);
        assertDoesNotThrow(() -> controller.lista());
        assertEquals(1, response.size());
        assertEquals(1L, response.get(0).getId());
        assertEquals("Java", response.get(0).getTitulo());
    }

    private void iniciaLivro() {
        livrosCadastradosResponse = ListaLivrosCadastradosResponse.builder().id(1L).titulo("Java").build();
    }
}