package com.datamonki.ApiCadastro.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.datamonki.ApiCadastro.dto.GradeGeradaDto;
import com.datamonki.ApiCadastro.model.Disponibilidade;
import com.datamonki.ApiCadastro.model.Matriz;
import com.datamonki.ApiCadastro.repository.DisponibilidadeRepository;
import com.datamonki.ApiCadastro.repository.MatrizRepository;
import com.datamonki.ApiCadastro.response.ApiResponse;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class GerarGradeService {
	
	private WebClient webClient;
	@Autowired
	private MatrizRepository matrizRepository;
	@Autowired
	private DisponibilidadeRepository disponibilidadeRepository;
	
    // Construtor para injetar WebClient
    public GerarGradeService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://127.0.0.1:8000/api/v2/").build();
    }
	
	public ResponseEntity<ApiResponse> getByGradeGen(){
	    List<Matriz> matrizes = matrizRepository.findAll();
	    List<Disponibilidade> disponibilidades = disponibilidadeRepository.findAll();

	    // Criar um mapa para enviar os dois objetos juntos no body
	    Map<String, Object> requestBody = new HashMap<>();
	    requestBody.put("matrizes", matrizes);
	    requestBody.put("disponibilidades", disponibilidades);

	    // Fazer a requisição POST corretamente
	    GradeGeradaDto retorno = webClient.post()
	            .uri("/gerar-grade/") 
	            .bodyValue(requestBody)  // Enviar o JSON correto
	            .retrieve()
	            .bodyToMono(GradeGeradaDto.class) 
	            .block(); 
	    return ResponseEntity.ok(new ApiResponse("Retorno", retorno));
	}
}
