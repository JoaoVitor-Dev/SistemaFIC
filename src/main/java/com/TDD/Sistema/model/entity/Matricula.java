package com.TDD.Sistema.model.entity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Matricula {
    int idMatricula;
    Estudante estudante;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDate dataMatricula;
    TurmaCurso turmaCurso;

    /**
     * Constructor
     * @param estudante
     * @param dataMatricula
     * @param turmaCurso
     */
    public Matricula(Estudante estudante, String dataMatricula, TurmaCurso turmaCurso) {
        this.estudante = estudante;
        this.dataMatricula = toLocalDate(dataMatricula);
        this.turmaCurso = turmaCurso;
    }

    /**
     *
     * @param data formatt d/m/aaaa
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
     *
     * @param dataMatricula
     */
    public void setDataMatriculaString(String dataMatricula) {
        this.dataMatricula = toLocalDate(dataMatricula);
    }

    public TurmaCurso getTurmaCurso() {
        return turmaCurso;
    }

    public void setTurmaCurso(TurmaCurso turmaCurso) {
        this.turmaCurso = turmaCurso;
    }
}
