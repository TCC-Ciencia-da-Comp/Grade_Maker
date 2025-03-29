package com.datamonki.ApiCadastro.model;


import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class GradeModelV1 {

    private Turma turma;
    private Matriz matriz;
    private Disponibilidade disponibilidade;
    private Professor professor;
    private Disciplina disciplina;
    private Map<String, Integer> diasSemana;
    private int versao;
    private String status;
}
