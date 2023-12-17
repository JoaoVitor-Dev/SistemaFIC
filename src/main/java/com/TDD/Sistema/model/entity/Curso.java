package com.TDD.Sistema.model.entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Curso {
    int idCurso;
    String nome;
    String cargaHoraria;
    String descricao;

    public Curso(String nome, String cargaHoraria, String descricao) {
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
        this.descricao = descricao;
    }
}
