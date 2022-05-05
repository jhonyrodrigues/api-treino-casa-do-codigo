package br.com.api.treino.casadocodigo.controller;

import br.com.api.treino.casadocodigo.controller.model.ListaLivrosCadastradosResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/livros")
public interface ListaLivrosCadastradosApi {
    @GetMapping
    List<ListaLivrosCadastradosResponse> lista();
}