package com.example.bancodedados.regesc.orm;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "professores")
public class Professor {

    @Id// Vai criar a Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)//vai criar ID sequencial na medida q são implementadas novas tuplas na tabela, este valor não pode ser Nulo
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, unique = true)//faz o Atributo Prontuário ter campos unicos, q não se repetem
    private String prontuario;
    @OneToMany(mappedBy = "professor")
    private List<Disciplina> disciplinas;

    @Deprecated
    public Professor(){
    }

    public Professor(String nome, String prontuario) {
        this.nome = nome;
        this.prontuario = prontuario;
    }

    public Long getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProntuario() {
        return prontuario;
    }

    public void setProntuario(String prontuario) {
        this.prontuario = prontuario;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", prontuario='" + prontuario + '\'' +
                '}';
    }
}
