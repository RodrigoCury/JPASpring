package br.dev.rodrigocury.service;

import br.dev.rodrigocury.models.Funcionario;
import br.dev.rodrigocury.repository.FuncionarioRepository;
import br.dev.rodrigocury.specification.SpecificationFuncionario;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class RelatorioFuncionarioDinamico {

    private final FuncionarioRepository funcionarioRepository;

    public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void iniciar(Scanner scanner) {
        System.out.println("Qual o Nome que quer pesquisar? ");
        String nome = scanner.next();

        if(nome.equalsIgnoreCase("NULL"))
            nome = null;

        System.out.println("Qual o cpf que quer pesquisar? ");
        String cpf = scanner.next();

        if(cpf.equalsIgnoreCase("NULL"))
            cpf = null;

        System.out.println("Qual o Salario que quer pesquisar? ");
        Double salario = scanner.nextDouble();

        if(salario.equals(0.0))
            salario = null;

        List<Funcionario> funcionarios = funcionarioRepository.findAll(
                Specification.where(SpecificationFuncionario.nome(nome)
                        .or(SpecificationFuncionario.cpf(cpf))
                        .or(SpecificationFuncionario.salario(salario))
                ));

        funcionarios.forEach(System.out::println);
    }
}
