package br.com.app.persistence.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.app.persistence.entities.AgenteEntity;

@Repository
public interface AgenteRepository extends CrudRepository<AgenteEntity, Long> {

	List<AgenteEntity> findAllByCodigoIn(List<Integer> codigos);
}
