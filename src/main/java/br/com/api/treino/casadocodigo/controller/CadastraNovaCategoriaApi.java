package br.com.api.treino.casadocodigo.controller;

import br.com.api.treino.casadocodigo.controller.model.CadastraNovaCategoriaRequest;
import br.com.api.treino.casadocodigo.controller.model.CadastraNovaCategoriaResponse;
import br.com.api.treino.casadocodigo.gateway.exception.GatewayException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RequestMapping("/categoria")
public interface CadastraNovaCategoriaApi {
    @PostMapping
    @ResponseStatus(CREATED)
    CadastraNovaCategoriaResponse cadastra(@RequestBody @Valid CadastraNovaCategoriaRequest request) throws GatewayException;
}