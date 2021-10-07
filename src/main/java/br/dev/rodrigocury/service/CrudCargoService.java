package br.dev.rodrigocury.service;

import br.dev.rodrigocury.models.Cargo;
import br.dev.rodrigocury.repository.CargoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Component
@Service
public class CrudCargoService {
    private Boolean system = true;
    private final CargoRepository cargoRepository;

    public CrudCargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public void inicial(Scanner scanner){
        while (system) {

            System.out.println("Cargo Selecionado: ");
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
                case "4" -> listar();
                case "5" -> listarPorIds(scanner);
                default -> System.out.println("Nenhuma opção Valida");
            }
        }
    }

    private void listarPorIds(Scanner scanner) {
        List<Integer> ids = new ArrayList<>();

        while(true){
            System.out.println("Digite o id(0 para sair): ");
            try {
                Integer id = scanner.nextInt();
                if (id.equals(0)){
                    break;
                }
                ids.add(id);
            } catch (Exception e){
                System.out.println("Entrada Inválida");
            }
        }
        Iterable<Cargo> cargos = cargoRepository.findAllById(ids);
        cargos.forEach(System.out::println);
    }

    public void salvar(Scanner scanner){
        System.out.print("Descricao do Cargo: ");
        String descricao = scanner.next();
        Cargo cargo = new Cargo(descricao);
        cargoRepository.save(cargo);
        System.out.println("Salvo!");
    }

    public void listar(){
        cargoRepository.findAll().forEach(System.out::println);
    }

    public void deletar(Scanner scanner){
        System.out.println("Qual Id do Cargo que quer deletar: ");
        try {
            Integer id = scanner.nextInt();
            cargoRepository.deleteById(id);
        } catch (Exception e){
            System.out.println("ID Inválido");
        }
    }

    public void atualizar(Scanner scanner){
        System.out.println("Qual Id do Cargo que quer Atualizar: ");

        try{
            Integer id = Integer.getInteger(scanner.next());
            Optional<Cargo> cargo = cargoRepository.findById(id);

            cargo.ifPresentOrElse(c -> {
                System.out.println("Qual a Nova Descricao do Cargo " + c + ": ");
                String novaDescricao = scanner.next();
                c.setDescricao(novaDescricao);
                cargoRepository.save(c);
                System.out.println("Atualizado!");
            },
            () -> System.out.println("Cargo Não encontrado com o id:" + id)
            );
        } catch (Exception e){
            System.out.println("ID Inválido");
        }
    }

}
