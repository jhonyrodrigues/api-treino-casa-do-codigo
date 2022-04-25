package br.com.api.treino.casadocodigo.gateway.database;

import br.com.api.treino.casadocodigo.gateway.database.model.AutorDatabase;
import br.com.api.treino.casadocodigo.gateway.database.repository.AutorRepository;
import br.com.api.treino.casadocodigo.gateway.exception.CadastraNovoAutorGatewayException;
import br.com.api.treino.casadocodigo.model.AutorDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CadastraNovoAutorGatewayImplTest {

    @InjectMocks
    private CadastraNovoAutorGatewayImpl gateway;

    @Mock
    private AutorRepository repository;

    private AutorDomain autorDomain;

    private AutorDatabase autorDatabase = new AutorDatabase();

    private final LocalDateTime registradoEm = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));

    @BeforeEach
    void setup() {
        iniciaAutor();
    }

    @Test
    void deveCadastrarNovoAutorComRetornoDeSucesso() throws CadastraNovoAutorGatewayException {

        when(repository.save(any())).thenReturn(autorDatabase);

        var response = gateway.cadastra(autorDomain);

        assertNotNull(response);
        assertEquals("Jhony", response.getNome());
        assertEquals("teste@teste.com", response.getEmail());
        assertEquals("Desenvolvedor", response.getDescricao());
        assertEquals(registradoEm, response.getRegistradoEm());
    }

    @Test
    void deveRetornarDuplicateKeyException() {

        when(repository.save(any())).thenThrow(new DuplicateKeyException("[GATEWAY] - E-mail cadastrado!"));

        try {
            gateway.cadastra(autorDomain);
        } catch (Exception e) {
            assertEquals(DuplicateKeyException.class, e.getClass());
            assertEquals("[GATEWAY] - E-mail cadastrado!", e.getMessage());
        }
    }

    @Test
    void deveRetornarCadastraNovoAutorGatewayException() throws CadastraNovoAutorGatewayException {

        when(repository.save(any())).thenThrow(new CadastraNovoAutorGatewayException("[GATEWAY] - Problema ao cadastrar novo autor", new Exception()));

        try {
            gateway.cadastra(autorDomain);
        }catch (Exception e) {
            assertEquals(CadastraNovoAutorGatewayException.class, e.getClass());
            assertEquals("[GATEWAY] - Problema ao cadastrar novo autor", e.getMessage());
        }
    }

    void iniciaAutor() {
        autorDomain = new AutorDomain("Jhony", "teste@teste.com", "Desenvolvedor", registradoEm);
        autorDatabase = new AutorDatabase(1L, "Jhony", "teste@teste.com", "Desenvolvedor", registradoEm);
    }
}