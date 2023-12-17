package com.TDD.Sistema.service;

import com.TDD.Sistema.model.entity.Estudante;
import com.TDD.Sistema.model.repository.DadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstudanteService {

    @Autowired
    private DadosRepository dadosRepository;

    /**
     * Cadastra estudante
     * @param estudante
     */
    public void cadastrarEstudante(Estudante estudante) {
        validacao(estudante);
        DadosRepository.saveEstudante(estudante);
    }

    /**
     * Valida o objeto estudante (atributos preenchidos)
     * Valida se estudante possui Celular setado
     * @param estudante
     */
    private void validacao(Estudante estudante){
        if (estudante == null || estudante.getNome() == null || estudante.getDataNascimento() == null || estudante.getDataNascimento() == null || estudante.getEndereco() == null) {
            throw new IllegalArgumentException("Informe todos os dados do estudante!");
        }

        if (estudante.getListCelular() == null || estudante.getListCelular().isEmpty()) {
            throw new IllegalArgumentException("O estudante deve possuir pelo menos um celular.");
        }
    }
}
