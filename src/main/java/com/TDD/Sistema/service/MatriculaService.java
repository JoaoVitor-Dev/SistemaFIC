package com.TDD.Sistema.service;
import com.TDD.Sistema.model.entity.Matricula;
import com.TDD.Sistema.model.entity.TurmaCurso;
import com.TDD.Sistema.model.repository.DadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.beans.Transient;

@Service
public class MatriculaService {
    @Autowired
    private DadosRepository dadosRepository;

    /**
     *
     * @param matricula
     * @return status da matricula
     */
    @Transient
    public String matriculaEstudante(Matricula matricula) {
        String mensagem = validaMatricula(matricula);
        if (mensagem.equals("Matricula valida")) {
            TurmaCurso turmaCurso = DadosRepository.findTurmaCursoById(matricula.getTurmaCurso().getIdTurmaCurso());

            if (turmaCurso.getVagasDisponiveis() > 0) {
                DadosRepository.saveMatricula(matricula);

                turmaCurso.setVagasDisponiveis(turmaCurso.getVagasDisponiveis() - 1);
                turmaCurso.adicionarMatircula(matricula);
                DadosRepository.saveTurmaCurso(turmaCurso);
            } else {
                return "Não há vagas disponíveis para a matrícula";
            }

            return "Matricula realizada com sucesso!";
        }
        return mensagem;
    }

    /**
     *
     * @param matricula
     * @return validação da matricula
     */
    private String validaMatricula(Matricula matricula) {
        if (matricula == null || matricula.getEstudante() == null || matricula.getDataMatricula() == null || matricula.getTurmaCurso() == null) {
            return "Por favor informe todos os campos da matricula!";
        }

        if (matricula.getEstudante().getIdade() < 15) {
            return "O estudante deve possuir no minimo 15 anos!";
        }

        if (!matricula.getTurmaCurso().podeMatricular(matricula)) {
            return "Matricula não permitida! Vagas indisponíveis ou fora do prazo de matricula!";
        }
        return "Matricula valida";
    }


}
