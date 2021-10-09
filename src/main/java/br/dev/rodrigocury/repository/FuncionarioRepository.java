package br.dev.rodrigocury.repository;

import br.dev.rodrigocury.models.Funcionario;
import br.dev.rodrigocury.models.FuncionarioProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer>, JpaSpecificationExecutor<Funcionario> {
    List<Funcionario> findByNome(String nome);
    List<Funcionario> findByNome(String nome, Pageable pageable);
    List<Funcionario> findByNomeLike(String nomeLike);
    List<Funcionario> findByNomeLike(String nomeLike, Pageable pageable);


    @Query("SELECT f FROM Funcionario f " +
            "WHERE f.nome = :nome " +
            "AND f.salario >= :salario " +
            "AND f.dataContratacao = :data")
    List<Funcionario> findNomeDataCoratacaoSalarioMaior(
            @Param("nome") String nome,
            @Param("salario") Double salario,
            @Param("data") LocalDate data);

    @Query(value = "SELECT * FROM funcionarios f WHERE f.data_contratacao >= :data", nativeQuery = true)
    List<Funcionario> findDataContratacaoMaior(
            @Param("data") LocalDate data);

    @Query(value = "SELECT f.id, f.nome, f.salario FROM funcionarios f ORDER BY f.salario DESC", nativeQuery = true)
    List<FuncionarioProjection> findFuncionarioSalario();
}
