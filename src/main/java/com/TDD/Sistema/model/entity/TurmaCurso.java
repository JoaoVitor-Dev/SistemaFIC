package com.TDD.Sistema.model.entity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TurmaCurso {
    int idTurmaCurso;
    String local;
    int vagas;
    int vagasDisponiveis;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDate inicioMatriculas;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDate fimMatriculas;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDate inicioAulas;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDate fimAulas;
    Curso curso;
    List<Matricula> matriculaList = new ArrayList<>();

    public TurmaCurso(String local, int vagas, int vagasDisponiveis, String inicioMatriculas, String fimMatriculas, String inicioAulas, String fimAulas, Curso curso) {
        this.local = local;
        this.vagas = vagas;
        this.vagasDisponiveis = vagasDisponiveis;
        this.inicioMatriculas = toLocalDate(inicioMatriculas);
        this.fimMatriculas = toLocalDate(fimMatriculas);
        this.inicioAulas = toLocalDate(inicioAulas);
        this.fimAulas = toLocalDate(fimAulas);
        this.curso = curso;
    }

    /**
     * @param data d/m/aaaa
     * @return
     */
    public static LocalDate toLocalDate(String data) {
        String[] dataArray = data.split("/");
        int dia = Integer.parseInt(dataArray[0]);
        int mes = Integer.parseInt(dataArray[1]);
        int ano = Integer.parseInt(dataArray[2]);
        return LocalDate.of(ano, mes, dia);
    }

    /**
     * @param matricula
     * @return true=permitido, false=não permitido
     */
    public boolean podeMatricular(Matricula matricula) {
        boolean result;
        result = (matricula.getDataMatricula().isEqual(inicioMatriculas) || matricula.getDataMatricula().isAfter(inicioMatriculas))
                && (matricula.getDataMatricula().isEqual(fimMatriculas) || matricula.getDataMatricula().isBefore(fimMatriculas))
                && vagasDisponiveis > 0 && matricula.getDataMatricula().isBefore(inicioAulas);

        return result;
    }

    /**
     * @param matricula
     */
    public void adicionarMatircula(Matricula matricula) {
        matriculaList.add(matricula);
    }

    /**
     * @param matricula
     */
    public void removerMatricula(Matricula matricula) {
        matriculaList.remove(matricula);
    }
}
