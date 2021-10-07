package br.dev.rodrigocury.repository;

import br.dev.rodrigocury.models.Funcionario;
import br.dev.rodrigocury.models.UnidadeTrabalho;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer> {
    List<Funcionario> findByNome(String nome);


}
