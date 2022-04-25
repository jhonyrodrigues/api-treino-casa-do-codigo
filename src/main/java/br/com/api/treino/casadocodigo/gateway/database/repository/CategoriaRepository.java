package br.com.api.treino.casadocodigo.gateway.database.repository;

import br.com.api.treino.casadocodigo.gateway.database.model.CategoriaDatabase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaDatabase, Long> {
}