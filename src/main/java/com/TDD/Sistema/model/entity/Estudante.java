package com.TDD.Sistema.model.entity;
import lombok.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Estudante {
    Integer idEstudante;
    String nome;
    LocalDate dataNascimento;
    String endereco;
    List<Celular> listCelular = new ArrayList<>();

    /**
     * Constructor
     * @param nome
     * @param dataNascimento
     * @param endereco
     */
    public Estudante(String nome, LocalDate dataNascimento, String endereco) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
    }

    /**
     *
     * @return idade
     */
    public Integer getIdade() {
        LocalDate dataAtual = LocalDate.now();
        int idade = (int) dataNascimento.until(dataAtual, ChronoUnit.YEARS);
        return idade;

    }

    /**
     *
     * @param celular
     */
    public void adicionarCelular(Celular celular) {
        listCelular.add(celular);
    }

    /**
     *
     * @param celular
     */
    public void removerCelular(Celular celular) {
        listCelular.remove(celular);
    }
}
