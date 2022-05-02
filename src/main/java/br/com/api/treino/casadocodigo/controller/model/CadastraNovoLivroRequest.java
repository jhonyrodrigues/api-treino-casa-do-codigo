package br.com.api.treino.casadocodigo.controller.model;

import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CadastraNovoLivroRequest {
    @NotBlank
    private String titulo;
    @NotBlank @Size(max = 500)
    private String resumo;
    private String sumario;
    @Min(20)
    private int preco;
    @Min(100)
    private int paginas;
    @NotBlank
    private String isbn;
    @Future
    private LocalDate dataLancamento;
    @NotNull
    private Integer idCategoria;
    @NonNull
    private Integer idAutor;
}