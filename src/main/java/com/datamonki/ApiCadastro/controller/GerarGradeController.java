package com.datamonki.ApiCadastro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datamonki.ApiCadastro.response.ApiResponse;
import com.datamonki.ApiCadastro.service.GerarGradeService;
import com.datamonki.ApiCadastro.service.GerarGradeJavaV1Service;

@RestController
@RequestMapping("/gerar-grade")
public class GerarGradeController {
	
	@Autowired
	private GerarGradeService gerarGradeService;
	
	@Autowired
	private GerarGradeJavaV1Service gerarGradeJavaV1Service;
	
	@GetMapping
	public ResponseEntity<ApiResponse> getByGradeGen() {
	        return gerarGradeService.getByGradeGen();
	}

    @GetMapping("/java/v1")
	public ResponseEntity<ApiResponse> getByGradeJavaV1() {
	        return gerarGradeJavaV1Service.getByGradeJavaV1();
	}
	
}
