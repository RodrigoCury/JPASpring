package br.dev.rodrigocury.service;

import br.dev.rodrigocury.models.Cargo;
import br.dev.rodrigocury.models.UnidadeTrabalho;
import br.dev.rodrigocury.repository.CargoRepository;
import br.dev.rodrigocury.repository.UnidadeTrabalhoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Component
@Service
public class CrudUnidadeService {
    private Boolean system = true;
    private final UnidadeTrabalhoRepository unidadeRepository;

    public CrudUnidadeService(UnidadeTrabalhoRepository unidadeRepository) {
        this.unidadeRepository = unidadeRepository;
    }

    public void inicial(Scanner scanner){
        while (system) {

            System.out.println("Unidade de Trabalho Selecionado: ");
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
        Iterable<UnidadeTrabalho> unidadeTrabalhos = unidadeRepository.findAllById(ids);
        unidadeTrabalhos.forEach(System.out::println);
    }

    public void salvar(Scanner scanner){
        System.out.print("Descricao da Unidade de Trabalho: ");
        String descricao = scanner.next();
        System.out.print("Endereço da Unidade de Trabalho: ");
        String endereco = scanner.next();
        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho(descricao, endereco);
        unidadeRepository.save(unidadeTrabalho);
        System.out.println("Salvo!");
    }

    public void listar(){
        unidadeRepository.findAll().forEach(System.out::println);
    }

    public void deletar(Scanner scanner){
        System.out.println("Qual Id da Unidade de Trabalho que quer deletar: ");
        try {
            Integer id = scanner.nextInt();
            unidadeRepository.deleteById(id);
        } catch (Exception e){
            System.out.println("ID Inválido");
        }
    }

    public void atualizar(Scanner scanner){
        System.out.println("Qual Id da Unidade de Trabalho que quer Atualizar: ");

        try{
            Integer id = scanner.nextInt();
            Optional<UnidadeTrabalho> cargo = unidadeRepository.findById(id);

            cargo.ifPresentOrElse(u -> {
                System.out.println("Unidade Atual "+ u + "!");
                System.out.println("Qual a Nova Descricao da Unidade de Trabalho: ");
                String novaDescricao = scanner.next();
                System.out.println("Qual o Novo Endereço da Unidade de Trabalho: ");
                String novoEndereco = scanner.next();
                u.setDescricao(novaDescricao);
                u.setEndereco(novoEndereco);
                unidadeRepository.save(u);
                System.out.println("Atualizado!");
            },
            () -> System.out.println("Unidade de Trabalho Não encontrado com o id:" + id)
            );
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("ID Inválido");
        }
    }

}
