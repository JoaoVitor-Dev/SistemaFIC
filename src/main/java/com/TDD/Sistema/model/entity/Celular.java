package com.TDD.Sistema.model.entity;
import lombok.*;
import org.springframework.stereotype.Service;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Celular {
    Integer idCelular;
    String numero;
    public Celular(String numero) {
        this.numero = numero;
    }
}
