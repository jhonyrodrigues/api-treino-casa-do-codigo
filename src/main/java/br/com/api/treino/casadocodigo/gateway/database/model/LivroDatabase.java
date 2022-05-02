package br.com.api.treino.casadocodigo.gateway.database.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LivroDatabase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private String resumo;
    private String sumario;
    private int preco;
    private int paginas;
    private String isbn;
    private LocalDate dataLancamento;
    @ManyToOne
    private CategoriaDatabase categoria;
    @ManyToOne
    private AutorDatabase autor;
}