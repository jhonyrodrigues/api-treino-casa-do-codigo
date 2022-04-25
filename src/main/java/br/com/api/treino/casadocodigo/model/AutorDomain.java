package br.com.api.treino.casadocodigo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class AutorDomain {
    private String nome;
    private String email;
    private String descricao;
    private LocalDateTime registradoEm;
}