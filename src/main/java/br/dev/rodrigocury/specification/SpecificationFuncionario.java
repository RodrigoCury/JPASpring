package br.dev.rodrigocury.specification;

import br.dev.rodrigocury.models.Funcionario;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class SpecificationFuncionario {

    public static Specification<Funcionario> nome(String nome) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
    }
    public static Specification<Funcionario> cpf(String cpf) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("cpf"), cpf);
    }
    public static Specification<Funcionario> salario(Double salario) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("salario"), salario);
    }
    public static Specification<Funcionario> dataContratacao(LocalDate data){
        return (root, query, criteriaBuilder) ->
            criteriaBuilder.greaterThan(root.get("dataContratacao"), data);
    }
}
