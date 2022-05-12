package br.com.api.treino.casadocodigo.useCase;

import br.com.api.treino.casadocodigo.controller.model.CadastraNovoClienteRequest;
import br.com.api.treino.casadocodigo.controller.model.CadastraNovoClienteResponse;
import br.com.api.treino.casadocodigo.gateway.CadastraNovoClienteGateway;
import br.com.api.treino.casadocodigo.gateway.database.adapter.ClienteAdapter;
import br.com.api.treino.casadocodigo.model.ClienteDomain;
import br.com.api.treino.casadocodigo.model.EstadoDomain;
import br.com.api.treino.casadocodigo.model.PaisDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CadastraNovoClienteUseCaseTest {

    @InjectMocks
    private CadastraNovoClienteUseCase useCase;

    @Mock
    private CadastraNovoClienteGateway gateway;

    @Mock
    private ClienteAdapter clienteAdapter;

    private ClienteDomain clienteDomain;

    private PaisDomain paisDomain;

    private EstadoDomain estadoDomain;

    private CadastraNovoClienteRequest novoClienteRequest;

    private CadastraNovoClienteResponse novoClienteResponse;

    private static final Long ID = 1L;
    private static final String EMAIL = "teste@teste.com.br";
    private static final String NOME = "Jhony";
    private static final String SOBRENOME = "Rodrigues";
    private static final String DOCUMENTO = "12345678910";
    private static final String ENDERECO = "Rua das Laranjeiras";
    private static final String COMPLEMENTO = "Condomínio Jardins";
    private static final String CIDADE = "São Paulo";
    private static final int PAIS_ID = 1;
    private static final int ESTADO_ID = 1;
    private static final String ESTADO = "São Paulo";
    private static final String PAIS = "Brasil";
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

        when(gateway.cadastra(any(), any())).thenReturn(clienteDomain);

        when(clienteAdapter.converteClienteDomainParaClienteResponse(any())).thenReturn(novoClienteResponse);

        var response = useCase.cadastra(novoClienteRequest);

        assertNotNull(response);
        assertEquals(ID, response.getId());

    }

    private void iniciaPais() {
        paisDomain = PaisDomain.builder().pais(PAIS).build();
    }

    private void iniciaEstado() {
        estadoDomain = EstadoDomain.builder().estado(ESTADO).paisDomain(PaisDomain.builder().pais(PAIS).build()).build();
    }

    private void iniciaCliente() {
        clienteDomain = ClienteDomain.builder().id(ID).email(EMAIL).nome(NOME).sobrenome(SOBRENOME)
                .documento(DOCUMENTO).endereco(ENDERECO).complemento(COMPLEMENTO)
                .cidade(CIDADE).pais(paisDomain).estado(estadoDomain).telefone(TELEFONE)
                .cep(CEP).build();

        novoClienteRequest = CadastraNovoClienteRequest.builder().email(EMAIL).nome(NOME).sobrenome(SOBRENOME)
                .documento(DOCUMENTO).endereco(ENDERECO).complemento(COMPLEMENTO)
                .cidade(CIDADE).paisId(PAIS_ID).estadoId(ESTADO_ID).telefone(TELEFONE)
                .cep(CEP).build();

        novoClienteResponse = CadastraNovoClienteResponse.builder().id(ID).build();
    }
}