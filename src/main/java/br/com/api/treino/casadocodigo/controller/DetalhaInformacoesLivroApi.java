package br.com.api.treino.casadocodigo.controller;

import br.com.api.treino.casadocodigo.controller.model.DetalhaInformacoesLivroResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/livro")
public interface DetalhaInformacoesLivroApi {

    @GetMapping("/{id}")
    DetalhaInformacoesLivroResponse detalha(@PathVariable Long id);
}
