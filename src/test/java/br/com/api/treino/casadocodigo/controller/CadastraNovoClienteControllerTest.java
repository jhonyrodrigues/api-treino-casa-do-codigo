package br.com.api.treino.casadocodigo.controller;

import br.com.api.treino.casadocodigo.controller.model.CadastraNovoClienteRequest;
import br.com.api.treino.casadocodigo.controller.model.CadastraNovoClienteResponse;
import br.com.api.treino.casadocodigo.useCase.CadastraNovoClienteUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CadastraNovoClienteControllerTest {

    @InjectMocks
    private CadastraNovoClienteController controller;

    @Mock
    private CadastraNovoClienteUseCase useCase;

    private CadastraNovoClienteRequest novoClienteRequest;

    private CadastraNovoClienteResponse novoClienteResponse;

    private static final String EMAIL = "teste@teste.com.br";
    private static final String NOME = "Jhony";
    private static final String SOBRENOME = "Rodrigues";
    private static final String DOCUMENTO = "12345678910";
    private static final String ENDERECO = "Rua das Laranjeiras";
    private static final String COMPLEMENTO = "Condomínio Jardins";
    private static final String CIDADE = "São Paulo";
    private static final int PAIS = 1;
    private static final int ESTADO = 1;
    private static final String TELEFONE = "1199999-9999";
    private static final String CEP = "12345-678";

    @BeforeEach
    void setUp() {
        iniciaCliente();
    }

    @Test
    void deveCadastrarNovoClienteComSucesso() {

        when(useCase.cadastra(any())).thenReturn(novoClienteResponse);

        assertDoesNotThrow(() -> controller.cadastra(novoClienteRequest));
    }

    private void iniciaCliente() {
        novoClienteRequest = CadastraNovoClienteRequest.builder().email(EMAIL).nome(NOME).sobrenome(SOBRENOME)
                .documento(DOCUMENTO).endereco(ENDERECO).complemento(COMPLEMENTO)
                .cidade(CIDADE).paisId(PAIS).estadoId(ESTADO).telefone(TELEFONE)
                .cep(CEP).build();

        novoClienteResponse = CadastraNovoClienteResponse.builder().id(1L).build();
    }
}