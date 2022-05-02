package br.com.api.treino.casadocodigo.controller;

import br.com.api.treino.casadocodigo.controller.model.CadastraNovoAutorRequest;
import br.com.api.treino.casadocodigo.controller.model.CadastraNovoAutorResponse;
import br.com.api.treino.casadocodigo.gateway.exception.GatewayException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RequestMapping("/autor")
public interface CadastraNovoAutorApi {
    @PostMapping
    @ResponseStatus(CREATED)
    CadastraNovoAutorResponse cadastra(@RequestBody @Valid CadastraNovoAutorRequest request) throws GatewayException;
}