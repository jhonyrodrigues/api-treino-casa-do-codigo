package br.com.api.treino.casadocodigo.gateway.database;

import br.com.api.treino.casadocodigo.gateway.database.model.PaisDatabase;
import br.com.api.treino.casadocodigo.gateway.database.repository.EstadoRepository;
import br.com.api.treino.casadocodigo.gateway.database.repository.PaisRepository;
import br.com.api.treino.casadocodigo.gateway.exception.GatewayException;
import br.com.api.treino.casadocodigo.gateway.exception.NotFoundGatewayException;
import br.com.api.treino.casadocodigo.model.EstadoDomain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CadastraEstadoGatewayImplTest {

    @InjectMocks
    private CadastraEstadoGatewayImpl gateway;

    @Mock
    private PaisRepository paisRepository;

    @Mock
    private EstadoRepository estadoRepository;

    private PaisDatabase paisDatabase;

    private EstadoDomain estadoDomain;

    private static final Integer ID = 1;

    @BeforeEach
    void setUp() {
        iniciaPais();
        iniciaEstado();
    }

    @Test
    void deveCadastrarNovoEstadoComSucesso() {

        when(paisRepository.findById(anyLong())).thenReturn(Optional.of(paisDatabase));

        Assertions.assertDoesNotThrow(() -> gateway.cadastra(estadoDomain, ID));
    }

    @Test
    void deveRetornarErroDuplicateKeyException(){

        when(paisRepository.findById(anyLong())).thenReturn(Optional.of(paisDatabase));

        when(estadoRepository.save(any())).thenThrow(new DataIntegrityViolationException("[GATEWAY] - Estado já foi cadastrado!"));

        assertThrows(DuplicateKeyException.class, ()-> gateway.cadastra(estadoDomain, ID));
    }

    @Test
    void deveRetornarNotFoundGatewayException(){

        when(paisRepository.findById(anyLong())).thenThrow(new NotFoundGatewayException("[GATEWAY] - País não cadastrado!"));

        assertThrows(NotFoundGatewayException.class, () -> gateway.cadastra(estadoDomain, ID));
    }

    @Test
    void deveRetornarGatewayException(){

        when(paisRepository.findById(anyLong())).thenReturn(Optional.of(paisDatabase));

        when(estadoRepository.save(any())).thenThrow(new GatewayException("[GATEWAY] - Problema ao cadastrar estado!", new Exception()));

        assertThrows(GatewayException.class, () -> gateway.cadastra(estadoDomain, ID));
    }

    private void iniciaPais() {
        paisDatabase = PaisDatabase.builder().id(1L).pais("Brasil").build();
    }

    private void iniciaEstado() {
        estadoDomain = EstadoDomain.builder().estado("São Paulo").build();
    }
}