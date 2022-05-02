package br.com.api.treino.casadocodigo.gateway.database;

import br.com.api.treino.casadocodigo.controller.model.CadastraNovoLivroRequest;
import br.com.api.treino.casadocodigo.gateway.database.adapter.LivroAdapter;
import br.com.api.treino.casadocodigo.gateway.database.model.AutorDatabase;
import br.com.api.treino.casadocodigo.gateway.database.model.CategoriaDatabase;
import br.com.api.treino.casadocodigo.gateway.database.model.LivroDatabase;
import br.com.api.treino.casadocodigo.gateway.database.repository.AutorRepository;
import br.com.api.treino.casadocodigo.gateway.database.repository.CategoriaRepository;
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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CadastraNovoLivroGatewayImplTest {

    private AutorDatabase autorDatabase;

    private CategoriaDatabase categoriaDatabase;

    private LivroDatabase livroDatabase;

    private LivroDomain livroDomain;

    private CadastraNovoLivroRequest novoLivroRequest;

    private final LocalDate dataLancamento = LocalDate.now().plusDays(1);

    @InjectMocks
    private CadastraNovoLivroGatewayImpl novoLivroGateway;

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private AutorRepository autorRepository;

    @Mock
    private LivroRepository livroRepository;

    @Mock
    private LivroAdapter livroAdapter;

    @BeforeEach
    void setUp() {
        iniciaCategoria();
        iniciaAutor();
        iniciaLivro();
    }

    @Test
    void deveCadastrarNovoLivroComRetornoDeSucesso() {

        when(categoriaRepository.findById(anyLong())).thenReturn(Optional.of(categoriaDatabase));

        when(autorRepository.findById(anyLong())).thenReturn(Optional.of(autorDatabase));

        when(livroAdapter.converteLivroDomainParaLivroDatabase(any(), any(), any())).thenReturn(livroDatabase);

        when(livroRepository.save(any())).thenReturn(livroDatabase);

        when(livroAdapter.converteLivroDatabaseParaLivroDomain(any())).thenReturn(livroDomain);

        var response = novoLivroGateway.cadastra(livroDomain, novoLivroRequest);

        assertNotNull(response);
        assertEquals(LivroDomain.class, response.getClass());
        assertEquals(1L, response.getId());
        assertEquals("Java", response.getTitulo());
        assertEquals("Desenvolvimento com orientação a objetos", response.getResumo());
        assertEquals("Desenvolvimento Backend com Java", response.getSumario());
        assertEquals(12000, response.getPreco());
        assertEquals(100, response.getPaginas());
        assertEquals("xpto", response.getIsbn());
        assertEquals(dataLancamento, response.getDataLancamento());
        assertEquals("Programação", response.getCategoria().getCategoria());
        assertEquals("Jhony", response.getAutor().getNome());
        assertEquals("teste@teste.com", response.getAutor().getEmail());
        assertEquals("Desenvolvedor Backend", response.getAutor().getDescricao());
    }

    @Test
    void deveRetornarDuplicateKeyException(){

        when(categoriaRepository.findById(anyLong())).thenReturn(Optional.of(categoriaDatabase));

        when(autorRepository.findById(anyLong())).thenReturn(Optional.of(autorDatabase));

        when(livroRepository.save(any())).thenThrow(new DataIntegrityViolationException("[GATEWAY] - Título já cadatrado em sistema!", new Exception()));

        try {
            novoLivroGateway.cadastra(livroDomain, novoLivroRequest);
        }catch (Exception ex) {
            assertEquals(DuplicateKeyException.class, ex.getClass());
            assertEquals("[GATEWAY] - Título: [Java] já cadastrado!", ex.getMessage());
        }
    }

    @Test
    void deveRetornarCategoriaNaoEncontrada() {

        when(categoriaRepository.findById(anyLong())).thenThrow(new NotFoundGatewayException("[GATEWAY] - Categoria não encontada!"));

        try {
            novoLivroGateway.cadastra(livroDomain, novoLivroRequest);
        }catch (Exception ex) {
            assertEquals(NotFoundGatewayException.class, ex.getClass());
            assertEquals("[GATEWAY] - Categoria não encontada!", ex.getMessage());
        }
    }

    @Test
    void deveRetornarAutorNaoEncontrado(){

        when(categoriaRepository.findById(anyLong())).thenReturn(Optional.of(categoriaDatabase));

        when(autorRepository.findById(anyLong())).thenThrow(new NotFoundGatewayException("[GATEWAY] - Autor não encontrado!"));

        try {
            novoLivroGateway.cadastra(livroDomain, novoLivroRequest);
        }catch (Exception ex){
            assertEquals(NotFoundGatewayException.class, ex.getClass());
            assertEquals("[GATEWAY] - Autor não encontrado!", ex.getMessage());
        }
    }

    private void iniciaAutor() {
        autorDatabase = AutorDatabase.builder().nome("Jhony").email("teste@teste.com.br").descricao("Desenvolvedor Backend")
                .registradoEm(LocalDateTime.now()).build();
    }

    private void iniciaCategoria() {
        categoriaDatabase = CategoriaDatabase.builder().categoria("Java").build();
    }

    private void iniciaLivro() {
        livroDomain = LivroDomain.builder().id(1L).titulo("Java").resumo("Desenvolvimento com orientação a objetos").sumario("Desenvolvimento Backend com Java")
                .preco(12000).paginas(100).isbn("xpto").dataLancamento(dataLancamento).categoria(CategoriaDomain.builder().categoria("Programação").build())
                .autor(AutorDomain.builder().nome("Jhony").email("teste@teste.com").descricao("Desenvolvedor Backend").registradoEm(LocalDateTime.now())
                        .build()).build();

        livroDatabase = LivroDatabase.builder().id(1L).titulo("Java").resumo("Desenvolvimento com orientação a objetos").sumario("Desenvolvimento Backend com Java")
                .preco(12000).paginas(100).isbn("xpto").dataLancamento(dataLancamento).categoria(CategoriaDatabase.builder().categoria("Programação").build())
                .autor(AutorDatabase.builder().nome("Jhony").email("teste@teste.com").descricao("Desenvolvedor Backend").registradoEm(LocalDateTime.now())
                        .build()).build();

        novoLivroRequest = CadastraNovoLivroRequest.builder().idCategoria(1).idAutor(1).build();
    }
}