package br.dev.rodrigocury.repository;

import br.dev.rodrigocury.models.Funcionario;
import br.dev.rodrigocury.models.UnidadeTrabalho;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer> {

}
