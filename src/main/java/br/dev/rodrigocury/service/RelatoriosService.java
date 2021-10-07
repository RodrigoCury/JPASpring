package br.dev.rodrigocury.service;

import br.dev.rodrigocury.models.Funcionario;
import br.dev.rodrigocury.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class RelatoriosService {

    private Boolean system = true;

    private final FuncionarioRepository funcionarioRepository;

    public RelatoriosService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scanner){
        while (system) {
            System.out.println("Cargo Selecionado: ");
            System.out.println("O que Deseja Fazer: ");
            System.out.println("0 - Sair");
            System.out.println("1 - Busca Funcionarios Por Nome");
            System.out.println("2 - Busca Funcionarios Por Nomes Parecidos ");
            String opcao = scanner.next();
            switch (opcao){
                case "0" -> system = false;
                case "1" -> buscaFuncionarioPorNome(scanner);
                case "2" -> buscaFuncionarioPorNomeLike(scanner);
                default -> System.out.println("Nenhuma opção Valida");
            }
        }
    }

    private void buscaFuncionarioPorNome(Scanner scanner) {
        System.out.println("Qual o Nome do Funcionario: ");
        String nome = scanner.next();

        List<Funcionario> lista = funcionarioRepository.findByNome(nome);
        lista.forEach(System.out::println);
    }

    private void buscaFuncionarioPorNomeLike(Scanner scanner) {
        System.out.println("Qual o Nome do Funcionario: ");
        String nome = "%" + scanner.next() + "%";

        List<Funcionario> lista = funcionarioRepository.findByNomeLike(nome);
        lista.forEach(System.out::println);
    }
}
