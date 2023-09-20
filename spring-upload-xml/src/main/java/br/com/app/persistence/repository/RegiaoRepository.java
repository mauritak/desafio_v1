package br.com.app.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.app.persistence.entities.RegiaoEntity;

@Repository
public interface RegiaoRepository extends JpaRepository<RegiaoEntity, Long> {
	
	 List<RegiaoEntity> findBySigla(String sigla);
}
