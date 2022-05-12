package br.com.api.treino.casadocodigo.gateway.database.repository;

import br.com.api.treino.casadocodigo.gateway.database.model.ClienteDatabase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteDatabase, Long> {
}