package br.com.api.treino.casadocodigo.controller;

import br.com.api.treino.casadocodigo.controller.model.DetalhaInformacoesLivroResponse;
import br.com.api.treino.casadocodigo.useCase.DetalhaInformacoesLivroUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DetalhaInformacoesLivroControllerTest {

    private DetalhaInformacoesLivroResponse informacoesLivroResponse;

    @InjectMocks
    private DetalhaInformacoesLivroController controller;

    @Mock
    private DetalhaInformacoesLivroUseCase useCase;

    private static final Long ID = 1L;
    private static final String TITULO = "Java";
    private static final String RESUMO = "Desenvolvimento com orientação a objetos";
    private static final String SUMARIO = "Desenvolvimento Backend com Java";
    private static final Integer PRECO = 2000;
    private static final Integer PAGINAS = 100;
    private static final String ISBN = "xpto2022";
    private static final String NOME = "Jhony";
    private static final String DESCRICAO = "Desenvolvedor Backend";
    private static final LocalDate DATA_LANCAMENTO = LocalDate.now();

    @BeforeEach
    void setUp() {
        iniciaDetalhesLivro();
    }

    @Test
    void deveDetalharLivroPeloIdComSucesso() {

        when(useCase.detalha(anyLong())).thenReturn(informacoesLivroResponse);

        var response = controller.detalha(ID);

        assertNotNull(response);
        assertDoesNotThrow(() -> controller.detalha(ID));
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

    private void iniciaDetalhesLivro() {
        informacoesLivroResponse = DetalhaInformacoesLivroResponse.builder().titulo(TITULO).preco(PRECO).resumo(RESUMO).sumario(SUMARIO).nomeAutor(NOME)
                .descricaoAutor(DESCRICAO).paginas(PAGINAS).isbn(ISBN).dataPublicacao(DATA_LANCAMENTO).build();
    }
}