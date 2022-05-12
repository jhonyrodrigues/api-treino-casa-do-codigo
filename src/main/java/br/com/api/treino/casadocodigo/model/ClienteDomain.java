package br.com.api.treino.casadocodigo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ClienteDomain {
    private Long id;
    private String email;
    private String nome;
    private String sobrenome;
    private String documento;
    private String endereco;
    private String complemento;
    private String cidade;
    private PaisDomain pais;
    private EstadoDomain estado;
    private String telefone;
    private String cep;
}