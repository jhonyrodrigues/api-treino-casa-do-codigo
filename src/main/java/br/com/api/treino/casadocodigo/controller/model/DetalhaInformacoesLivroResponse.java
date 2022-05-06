package br.com.api.treino.casadocodigo.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class DetalhaInformacoesLivroResponse {
    private String titulo;
    private Integer preco;
    private String resumo;
    private String sumario;
    private String nomeAutor;
    private String descricaoAutor;
    private int paginas;
    private String isbn;
    private LocalDate dataPublicacao;
}