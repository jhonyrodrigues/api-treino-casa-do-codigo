package br.com.api.treino.casadocodigo.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CadastraNovoAutorRequest {
    @NotBlank
    private String nome;
    @Email
    @NotBlank
    private String email;
    @Size(min = 10, max = 400)
    private String descricao;
}