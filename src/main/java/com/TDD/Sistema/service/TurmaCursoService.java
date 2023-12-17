package com.TDD.Sistema.service;
import com.TDD.Sistema.model.entity.Matricula;
import com.TDD.Sistema.model.entity.TurmaCurso;
import com.TDD.Sistema.model.repository.DadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TurmaCursoService {

    @Autowired
    private DadosRepository dadosRepository;

    /**
     *
     * @param id
     * @return número de vagas disponíveis
     */
    int vagasDisponivies(int id) {
        return dadosRepository.findTurmaCursoById(id).getVagasDisponiveis();
    }

    /**
     *
     * @return lista de turmas com alunos
     */
    public List<TurmaCurso> listarTurmasComAlunosMatriculados() {
        List<TurmaCurso> turmasComAlunos = DadosRepository.getAllTurmaCursos()
                .stream()
                .filter(turmaCurso -> !turmaCurso.getMatriculaList().isEmpty())
                .collect(Collectors.toList());

        if (turmasComAlunos.isEmpty()) {
            throw new RuntimeException("Nenhuma turma encontrada com alunos matriculados.");
        }

        return turmasComAlunos;
    }

    /**
     *
     * @param id
     * @return
     */
    public TurmaCurso turmaCursoId(int id) {
        return DadosRepository.findTurmaCursoById(id);
    }

    /**

     * @param turmaId
     * @return lista de alunos matriculados na turma
     */
    public List<Matricula> listarAlunosPorTurmaId(int turmaId) {
        TurmaCurso turma = DadosRepository.findTurmaCursoById(turmaId);

        if (turma == null) {
            throw new RuntimeException("Turma não identificada");
        }

        if (turma.getMatriculaList().isEmpty()) {
            throw new RuntimeException("Turma não possuí alunos matriculados!");
        }

        return turma.getMatriculaList();
    }
}
