package br.com.api.treino.casadocodigo.useCase;

import br.com.api.treino.casadocodigo.controller.model.ListaLivrosCadastradosResponse;
import br.com.api.treino.casadocodigo.gateway.database.ListaLivrosCadastradosGatewayImpl;
import br.com.api.treino.casadocodigo.gateway.database.adapter.LivroAdapter;
import br.com.api.treino.casadocodigo.model.LivroDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListaLivrosCadastradosUseCaseTest {

    private LivroDomain livroDomain;

    private ListaLivrosCadastradosResponse livrosResponse;

    @InjectMocks
    private ListaLivrosCadastradosUseCase useCase;

    @Mock
    private ListaLivrosCadastradosGatewayImpl gateway;

    @Mock
    private LivroAdapter livroAdapter;

    @BeforeEach
    void setUp() {
        iniciaLivro();
    }

    @Test
    void deveListarLivrosCadastrados() {

        when(gateway.lista()).thenReturn(List.of(livroDomain));

        when(livroAdapter.converteLivroDomainParaListaDeLivrosCadastradosResponse(livroDomain)).thenReturn(livrosResponse);

        var response = useCase.lista();

        assertNotNull(response);
        assertEquals(ArrayList.class, response.getClass());
        assertEquals(1L, response.get(0).getId());
        assertEquals("Java", response.get(0).getTitulo());
    }

    private void iniciaLivro() {
        livroDomain = LivroDomain.builder().id(1L).titulo("Java").build();
        livrosResponse = ListaLivrosCadastradosResponse.builder().id(1L).titulo("Java").build();
    }
}