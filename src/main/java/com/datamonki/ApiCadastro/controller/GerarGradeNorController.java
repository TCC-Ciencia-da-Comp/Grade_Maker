package com.datamonki.ApiCadastro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datamonki.ApiCadastro.response.ApiResponse;
import com.datamonki.ApiCadastro.service.GerarGradeNorService;

@RestController
@RequestMapping("/gerar-grade-nor")
public class GerarGradeNorController {

    @Autowired
	private GerarGradeNorService gerarGradeNorService;

    @GetMapping
	public ResponseEntity<ApiResponse> getByGradeNor() {
	        return gerarGradeNorService.getByGradeNor();
	}
}
