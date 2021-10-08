package br.dev.rodrigocury.repository;

import br.dev.rodrigocury.models.Funcionario;
import br.dev.rodrigocury.models.UnidadeTrabalho;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer> {
    List<Funcionario> findByNome(String nome);
    List<Funcionario> findByNomeLike(String nomeLike);


    @Query("SELECT f FROM Funcionario f " +
            "WHERE f.nome = :nome " +
            "AND f.salario >= :salario " +
            "AND f.dataContratacao = :data")
    List<Funcionario> findNomeDataCoratacaoSalarioMaior(
            @Param("nome") String nome,
            @Param("salario") Double salario,
            @Param("data") LocalDate data);

}
