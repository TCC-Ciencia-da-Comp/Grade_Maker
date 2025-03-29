package com.datamonki.ApiCadastro.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.datamonki.ApiCadastro.dto.GradeGeradaJavaV1Dto;
import com.datamonki.ApiCadastro.model.Disponibilidade;
import com.datamonki.ApiCadastro.model.Matriz;
import com.datamonki.ApiCadastro.model.Turma;
import com.datamonki.ApiCadastro.repository.DisponibilidadeRepository;
import com.datamonki.ApiCadastro.repository.MatrizRepository;
import com.datamonki.ApiCadastro.repository.TurmaRepository;
import com.datamonki.ApiCadastro.response.ApiResponse;

@Service
public class GerarGradeJavaV1Service {  

	private WebClient webClient;
	@Autowired
	private MatrizRepository matrizRepository;
	@Autowired
	private DisponibilidadeRepository disponibilidadeRepository;
	@Autowired
	private TurmaRepository turmaRepository;
	
    // Construtor para injetar WebClient
    public GerarGradeJavaV1Service(WebClient.Builder webClientBuilder) { 
        this.webClient = webClientBuilder.baseUrl("http://localhost:8081").build();
    }
	
	public ResponseEntity<ApiResponse> getByGradeJavaV1(){
	    List<Matriz> matrizes = matrizRepository.findAll();
	    List<Disponibilidade> disponibilidades = disponibilidadeRepository.findAll();
	    List<Turma> turmas = turmaRepository.findAll();
			
	    Map<String, Object> requestBody = new HashMap<>();
	    requestBody.put("matrizes", matrizes);
	    requestBody.put("disponibilidades", disponibilidades);
	    requestBody.put("turmas", turmas); 

	    // Fazer a requisição POST corretamente
	    GradeGeradaJavaV1Dto retorno = webClient.post()
	            .uri("/gerar-grade/java/v1") 
	            .bodyValue(requestBody)  // Enviar o JSON correto
	            .retrieve()
	            .bodyToMono(GradeGeradaJavaV1Dto.class) 
	            .block(); 
	    return ResponseEntity.ok(new ApiResponse("Retorno", retorno)); 
	}
}
