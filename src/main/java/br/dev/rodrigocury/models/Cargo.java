package br.dev.rodrigocury.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cargos")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descricao;

    @OneToMany(mappedBy = "cargo")
    private List<Funcionario> funcionarios;

    public Cargo() {
    }

    public Cargo(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Cargo(String descricao) {
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "id=" + id +
                ", descricao=" + descricao +
                '}';
    }
}
