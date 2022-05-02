package br.com.api.treino.casadocodigo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
@Getter
@Builder
@AllArgsConstructor
public class LivroDomain {
    private Long id;
    private String titulo;
    private String resumo;
    private String sumario;
    private int preco;
    private int paginas;
    private String isbn;
    private LocalDate dataLancamento;
    private CategoriaDomain categoria;
    private AutorDomain autor;
}