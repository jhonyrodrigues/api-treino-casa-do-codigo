package br.com.api.treino.casadocodigo.controller;

import br.com.api.treino.casadocodigo.controller.model.CadastraNovoLivroRequest;
import br.com.api.treino.casadocodigo.controller.model.CadastraNovoLivroResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RequestMapping("/livro")
public interface CadastraNovoLivroApi {
    @PostMapping
    @ResponseStatus(CREATED)
    CadastraNovoLivroResponse cadastra(@RequestBody @Valid CadastraNovoLivroRequest request);
}