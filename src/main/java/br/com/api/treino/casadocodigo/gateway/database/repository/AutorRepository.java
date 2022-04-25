package br.com.api.treino.casadocodigo.gateway.database.repository;

import br.com.api.treino.casadocodigo.gateway.database.model.AutorDatabase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<AutorDatabase, Long> {
    Optional<AutorDatabase> findByEmail(String email);
}
