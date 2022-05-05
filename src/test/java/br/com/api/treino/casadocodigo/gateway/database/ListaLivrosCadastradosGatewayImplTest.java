package br.com.api.treino.casadocodigo.gateway.database;

import br.com.api.treino.casadocodigo.gateway.database.adapter.LivroAdapter;
import br.com.api.treino.casadocodigo.gateway.database.model.LivroDatabase;
import br.com.api.treino.casadocodigo.gateway.database.repository.LivroRepository;
import br.com.api.treino.casadocodigo.gateway.exception.GatewayException;
import br.com.api.treino.casadocodigo.model.LivroDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListaLivrosCadastradosGatewayImplTest {

    private LivroDatabase livroDatabase;

    private LivroDomain livroDomain;

    @InjectMocks
    private ListaLivrosCadastradosGatewayImpl gateway;

    @Mock
    private LivroRepository repository;

    @Mock
    private LivroAdapter livroAdapter;

    @BeforeEach
    void setUp() {
        iniciaLivro();
    }

    @Test
    void deveListarLivrosCadastrados() {

        when(repository.findAll()).thenReturn(List.of(livroDatabase));

        when(livroAdapter.converteLivroDatabaseParaLivroDomain(livroDatabase)).thenReturn(livroDomain);

        var response = gateway.lista();

        assertNotNull(response);
        assertEquals(ArrayList.class, response.getClass());
        assertEquals(1L, response.get(0).getId());
        assertEquals("Java", response.get(0).getTitulo());
    }

    @Test
    void deveRetornarGatewayException(){

        when(repository.findAll()).thenThrow(new GatewayException("[GATEWAY] - Problema ao listar os livros", new Exception()));

        assertThrows(Exception.class, () -> gateway.lista());
    }

    private void iniciaLivro() {
        livroDatabase = LivroDatabase.builder().id(1L).titulo("Java").build();
        livroDomain = LivroDomain.builder().id(1L).titulo("Java").build();
    }
}