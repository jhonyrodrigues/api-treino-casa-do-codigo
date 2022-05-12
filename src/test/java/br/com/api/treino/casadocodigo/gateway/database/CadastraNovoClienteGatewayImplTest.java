package br.com.api.treino.casadocodigo.gateway.database;

import br.com.api.treino.casadocodigo.controller.model.CadastraNovoClienteRequest;
import br.com.api.treino.casadocodigo.gateway.database.adapter.ClienteAdapter;
import br.com.api.treino.casadocodigo.gateway.database.model.ClienteDatabase;
import br.com.api.treino.casadocodigo.gateway.database.model.EstadoDatabase;
import br.com.api.treino.casadocodigo.gateway.database.model.PaisDatabase;
import br.com.api.treino.casadocodigo.gateway.database.repository.ClienteRepository;
import br.com.api.treino.casadocodigo.gateway.database.repository.EstadoRepository;
import br.com.api.treino.casadocodigo.gateway.database.repository.PaisRepository;
import br.com.api.treino.casadocodigo.gateway.exception.GatewayException;
import br.com.api.treino.casadocodigo.model.ClienteDomain;
import br.com.api.treino.casadocodigo.model.EstadoDomain;
import br.com.api.treino.casadocodigo.model.PaisDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CadastraNovoClienteGatewayImplTest {

    @InjectMocks
    private CadastraNovoClienteGatewayImpl gateway;
    @Mock
    private ClienteRepository clienteRepository;
    @Mock
    private PaisRepository paisRepository;
    @Mock
    private EstadoRepository estadoRepository;
    @Mock
    private ClienteAdapter clienteAdapter;

    private ClienteDomain clienteDomain;

    private PaisDomain paisDomain;

    private EstadoDomain estadoDomain;

    private ClienteDatabase clienteDatabase;

    private EstadoDatabase estadoDatabase;

    private PaisDatabase paisDatabase;

    private CadastraNovoClienteRequest novoClienteRequest;

    private static final String EMAIL = "teste@teste.com.br";
    private static final String NOME = "Jhony";
    private static final String SOBRENOME = "Rodrigues";
    private static final String DOCUMENTO = "12345678910";
    private static final String ENDERECO = "Rua das Laranjeiras";
    private static final String COMPLEMENTO = "Condomínio Jardins";
    private static final String CIDADE = "São Paulo";
    private static final String PAIS = "Brasil";
    private static final String ESTADO = "SP";
    private static final String TELEFONE = "1199999-9999";
    private static final String CEP = "12345-678";

    @BeforeEach
    void setUp() {
        iniciaPais();
        iniciaEstado();
        iniciaCliente();
    }

    @Test
    void deveCadastrarNovoClienteComSucesso() {

        when(paisRepository.findById(anyLong())).thenReturn(Optional.of(paisDatabase));

        when(estadoRepository.findById(anyLong())).thenReturn(Optional.of(estadoDatabase));

        when(clienteRepository.save(any())).thenReturn(clienteDatabase);

        when(clienteAdapter.converteClienteDatabaseParaClienteDomain(any())).thenReturn(clienteDomain);

        var response = gateway.cadastra(clienteDomain, CadastraNovoClienteRequest.builder().paisId(1).estadoId(1).build());

        assertNotNull(response);
        assertEquals(EMAIL, response.getEmail());
        assertEquals(NOME, response.getNome());
        assertEquals(SOBRENOME, response.getSobrenome());
        assertEquals(DOCUMENTO, response.getDocumento());
        assertEquals(ENDERECO, response.getEndereco());
        assertEquals(COMPLEMENTO, response.getComplemento());
        assertEquals(CIDADE, response.getCidade());
        assertEquals(PAIS, response.getPais().getPais());
        assertEquals(ESTADO, response.getEstado().getEstado());
        assertEquals(TELEFONE, response.getTelefone());
        assertEquals(CEP, response.getCep());
    }

    @Test
    void deveRetornarDuplicateKeyException() {

        when(paisRepository.findById(anyLong())).thenReturn(Optional.of(paisDatabase));

        when(estadoRepository.findById(anyLong())).thenReturn(Optional.of(estadoDatabase));

        when(clienteRepository.save(any())).thenThrow(new DataIntegrityViolationException("[GATEWAY] - Email já cadastrado!"));

        assertThrows(DuplicateKeyException.class, () -> gateway.cadastra(clienteDomain, novoClienteRequest));
    }

    @Test
    void deveRetornarGatewayException() {

        when(paisRepository.findById(anyLong())).thenReturn(Optional.of(paisDatabase));

        when(estadoRepository.findById(anyLong())).thenReturn(Optional.of(estadoDatabase));

        when(clienteRepository.save(any())).thenThrow(new GatewayException("[GATEWAY] - Problema ao cadastrar novo cliente!", new Exception()));

        assertThrows(GatewayException.class, () -> gateway.cadastra(clienteDomain, novoClienteRequest));
    }

    private void iniciaPais() {
        paisDomain = PaisDomain.builder().pais("Brasil").build();
        paisDatabase = PaisDatabase.builder().id(1L).pais("Brasil").build();
    }

    private void iniciaEstado() {
        estadoDomain = EstadoDomain.builder().estado("SP").paisDomain(PaisDomain.builder().pais("Brasil").build()).build();
        estadoDatabase = EstadoDatabase.builder().id(1L).estado("SP").paisDatabase(PaisDatabase.builder().pais("Brasil").build()).build();
    }

    private void iniciaCliente() {
        clienteDomain = ClienteDomain.builder().email(EMAIL).nome(NOME).sobrenome(SOBRENOME)
                .documento(DOCUMENTO).endereco(ENDERECO).complemento(COMPLEMENTO)
                .cidade(CIDADE).pais(paisDomain).estado(estadoDomain).telefone(TELEFONE)
                .cep(CEP).build();

        clienteDatabase = ClienteDatabase.builder().email(EMAIL).nome(NOME).sobrenome(SOBRENOME)
                .documento(DOCUMENTO).endereco(ENDERECO).complemento(COMPLEMENTO)
                .cidade(CIDADE).pais(paisDatabase).estado(estadoDatabase).telefone(TELEFONE)
                .cep(CEP).build();

        novoClienteRequest = CadastraNovoClienteRequest.builder().email(EMAIL).nome(NOME).sobrenome(SOBRENOME)
                .documento(DOCUMENTO).endereco(ENDERECO).complemento(COMPLEMENTO)
                .cidade(CIDADE).paisId(1).estadoId(1).telefone(TELEFONE)
                .cep(CEP).build();
    }
}