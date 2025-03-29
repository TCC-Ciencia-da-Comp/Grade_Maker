package com.datamonki.ApiCadastro.dto;

import java.util.List;

import com.datamonki.ApiCadastro.model.GradeModelV1;

public record GradeGeradaJavaV1Dto(Integer versao, List<GradeModelV1> grade, List<String> conflitos, Integer qtdErros, Integer pontuacao) {

}
