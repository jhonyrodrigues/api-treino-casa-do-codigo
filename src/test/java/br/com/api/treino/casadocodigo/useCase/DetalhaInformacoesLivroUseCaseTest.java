package br.com.api.treino.casadocodigo.useCase;

import br.com.api.treino.casadocodigo.controller.model.DetalhaInformacoesLivroResponse;
import br.com.api.treino.casadocodigo.gateway.DetalhaInformacoesLivroGateway;
import br.com.api.treino.casadocodigo.gateway.database.adapter.LivroAdapter;
import br.com.api.treino.casadocodigo.model.AutorDomain;
import br.com.api.treino.casadocodigo.model.CategoriaDomain;
import br.com.api.treino.casadocodigo.model.LivroDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DetalhaInformacoesLivroUseCaseTest {

    private LivroDomain livroDomain;

    private DetalhaInformacoesLivroResponse informacoesLivroResponse;

    @InjectMocks
    private DetalhaInformacoesLivroUseCase useCase;

    @Mock
    private DetalhaInformacoesLivroGateway gateway;

    @Mock
    private LivroAdapter livroAdapter;

    private static final Long ID = 1L;
    private static final String TITULO = "Java";
    private static final String RESUMO = "Desenvolvimento com orientação a objetos";
    private static final String SUMARIO = "Desenvolvimento Backend com Java";
    private static final Integer PRECO = 2000;
    private static final Integer PAGINAS = 100;
    private static final String ISBN = "xpto2022";

    private static final String CATEGORIA = "Programação";
    private static final String NOME = "Jhony";

    private static final String EMAIL = "teste@teste.com";
    private static final String DESCRICAO = "Desenvolvedor Backend";
    private static final LocalDate DATA_LANCAMENTO = LocalDate.now();
    private static final LocalDateTime REGISTRADO_EM = LocalDateTime.now();

    @BeforeEach
    void setUp() {
        iniciaLivro();
    }

    @Test
    void deveDetalharLivroPeloId() {

        when(gateway.detalha(anyLong())).thenReturn(livroDomain);

        when(livroAdapter.converteLivroDomainParaDetalhaInformacoesLivroResponse(any())).thenReturn(informacoesLivroResponse);

        var response = useCase.detalha(ID);

        assertNotNull(response);
        assertEquals(DetalhaInformacoesLivroResponse.class, response.getClass());
        assertEquals(TITULO, response.getTitulo());
        assertEquals(PRECO, response.getPreco());
        assertEquals(RESUMO, response.getResumo());
        assertEquals(SUMARIO, response.getSumario());
        assertEquals(NOME, response.getNomeAutor());
        assertEquals(DESCRICAO, response.getDescricaoAutor());
        assertEquals(PAGINAS, response.getPaginas());
        assertEquals(ISBN, response.getIsbn());
        assertEquals(DATA_LANCAMENTO, response.getDataPublicacao());
    }

    private void iniciaLivro() {
        livroDomain = LivroDomain.builder().id(ID).titulo(TITULO).resumo(RESUMO).sumario(SUMARIO)
                .preco(PRECO).paginas(PAGINAS).isbn(ISBN).dataLancamento(DATA_LANCAMENTO).categoria(CategoriaDomain.builder().categoria(CATEGORIA).build())
                .autor(AutorDomain.builder().nome(NOME).email(EMAIL).descricao(DESCRICAO).registradoEm(REGISTRADO_EM)
                        .build()).build();
        informacoesLivroResponse = DetalhaInformacoesLivroResponse.builder().titulo(TITULO).preco(PRECO).resumo(RESUMO).sumario(SUMARIO).nomeAutor(NOME)
                .descricaoAutor(DESCRICAO).paginas(PAGINAS).isbn(ISBN).dataPublicacao(DATA_LANCAMENTO).build();
    }
}