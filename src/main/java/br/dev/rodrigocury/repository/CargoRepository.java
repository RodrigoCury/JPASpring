package br.dev.rodrigocury.repository;

import br.dev.rodrigocury.models.Cargo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoRepository extends CrudRepository<Cargo, Integer> {

}
