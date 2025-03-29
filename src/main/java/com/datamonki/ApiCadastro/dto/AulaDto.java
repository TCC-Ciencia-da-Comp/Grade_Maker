package com.datamonki.ApiCadastro.dto;

import com.datamonki.ApiCadastro.model.DiaSemana;
import com.datamonki.ApiCadastro.model.Disciplina;
import com.datamonki.ApiCadastro.model.Professor;
import com.datamonki.ApiCadastro.model.Turma;
import com.datamonki.ApiCadastro.model.Turno;

public record AulaDto(Turma turma, Professor professor, DiaSemana diaSemana,Turno turno, Disciplina disciplina) {
	
}
