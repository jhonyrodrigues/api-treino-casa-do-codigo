package br.com.api.treino.casadocodigo.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
public class CadastraEstadoRequest {
    @NotBlank
    private String estado;

    @NotNull
    private Integer paisId;
}
