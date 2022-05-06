package br.com.api.treino.casadocodigo.gateway.database.repository;

import br.com.api.treino.casadocodigo.gateway.database.model.EstadoDatabase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<EstadoDatabase, Long> {
}