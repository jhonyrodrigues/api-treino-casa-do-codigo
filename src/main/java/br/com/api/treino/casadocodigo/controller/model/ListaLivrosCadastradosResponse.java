package br.com.api.treino.casadocodigo.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ListaLivrosCadastradosResponse {
    private Long id;
    private String titulo;
}