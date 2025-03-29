package com.datamonki.ApiCadastro.dto;

import java.util.List;


public record GradeGeradaDto(Double nota, List<String> conflitos, List<AulaDto> aulas) {
	
}
