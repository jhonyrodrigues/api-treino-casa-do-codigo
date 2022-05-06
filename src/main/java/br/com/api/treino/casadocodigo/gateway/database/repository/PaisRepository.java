package br.com.api.treino.casadocodigo.gateway.database.repository;

import br.com.api.treino.casadocodigo.gateway.database.model.PaisDatabase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepository extends JpaRepository<PaisDatabase, Long> {
}