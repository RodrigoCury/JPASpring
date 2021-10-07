package br.dev.rodrigocury.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class UnidadeTrabalho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String descricao;
    private String endereco;

    @ManyToMany(mappedBy = "unidadeTrabalhos", fetch = FetchType.EAGER)
    private List<Funcionario> funcionarios;

    public UnidadeTrabalho() {
    }

    public UnidadeTrabalho(String descricao, String endereco) {
        this.descricao = descricao;
        this.endereco = endereco;
    }

    public UnidadeTrabalho(Integer id, String descricao, String endereco) {
        this(descricao, endereco);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Unidade{" +
                "id=" + id +
                ", descricao=" + descricao +
                ", endereco=" + endereco +
                '}';
    }
}
