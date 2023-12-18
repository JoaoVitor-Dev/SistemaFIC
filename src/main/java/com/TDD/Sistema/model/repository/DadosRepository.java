package com.TDD.Sistema.model.repository;
import com.TDD.Sistema.model.entity.*;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DadosRepository {
    private static final Map<Integer, Celular> celulares = new HashMap<>();
    private static final Map<Integer, Curso> cursos = new HashMap<>();
    private static final Map<Integer, Estudante> estudantes = new HashMap<>();
    private static final Map<Integer, Matricula> matriculas = new HashMap<>();
    private static final Map<Integer, TurmaCurso> turmas = new HashMap<>();

    private static int incrementaCelularID = 1;
    private static int incrementaCursoID = 1;
    private static int incrementaEstudanteID = 1;
    private static int incrementaMatriculaID = 1;
    private static int incrementaTurmaID = 1;

    static
    {
        criaDadosParaTestes();
    }

    /**
     * Neste método crio dados "fakes" para os testes de unidade
     */
    public static void criaDadosParaTestes(){
        saveCelular(new Celular("(94) 3384-8433"));
        saveCelular(new Celular("(63) 226-2406"));
        saveCelular(new Celular("(63) 4444-5555"));
        saveCelular(new Celular("(63) 5555-4444"));
        saveCelular(new Celular("(63) 6666-3333"));
        saveCelular(new Celular("(63) 7777-2222"));

        Estudante estudante0 = new Estudante("Mauro de Boni", LocalDate.of(2000, 9, 20), "Rua P5 Q24 LOTE 19");
        estudante0.adicionarCelular(findCelularById(1));
        estudante0.adicionarCelular(findCelularById(2));
        saveEstudante(estudante0);

        Estudante estudante1 = new Estudante("Fagno", LocalDate.of(2001, 9, 20), "Rua P5 Q24 LOTE 19");
        estudante1.adicionarCelular(findCelularById(3));
        saveEstudante(estudante1);

        Estudante estudante2 = new Estudante("Marinaldo", LocalDate.of(2001, 9, 20), "Rua P5 Q24 LOTE 19");
        estudante2.adicionarCelular(findCelularById(4));
        saveEstudante(estudante2);

        Estudante estudante3 = new Estudante("Manoel Campos", LocalDate.of(1999, 9, 20), "Rua P5 Q24 LOTE 19");
        estudante3.adicionarCelular(findCelularById(5));
        saveEstudante(estudante3);

        Estudante estudante4 = new Estudante("João Vitor de Sousa", LocalDate.of(2020, 9, 20), "Rua P5 Q24 LOTE 19");
        estudante4.adicionarCelular(findCelularById(0));
        saveEstudante(estudante4);

        Curso curso1 = new Curso("Testes de Sofware","40 horas",  "Testes em sistemas");
        saveCurso(curso1);
        Curso curso2 = new Curso("Desenvolvimento Mobile","60 horas",  "Programação e criação de aplicativos android");
        saveCurso(curso2);
        Curso curso3 = new Curso("Programação Web I","50 horas" , "Programação de aplicações web nível 1");
        saveCurso(curso3);
        Curso curso4 = new Curso("Programação Web II", "40 horas", "Programação de aplicações web nível 1");
        saveCurso(curso4);
        Curso curso5 = new Curso("Introdução a Conectividade","40 horas","Comunicações de Máquinas e redes");
        saveCurso(curso5);
        Curso curso6 = new Curso("Inglês Instrumental","30 horas", "Interpretação básica de inglês");
        saveCurso(curso6);
        Curso curso7 = new Curso("Gestão de Projetos", "45 horas", "PMI Fundamentals");
        saveCurso(curso7);
        Curso curso8 = new Curso("Estratégias de Marketing Digital", "55 horas", "Marketing Avançado");
        saveCurso(curso8);
        Curso curso9 = new Curso("Fotografia para Iniciantes","35 horas", "Fundamentos de Fotografia");
        saveCurso(curso9);

        TurmaCurso turmaCurso1 = new TurmaCurso("Palmas", 15, 14, "2/1/2023", "20/1/2023", "21/1/2023", "10/5/2023", findCursoById(1));
        saveTurmaCurso(turmaCurso1);
        TurmaCurso turmaCurso2 = new TurmaCurso("Palmas", 15, 14, "2/1/2023", "20/1/2023", "21/1/2023", "10/5/2023", findCursoById(2));
        saveTurmaCurso(turmaCurso2);
        TurmaCurso turmaCurso3 = new TurmaCurso("Palmas", 15, 15, "2/1/2023", "20/1/2023", "21/1/2023", "10/5/2023", findCursoById(3));
        saveTurmaCurso(turmaCurso3);

        Matricula matricula1 = new Matricula(findEstudanteById(1), "3/1/2023", findTurmaCursoById(1));
        turmaCurso1.adicionarMatircula(matricula1);
        saveMatricula(matricula1);

        Matricula matricula2 = new Matricula(findEstudanteById(2), "3/1/2023", findTurmaCursoById(2));
        turmaCurso2.adicionarMatircula(matricula2);

    }

    /**
     * Método para salvar Celular
     * @param celular
     */
    public static void saveCelular(Celular celular) {
        celular.setIdCelular(incrementaCelularID++);
        celulares.put(celular.getIdCelular(), celular);
    }

    public static List<Celular> getAllCelulares() {
        return new ArrayList<>(celulares.values());
    }

    public static Celular findCelularById(Integer id) {
        return celulares.get(id);
    }


    /**
     * Método para Salvar estudante
     * @param estudante
     */
    public static void saveEstudante(Estudante estudante) {
        estudante.setIdEstudante(incrementaEstudanteID++);
        estudantes.put(estudante.getIdEstudante(), estudante);
    }

    public static List<Estudante> getAllEstudante() {
        return new ArrayList<>(estudantes.values());
    }

    public static Estudante findEstudanteById(Integer id) {
        return estudantes.get(id);
    }


    /**
     * Método para salvar Curso
     * @param curso
     */
    public static void saveCurso(Curso curso) {
        curso.setIdCurso(incrementaCursoID++);
        cursos.put(curso.getIdCurso(), curso);
    }

    public static List<Curso> getAllCursos() {
        return new ArrayList<>(cursos.values());
    }

    public static Curso findCursoById(Integer id) {
        return cursos.get(id);
    }

    /**
     * Método para salvar o TurmaCurso
     * @param turmaCurso
     */
    public static void saveTurmaCurso(TurmaCurso turmaCurso) {
        turmaCurso.setIdTurmaCurso(incrementaTurmaID++);
        turmas.put(turmaCurso.getIdTurmaCurso(), turmaCurso);
    }

    public static List<TurmaCurso> getAllTurmaCursos() {
        return new ArrayList<>(turmas.values());
    }

    public static TurmaCurso findTurmaCursoById(Integer id) {
        return turmas.get(id);
    }

    /**
     * Método para salvar Matricula
     * @param matricula
     */
    public static void saveMatricula(Matricula matricula) {
        matricula.setIdMatricula(incrementaMatriculaID++);
        matriculas.put(matricula.getIdMatricula(), matricula);
    }

    public static List<Matricula> getAllMatriculas() {
        return new ArrayList<>(matriculas.values());
    }

    public static Matricula findMatriculaById(Integer id) {
        return matriculas.get(id);
    }


}
