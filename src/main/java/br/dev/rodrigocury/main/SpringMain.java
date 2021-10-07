package br.dev.rodrigocury.main;

import br.dev.rodrigocury.models.Cargo;
import br.dev.rodrigocury.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EntityScan(basePackages = {"br.dev.rodrigocury.models"})
@ComponentScan(basePackages={"br.dev.rodrigocury.repository"})
@EnableJpaRepositories(basePackages = {"br.dev.rodrigocury.repository"})
public class SpringMain implements CommandLineRunner {

    @Autowired
    private final CargoRepository repository;

    public SpringMain(CargoRepository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringMain.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Cargo cargo = new Cargo("Junior Dev");
        repository.save(cargo);
    }
}
