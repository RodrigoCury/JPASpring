package br.dev.rodrigocury.service;

import br.dev.rodrigocury.models.Cargo;
import br.dev.rodrigocury.models.Funcionario;
import br.dev.rodrigocury.models.UnidadeTrabalho;
import br.dev.rodrigocury.repository.CargoRepository;
import br.dev.rodrigocury.repository.FuncionarioRepository;
import br.dev.rodrigocury.repository.UnidadeTrabalhoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;

@Component
@Service
public class CrudFuncionarioService {
    private Boolean system = true;

    private final FuncionarioRepository funcionarioRepository;
    private final CargoRepository cargoRepository;
    private final UnidadeTrabalhoRepository unidadeTrabalhoRepository;

    public CrudFuncionarioService(FuncionarioRepository funcionarioRepository, CargoRepository cargoRepository, UnidadeTrabalhoRepository unidadeTrabalhoRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.cargoRepository = cargoRepository;
        this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
    }

    public void inicial(Scanner scanner){
        while (system) {
            System.out.println("Funcionario Selecionado: ");
            System.out.println("O que Deseja Fazer: ");
            System.out.println("0 - Sair");
            System.out.println("1 - Salvar");
            System.out.println("2 - Atualizar");
            System.out.println("3 - Deletar");
            System.out.println("4 - Listar DB");
            System.out.println("5 - Listar por Ids");
            String opcao = scanner.next();
            switch (opcao){
                case "0" -> system = false;
                case "1" -> salvar(scanner);
                case "2" -> atualizar(scanner);
                case "3" -> deletar(scanner);
                case "4" -> listar(scanner);
                case "5" -> listarPorIds(scanner);
                default -> System.out.println("Nenhuma op????o Valida");
            }
        }
    }

    private void listarPorIds(Scanner scanner) {
        ArrayList<Integer> ids = new ArrayList<>();

        while(true){
            System.out.println("Digite o id(0 para sair): ");
            try {
                Integer id = scanner.nextInt();
                if (id.equals(0)){
                    break;
                }
                ids.add(id);
            } catch (Exception e){
                System.out.println("Entrada Inv??lida");
            }
        }
        Iterable<Funcionario> funcionarios = funcionarioRepository.findAllById(ids);
        funcionarios.forEach(System.out::println);
    }

    public void salvar(Scanner scanner){
        System.out.print("Nome do Funcionario: ");
        String nome = scanner.next();
        System.out.print("CPF do Funcionario: ");
        String cpf = scanner.next();
        System.out.print("Salario do Funcionario: ");
        Double salario = scanner.nextDouble();
        LocalDate dataContratacao = LocalDate.now();
        Cargo cargo = encontraCargo(scanner);
        List<UnidadeTrabalho> unidades = adicionaUnidades(scanner);

        Funcionario funcionario = new Funcionario(nome, cpf, salario, dataContratacao, cargo, unidades);
        funcionarioRepository.save(funcionario);
        System.out.println("Salvo!");
    }

    public void listar(Scanner scanner){
        try {
            System.out.println("Qual P??gina deseja visualizar? ");
            int pagina = scanner.nextInt();
            Pageable pageable = PageRequest.of(pagina, 1);
            Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);

            System.out.println(funcionarios);
            System.out.println("Pagina Atual: "+ funcionarios.getNumber());
            funcionarios.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Quer fazer outra pesquisa?(s/n)");
            String escolha = scanner.next();
            if(escolha.equals("s")){
                listar(scanner);
            }
        }
    }

    public void deletar(Scanner scanner){
        System.out.println("Qual Id do Funcionario que quer deletar: ");
        try {
            Integer id = scanner.nextInt();
            funcionarioRepository.deleteById(id);
        } catch (Exception e){
            System.out.println("ID Inv??lido");
        }
    }

    public void atualizar(Scanner scanner){
        System.out.println("Qual Id do Cargo que quer Atualizar: ");

        try{
            Integer id = Integer.getInteger(scanner.next());
            Optional<Funcionario> funcionario = funcionarioRepository.findById(id);

            funcionario.ifPresentOrElse(f -> {
                System.out.print("Nome do Funcionario: ");
                String nome = scanner.next();
                System.out.print("CPF do Funcionario: ");
                String cpf = scanner.next();
                System.out.print("Salario do Funcionario: ");
                Double salario = scanner.nextDouble();
                LocalDate dataContratacao = f.getDataContratacao();
                Cargo cargo = encontraCargo(scanner);
                List<UnidadeTrabalho> unidades = adicionaUnidades(scanner);

                Funcionario funcionarioAlterado = new Funcionario(id, nome, cpf, salario, dataContratacao, cargo, unidades);

                funcionarioRepository.save(funcionarioAlterado);
                System.out.println("Atualizado!");
            },
            () -> System.out.println("Cargo N??o encontrado com o id:" + id)
            );
        } catch (Exception e){
            System.out.println("Entrada Inv??lida");
        }
    }

    public List<UnidadeTrabalho> adicionaUnidades(Scanner scanner){
        List<UnidadeTrabalho> unidades = new ArrayList<>();

        do {
            try {
                System.out.println("Digite o unidadeId (Para sair digite 0)");
                int unidadeId = scanner.nextInt();

                if(unidadeId != 0) {
                    Optional<UnidadeTrabalho> unidade = unidadeTrabalhoRepository.findById(unidadeId);
                    unidade.ifPresentOrElse(
                            unidades::add,
                            () -> System.out.println("Unidade com ID "+ unidadeId + " N??o foi encontrado")
                    );
                } else {
                    break;
                }
            } catch (Exception e ){
                System.out.println("Entrada Inv??lida");
            }
        } while (true);

        return unidades;
    }

    public Cargo encontraCargo(Scanner scanner){

        AtomicReference<Boolean> isTrue = new AtomicReference<>(true);
        final Cargo cargoEncontrado = new Cargo();

        do {
            try {
                System.out.println("Digite o CargoID: ");
                int unidadeId = scanner.nextInt();
                Optional<Cargo> cargo = cargoRepository.findById(unidadeId);
                cargo.ifPresentOrElse(
                        c -> {
                            cargoEncontrado.setId(c.getId());
                            cargoEncontrado.setDescricao(c.getDescricao());
                            cargoEncontrado.setFuncionarios(c.getFuncionarios());
                            isTrue.set(false);
                        },
                        () -> System.out.println("Unidade com ID "+ unidadeId + " N??o foi encontrado")
                );

            } catch (Exception e ){
                System.out.println("Entrada Inv??lida");
            }
        } while (isTrue.get());

        return cargoEncontrado;
    }


}
