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

public class GradeEntry {

    private Turma turma;
    private Matriz matriz;
    private Disponibilidade disponibilidade; // pode ser nulo, em caso de "vazio"
    private Professor professor;       // pode ser nulo, em caso de "vazio"
    private Disciplina disciplina;
    private Map<String, Integer> diasSemana;
    private int versao;
    private String status; // "ok" ou "vazio"
}
