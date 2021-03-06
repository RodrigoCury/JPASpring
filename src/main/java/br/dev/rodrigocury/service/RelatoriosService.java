package br.dev.rodrigocury.service;

import br.dev.rodrigocury.models.Funcionario;
import br.dev.rodrigocury.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
            System.out.println("3 - Busca Funcionarios Por Nomes; Data Contratação ; Salario ");
            System.out.println("4 - Busca Funcionarios Por Data Contratação");
            System.out.println("5 - Busca Funcionarios por maior salario");
            String opcao = scanner.next();
            switch (opcao){
                case "0" -> system = false;
                case "1" -> buscaFuncionarioPorNome(scanner);
                case "2" -> buscaFuncionarioPorNomeLike(scanner);
                case "3" -> buscaFuncionarioPorNomeSalarioEData(scanner);
                case "4" -> buscaFuncionarioPorData(scanner);
                case "5" -> listarPorSalario(scanner);
                default -> System.out.println("Nenhuma opção Valida");
            }
        }
    }

    private void listarPorSalario(Scanner scanner) {
        try {
            funcionarioRepository.findFuncionarioSalario().forEach(funcionarioProjection -> {
                System.out.println(String.format(
                    "Nome: %s - id: %d - Salario: %s",
                        funcionarioProjection.getNome(),
                        funcionarioProjection.getId(),
                        funcionarioProjection.getSalario()
                ));
            });
        } catch (Exception e){
            System.out.println("Houve um erro! Tntar novamente? (s/n)");
            String escolha = scanner.next();
            if(escolha.equals("s")){
                listarPorSalario(scanner);
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

    private void buscaFuncionarioPorNomeSalarioEData(Scanner scanner){
        try {
            System.out.println("Qual o Nome que quer Encontrar? ");
            String nome = scanner.next();
            System.out.println("Qual o Salario que quer Encontrar? ");
            Double salario = scanner.nextDouble();
            System.out.println("Qual a Data de Entrada?");
            System.out.println("Qual o Ano");
            int ano = scanner.nextInt();
            System.out.println("Qual o Mês(1-12)?");
            int mes = scanner.nextInt();
            System.out.println("Qual o dia?");
            int dia = scanner.nextInt();
            LocalDate data = LocalDate.of(ano, mes, dia);
            List<Funcionario> funcionariosEncontrados = funcionarioRepository.findNomeDataCoratacaoSalarioMaior(nome, salario, data);
            funcionariosEncontrados.forEach(System.out::println);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Houve um erro! Tentar novamente (s/n)");
            String tentarNovamente = scanner.next();
            if (tentarNovamente.equals("s")){
                buscaFuncionarioPorNomeSalarioEData(scanner);
            }
        }

    }

    private void buscaFuncionarioPorData(Scanner scanner){
        try {
            System.out.println("Qual a Data de Entrada?");
            System.out.println("Qual o Ano");
            int ano = scanner.nextInt();
            System.out.println("Qual o Mês(1-12)?");
            int mes = scanner.nextInt();
            System.out.println("Qual o dia?");
            int dia = scanner.nextInt();
            LocalDate data = LocalDate.of(ano, mes, dia);
            List<Funcionario> funcionariosEncontrados = funcionarioRepository.findDataContratacaoMaior(data);
            funcionariosEncontrados.forEach(System.out::println);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Houve um erro! Tentar novamente (s/n)");
            String tentarNovamente = scanner.next();
            if (tentarNovamente.equals("s")){
                buscaFuncionarioPorData(scanner);
            }
        }
    }
}
