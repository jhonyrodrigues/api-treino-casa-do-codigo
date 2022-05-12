package br.com.api.treino.casadocodigo.controller;

import br.com.api.treino.casadocodigo.controller.model.CadastraNovoClienteRequest;
import br.com.api.treino.casadocodigo.controller.model.CadastraNovoClienteResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RequestMapping("/cliente")
public interface CadastraNovoClienteApi {
    @PostMapping
    @ResponseStatus(CREATED)
    CadastraNovoClienteResponse cadastra(@RequestBody @Valid CadastraNovoClienteRequest request);
}