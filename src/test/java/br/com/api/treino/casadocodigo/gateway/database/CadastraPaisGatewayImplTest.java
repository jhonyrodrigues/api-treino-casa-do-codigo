package br.com.api.treino.casadocodigo.gateway.database;

import br.com.api.treino.casadocodigo.gateway.database.repository.PaisRepository;
import br.com.api.treino.casadocodigo.gateway.exception.GatewayException;
import br.com.api.treino.casadocodigo.model.PaisDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CadastraPaisGatewayImplTest {

    @InjectMocks
    private CadastraPaisGatewayImpl gateway;

    @Mock
    private PaisRepository repository;

    private PaisDomain paisDomain;

    @BeforeEach
    void setUp() {
        iniciaPais();
    }

    @Test
    void deveCadastrarNovoPaisComSucesso() {
        assertDoesNotThrow(()-> gateway.cadastra(paisDomain));
    }

    @Test
    void deveRetornarErroDuplicateKeyException(){

        when(repository.save(any())).thenThrow(new DataIntegrityViolationException("[GATEWAY] - País já foi cadastrado!"));

        assertThrows(DuplicateKeyException.class, ()-> gateway.cadastra(paisDomain));
    }

    @Test
    void deveRetornarGatewayException(){

        when(repository.save(any())).thenThrow(new GatewayException("[GATEWAY] - Problema ao cadastrar país!", new Exception()));

        assertThrows(GatewayException.class, () -> gateway.cadastra(paisDomain));
    }

    private void iniciaPais() {
        paisDomain = PaisDomain.builder().pais("Brasil").build();
    }
}