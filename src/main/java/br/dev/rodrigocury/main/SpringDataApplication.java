package br.dev.rodrigocury.main;

import br.dev.rodrigocury.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Scanner;


@SpringBootApplication
@EntityScan(basePackages = {"br.dev.rodrigocury.models"})
@ComponentScan(basePackages={"br.dev.rodrigocury.repository", "br.dev.rodrigocury.service"})
@EnableJpaRepositories(basePackages = {"br.dev.rodrigocury.repository"})
public class SpringDataApplication implements CommandLineRunner {

    private Boolean system = true;

    private final CrudCargoService cargoService;
    private final CrudFuncionarioService funcionarioService;
    private final CrudUnidadeService unidadeService;
    private final RelatoriosService relatoriosService;
    private final RelatorioFuncionarioDinamico relatorioFuncionarioDinamico;

    public SpringDataApplication(CrudCargoService service, CrudFuncionarioService funcionarioService, CrudUnidadeService unidadeService, RelatoriosService relatoriosService, RelatorioFuncionarioDinamico relatorioFuncionarioDinamico) {
        this.cargoService = service;
        this.funcionarioService = funcionarioService;
        this.unidadeService = unidadeService;
        this.relatoriosService = relatoriosService;
        this.relatorioFuncionarioDinamico = relatorioFuncionarioDinamico;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringDataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (system){
            System.out.println("Qual função deseja executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Cargo");
            System.out.println("2 - Funcionario");
            System.out.println("3 - Unidade");
            System.out.println("4 - Relatórios");
            System.out.println("5 - Relatorios dinamicos Funcionario");

            String escolha = scanner.next();

            switch (escolha) {
                case "0" -> {
                    System.out.println("Saindo!");
                    system = false;
                }
                case "1" -> cargoService.inicial(scanner);
                case "2" -> funcionarioService.inicial(scanner);
                case "3" -> unidadeService.inicial(scanner);
                case "4" -> relatoriosService.inicial(scanner);
                case "5" -> relatorioFuncionarioDinamico.iniciar(scanner);
                default -> System.out.println("Selecione uma opção válida");
            }
        }
    }
}
