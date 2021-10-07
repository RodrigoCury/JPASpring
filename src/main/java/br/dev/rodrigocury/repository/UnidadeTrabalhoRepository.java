package br.dev.rodrigocury.repository;

import br.dev.rodrigocury.models.Cargo;
import br.dev.rodrigocury.models.UnidadeTrabalho;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeTrabalhoRepository extends CrudRepository<UnidadeTrabalho, Integer> {

}
