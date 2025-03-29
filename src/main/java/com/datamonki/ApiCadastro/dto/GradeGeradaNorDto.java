package com.datamonki.ApiCadastro.dto;

import java.util.List;

import com.datamonki.ApiCadastro.model.GradeEntry;

public record GradeGeradaNorDto(Integer versao, List<GradeEntry> grade, List<String> conflitos, Integer qtdErros, Integer pontuacao) {

}
