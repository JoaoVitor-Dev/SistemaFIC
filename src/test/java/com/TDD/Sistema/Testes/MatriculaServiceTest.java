package com.TDD.Sistema.Testes;

import com.TDD.Sistema.model.entity.Estudante;
import com.TDD.Sistema.model.entity.Matricula;
import com.TDD.Sistema.model.entity.TurmaCurso;
import com.TDD.Sistema.model.repository.DadosRepository;
import com.TDD.Sistema.service.MatriculaService;
import com.TDD.Sistema.service.TurmaCursoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class MatriculaServiceTest {
    @InjectMocks
    MatriculaService matriculaService;

    @InjectMocks
    TurmaCursoService turmaCursoService;

    Estudante estudante;
    Estudante estudanteMenor;
    Matricula matricula;
    Matricula matriculaDadosFaltante;
    Matricula matriculaMenor;
    Matricula matriculaTurmaSemVaga;
    Matricula matriculaForaDoPeriodo;
    TurmaCurso turmaCurso;

    @BeforeEach
    public void setUp() {

        estudante = DadosRepository.findEstudanteById(3);
        estudanteMenor = DadosRepository.findEstudanteById(5);

        turmaCurso = DadosRepository.findTurmaCursoById(1);

        matricula = new Matricula();
        matricula.setDataMatriculaString("10/01/2023");
        matricula.setEstudante(estudante);
        matricula.setTurmaCurso(turmaCurso);

        matriculaDadosFaltante = new Matricula();
        matriculaDadosFaltante.setEstudante(estudante);
        matriculaDadosFaltante.setTurmaCurso(turmaCurso);

        matriculaMenor = new Matricula();
        matriculaMenor.setDataMatriculaString("10/01/2023");
        matriculaMenor.setEstudante(estudanteMenor);
        matriculaMenor.setTurmaCurso(turmaCurso);

        matricula = new Matricula();
        matricula.setDataMatriculaString("10/01/2023");
        matricula.setEstudante(estudante);
        matricula.setTurmaCurso(turmaCurso);

        matriculaTurmaSemVaga = new Matricula();
        matriculaTurmaSemVaga.setDataMatriculaString("10/01/2023");
        matriculaTurmaSemVaga.setEstudante(estudante);
        matriculaTurmaSemVaga.setTurmaCurso(DadosRepository.findTurmaCursoById(3));
        matriculaTurmaSemVaga.getTurmaCurso().setVagasDisponiveis(0);

        matriculaForaDoPeriodo = new Matricula();
        matriculaForaDoPeriodo.setDataMatriculaString("01/01/2023");
        matriculaForaDoPeriodo.setEstudante(estudante);
        matriculaForaDoPeriodo.setTurmaCurso(turmaCurso);

    }


    // Neste bloco faço a implementação dos testes

    /**
     * Teste de matricula
     */
    @Test
    void matriculaComSucesso() {
        assertEquals("Matricula realizada com sucesso!", matriculaService.matriculaEstudante(matricula));
    }

    /**
     * Teste de matricula com dados faltando
     */
    @Test
    void matriculaComDadosVazios() {
        assertEquals("Por favor informe todos os campos da matricula!", matriculaService.matriculaEstudante(matriculaDadosFaltante));
    }

    /**
     * Teste de matricula com estudante menor de 15 anos
     */
    @Test
    void matriculaComEstudanteMenor15anos() {
        assertEquals("O estudante deve possuir no minimo 15 anos!", matriculaService.matriculaEstudante(matriculaMenor));
    }

    /**
     * Teste de matricula sem vagas disponíveis
     */
    @Test
    void matriculaSemVaga() {
        assertEquals("Matricula não permitida! Vagas indisponíveis ou fora do prazo de matricula!", matriculaService.matriculaEstudante(matriculaTurmaSemVaga));
    }

    /**
     * Teste de matricula fora do período
     *
     */
    @Test
    void matriculaForaDoPeriodo() {
        assertEquals("Matricula não permitida! Vagas indisponíveis ou fora do prazo de matricula!", matriculaService.matriculaEstudante(matriculaForaDoPeriodo));
    }

    /**
     * Teste de listagem de turma sem alunos (exibir mensagem quando não houver alunos)
     */
    @Test
    public void testListarAlunosPorTurmaIdSemAlunosMatriculados() {

        int turmaIdSemAlunos = 3;

        RuntimeException exception = assertThrows(RuntimeException.class, () -> turmaCursoService.listarAlunosPorTurmaId(turmaIdSemAlunos));

        String expectedMessage = "Turma não possuí alunos matriculados!";
        String actualMessage = exception.getMessage();

        assert (actualMessage.contains(expectedMessage));
    }

}
