package br.com.api.treino.casadocodigo.useCase;

import br.com.api.treino.casadocodigo.controller.model.CadastraNovoLivroRequest;
import br.com.api.treino.casadocodigo.controller.model.CadastraNovoLivroResponse;
import br.com.api.treino.casadocodigo.gateway.CadastraNovoLivroGateway;
import br.com.api.treino.casadocodigo.gateway.database.adapter.LivroAdapter;
import br.com.api.treino.casadocodigo.model.AutorDomain;
import br.com.api.treino.casadocodigo.model.CategoriaDomain;
import br.com.api.treino.casadocodigo.model.LivroDomain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CadastraNovoLivroUseCaseTest {
    private LivroDomain livroDomain;
    private CadastraNovoLivroRequest novoLivroRequest;

    private CadastraNovoLivroResponse novoLivroResponse;
    private final LocalDate dataLancamento = LocalDate.now().plusDays(1);
    @InjectMocks
    private CadastraNovoLivroUseCase useCase;
    @Mock
    private CadastraNovoLivroGateway gateway;

    @Mock
    private LivroAdapter livroAdapter;

    @BeforeEach
    void setUp() {
        iniciaLivro();
    }

    @Test
    void deveCadastrarUmNovoLivroComRetornoDeSucesso() {

        when(livroAdapter.converteLivroRequestParaLivroDomain(any())).thenReturn(livroDomain);

        when(gateway.cadastra(any(), any())).thenReturn(livroDomain);

        when(livroAdapter.converteLivroDomainParaLivroResponse(any())).thenReturn(novoLivroResponse);

        var response = useCase.cadastra(novoLivroRequest);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(CadastraNovoLivroResponse.class, response.getClass());
        Assertions.assertEquals("Java", response.getTitulo());
        Assertions.assertEquals("Jhony", response.getAutor());
        Assertions.assertEquals(dataLancamento, response.getDataLancamento());
    }

    private void iniciaLivro() {
        livroDomain = LivroDomain.builder().id(1L).titulo("Java").resumo("Desenvolvimento com orientação a objetos").sumario("Desenvolvimento Backend com Java")
                .preco(12000).paginas(100).isbn("xpto").dataLancamento(dataLancamento).categoria(CategoriaDomain.builder().categoria("Programação").build())
                .autor(AutorDomain.builder().nome("Jhony").email("teste@teste.com").descricao("Desenvolvedor Backend").registradoEm(LocalDateTime.now())
                        .build()).build();

        novoLivroRequest = CadastraNovoLivroRequest.builder().titulo("Java").resumo("Desenvolvimento com orientação a objetos")
                .sumario("Desenvolvimento Backend com Java").preco(1200).paginas(100).isbn("xpto2022").dataLancamento(dataLancamento)
                .idCategoria(1).idAutor(1).build();

        novoLivroResponse = CadastraNovoLivroResponse.builder().titulo("Java").autor("Jhony").dataLancamento(dataLancamento).build();
    }
}