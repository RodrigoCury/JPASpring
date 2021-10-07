package br.dev.rodrigocury.models;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String cpf;
    private Double salario;
    private LocalDate dataContratacao;

    @ManyToOne
    @JoinColumn(name = "cargo_id", nullable = false)
    private Cargo cargo;

    @Fetch(FetchMode.SELECT)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="funcionarios_unidades", joinColumns = {@JoinColumn(name = "fk_funcionario")} ,inverseJoinColumns = {@JoinColumn(name = "fk_unidade")})
    private List<UnidadeTrabalho> unidadeTrabalhos;

    public Funcionario() {
    }

    public Funcionario(String nome, String cpf, Double salario, LocalDate dataContratacao, Cargo cargo, List<UnidadeTrabalho> unidadeTrabalhoTrabalhos) {
        this.nome = nome;
        this.cpf = cpf;
        this.salario = salario;
        this.dataContratacao = dataContratacao;
        this.cargo = cargo;
        this.unidadeTrabalhos = unidadeTrabalhoTrabalhos;
    }

    public Funcionario(Integer id, String nome, String cpf, Double salario, LocalDate dataContratacao, Cargo cargo, List<UnidadeTrabalho> unidadeTrabalhoTrabalhos) {
        this(nome, cpf, salario,  dataContratacao, cargo, unidadeTrabalhoTrabalhos);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public Double getSalario() {
        return salario;
    }

    public LocalDate getDataContratacao() {
        return dataContratacao;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public List<UnidadeTrabalho> getUnidadeTrabalhos() {
        return unidadeTrabalhos;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public void setDataContratacao(LocalDate dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public void setUnidadeTrabalhos(List<UnidadeTrabalho> unidadeTrabalhos) {
        this.unidadeTrabalhos = unidadeTrabalhos;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "nome=" + nome +
                ", salario=" + salario +
                ", dataContratacao=" + dataContratacao +
                ", cargo=" + cargo +
                '}';
    }
}
