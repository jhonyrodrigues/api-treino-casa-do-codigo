package br.com.api.treino.casadocodigo.gateway.database;

import br.com.api.treino.casadocodigo.gateway.database.adapter.LivroAdapter;
import br.com.api.treino.casadocodigo.gateway.database.model.AutorDatabase;
import br.com.api.treino.casadocodigo.gateway.database.model.CategoriaDatabase;
import br.com.api.treino.casadocodigo.gateway.database.model.LivroDatabase;
import br.com.api.treino.casadocodigo.gateway.database.repository.LivroRepository;
import br.com.api.treino.casadocodigo.gateway.exception.NotFoundGatewayException;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DetalhaInformacoesLivroGatewayImplTest {

    private LivroDatabase livroDatabase;

    private LivroDomain livroDomain;

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

    @InjectMocks
    private DetalhaInformacoesLivroGatewayImpl gateway;

    @Mock
    private LivroRepository repository;

    @Mock
    private LivroAdapter livroAdapter;

    @BeforeEach
    void setUp() {
        inciaLivro();
    }

    @Test
    void deveBuscarLivroPeloId() {

        when(repository.findById(anyLong())).thenReturn(Optional.of(livroDatabase));

        when(livroAdapter.converteLivroDatabaseParaLivroDomain(any())).thenReturn(livroDomain);

        var response = gateway.detalha(ID);

        assertNotNull(response);
        assertEquals(LivroDomain.class, response.getClass());
        assertEquals(TITULO, response.getTitulo());
        assertEquals(PRECO, response.getPreco());
        assertEquals(RESUMO, response.getResumo());
        assertEquals(SUMARIO, response.getSumario());
        assertEquals(NOME, response.getAutor().getNome());
        assertEquals(DESCRICAO, response.getAutor().getDescricao());
        assertEquals(PAGINAS, response.getPaginas());
        assertEquals(ISBN, response.getIsbn());
        assertEquals(DATA_LANCAMENTO, response.getDataLancamento());
    }

    @Test
    void deveRetornarNotFoundGatewayException() {

        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundGatewayException.class, () -> gateway.detalha(ID));
    }

    private void inciaLivro() {
        livroDomain = LivroDomain.builder().id(ID).titulo(TITULO).resumo(RESUMO).sumario(SUMARIO)
                .preco(PRECO).paginas(PAGINAS).isbn(ISBN).dataLancamento(DATA_LANCAMENTO).categoria(CategoriaDomain.builder().categoria(CATEGORIA).build())
                .autor(AutorDomain.builder().nome(NOME).email(EMAIL).descricao(DESCRICAO).registradoEm(REGISTRADO_EM)
                        .build()).build();

        livroDatabase = LivroDatabase.builder().id(ID).titulo(TITULO).resumo(RESUMO).sumario(SUMARIO)
                .preco(PRECO).paginas(PAGINAS).isbn(ISBN).dataLancamento(DATA_LANCAMENTO).categoria(CategoriaDatabase.builder().categoria(CATEGORIA).build())
                .autor(AutorDatabase.builder().nome(NOME).email(EMAIL).descricao(DESCRICAO).registradoEm(REGISTRADO_EM)
                        .build()).build();
    }
}