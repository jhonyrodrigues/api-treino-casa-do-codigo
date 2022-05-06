package br.com.api.treino.casadocodigo.gateway.database.adapter;

import br.com.api.treino.casadocodigo.controller.model.CadastraNovoLivroRequest;
import br.com.api.treino.casadocodigo.controller.model.CadastraNovoLivroResponse;
import br.com.api.treino.casadocodigo.controller.model.DetalhaInformacoesLivroResponse;
import br.com.api.treino.casadocodigo.controller.model.ListaLivrosCadastradosResponse;
import br.com.api.treino.casadocodigo.gateway.database.model.AutorDatabase;
import br.com.api.treino.casadocodigo.gateway.database.model.CategoriaDatabase;
import br.com.api.treino.casadocodigo.gateway.database.model.LivroDatabase;
import br.com.api.treino.casadocodigo.model.AutorDomain;
import br.com.api.treino.casadocodigo.model.CategoriaDomain;
import br.com.api.treino.casadocodigo.model.LivroDomain;
import org.springframework.stereotype.Service;

@Service
public class LivroAdapter {
    public LivroDatabase converteLivroDomainParaLivroDatabase(LivroDomain livroDomain, CategoriaDatabase categoriaDatabase, AutorDatabase autorDatabase) {
        return LivroDatabase.builder().titulo(livroDomain.getTitulo())
                .resumo(livroDomain.getResumo()).sumario(livroDomain.getSumario()).preco(livroDomain.getPreco())
                .paginas(livroDomain.getPaginas()).isbn(livroDomain.getIsbn()).dataLancamento(livroDomain.getDataLancamento())
                .categoria(categoriaDatabase).autor(autorDatabase).build();
    }

    public LivroDomain converteLivroDatabaseParaLivroDomain(LivroDatabase livroDatabase) {
        return LivroDomain.builder().id(livroDatabase.getId()).titulo(livroDatabase.getTitulo()).resumo(livroDatabase.getResumo())
                .sumario(livroDatabase.getSumario()).preco(livroDatabase.getPreco()).paginas(livroDatabase.getPaginas())
                .isbn(livroDatabase.getIsbn()).dataLancamento(livroDatabase.getDataLancamento())
                .categoria(CategoriaDomain.builder().categoria(livroDatabase.getCategoria().getCategoria()).build())
                .autor(AutorDomain.builder().nome(livroDatabase.getAutor().getNome()).email(livroDatabase.getAutor()
                        .getEmail()).descricao(livroDatabase.getAutor().getDescricao()).build()).build();
    }

    public LivroDomain converteLivroRequestParaLivroDomain(CadastraNovoLivroRequest request) {
        return LivroDomain.builder().titulo(request.getTitulo())
                .resumo(request.getResumo()).sumario(request.getSumario()).preco(request.getPreco())
                .paginas(request.getPaginas()).isbn(request.getIsbn())
                .dataLancamento(request.getDataLancamento()).build();
    }

    public CadastraNovoLivroResponse converteLivroDomainParaLivroResponse(LivroDomain livroDomain) {
        return CadastraNovoLivroResponse.builder().titulo(livroDomain.getTitulo()).autor(livroDomain.getAutor().getNome())
                .dataLancamento(livroDomain.getDataLancamento()).build();
    }

    public ListaLivrosCadastradosResponse converteLivroDomainParaListaDeLivrosCadastradosResponse(LivroDomain livroDomain) {
        return ListaLivrosCadastradosResponse.builder().id(livroDomain.getId()).titulo(livroDomain.getTitulo()).build();
    }

    public DetalhaInformacoesLivroResponse converteLivroDomainParaDetalhaInformacoesLivroResponse(LivroDomain livroDomain) {
        return DetalhaInformacoesLivroResponse.builder().titulo(livroDomain.getTitulo()).preco(livroDomain.getPreco())
                .resumo(livroDomain.getResumo()).sumario(livroDomain.getSumario()).nomeAutor(livroDomain.getAutor().getNome())
                .descricaoAutor(livroDomain.getAutor().getDescricao()).paginas(livroDomain.getPaginas())
                .isbn(livroDomain.getIsbn()).dataPublicacao(livroDomain.getDataLancamento())
                .build();
    }
}