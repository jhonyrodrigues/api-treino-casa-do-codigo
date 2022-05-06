package br.com.api.treino.casadocodigo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class EstadoDomain {
    private String estado;
    private PaisDomain paisDomain;
}
