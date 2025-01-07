package com.datamonki.ApiCadastro.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.datamonki.ApiCadastro.dto.DisponibilidadeDto;
import com.datamonki.ApiCadastro.exceptions.IdNotFoundException;
import com.datamonki.ApiCadastro.exceptions.ValidationException;
import com.datamonki.ApiCadastro.model.DiaSemana;
import com.datamonki.ApiCadastro.model.Disciplina;
import com.datamonki.ApiCadastro.model.Disponibilidade;
import com.datamonki.ApiCadastro.model.Professor;
import com.datamonki.ApiCadastro.model.Turno;
import com.datamonki.ApiCadastro.repository.DiaSemanaRepository;
import com.datamonki.ApiCadastro.repository.DisciplinaRepository;
import com.datamonki.ApiCadastro.repository.DisponibilidadeRepository;
import com.datamonki.ApiCadastro.repository.ProfessorRepository;
import com.datamonki.ApiCadastro.repository.TurnoRepository;
import com.datamonki.ApiCadastro.response.ApiResponse;

import jakarta.transaction.Transactional;

@Service
public class DisponibilidadeService {
	
	@Autowired
	private DisponibilidadeRepository disponibilidadeRepository;
	@Autowired
	private ProfessorRepository professorRepository;
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	@Autowired
	private TurnoRepository turnoRepository;
	@Autowired
	private DiaSemanaRepository diaSemanaRepository;
	
	private void verificarProfessorId(Integer id) {
		if(!professorRepository.existsById(id)) {
			throw new IdNotFoundException("Não há professor registrado com o id: "+ id + " verifique e tente novamente");
		}
	}
	
	//Verificar se os atributos passados estão corretos e se os ids de fato existem no banco
	private void verificar(DisponibilidadeDto disponibilidadeDto) {
		List<String> messages = new ArrayList<String>();
		Optional<Professor> professor = professorRepository.findById(disponibilidadeDto.idProfessor());
		if(!professorRepository.existsById(disponibilidadeDto.idProfessor())){
			messages.add("Não há professor registrado com o id:"+ disponibilidadeDto.idProfessor() + " verifique e tente novamente");
		}
		else if(!disciplinaRepository.existsById(disponibilidadeDto.idDisciplina())) {
			messages.add("Não há disciplina registrada com o id:"+ disponibilidadeDto.idDisciplina() + " verifique e tente novamente");
		}
		else if(!turnoRepository.existsById(disponibilidadeDto.idTurno())) {
			messages.add("Não há turno registrado com o id:"+ disponibilidadeDto.idTurno() + " verifique e tente novamente");
		}
		else if(!diaSemanaRepository.existsById(disponibilidadeDto.idDiaSemana())) {
			messages.add("Não há dia da semana registrado com o id:"+ disponibilidadeDto.idDiaSemana() + " verifique e tente novamente");
		}
		else if(disponibilidadeRepository.verifyRepeticao(disponibilidadeDto.idProfessor(), disponibilidadeDto.idDiaSemana(), 
				disponibilidadeDto.idTurno(), disponibilidadeDto.semestre(), disponibilidadeDto.ano())) {
			messages.add("Disponibilidade já registrada");
		}
		else if(disponibilidadeDto.semestre() < 1 || disponibilidadeDto.semestre() > 2) {
			messages.add("Só são permitidos primeiro e segundo semestre.");
		}
		
		
		if(!messages.isEmpty()) {
			throw new ValidationException(messages);
		}
	}
	@Transactional
	public ResponseEntity<ApiResponse> save(DisponibilidadeDto disponibilidadeDto){
		verificar(disponibilidadeDto);
		Optional<Professor> professor = professorRepository.findById(disponibilidadeDto.idProfessor());
		Optional<DiaSemana> diaSemana = diaSemanaRepository.findById(disponibilidadeDto.idDiaSemana());
		Optional<Turno> turno = turnoRepository.findById(disponibilidadeDto.idTurno());
		Optional<Disciplina> disciplina = disciplinaRepository.findById(disponibilidadeDto.idDisciplina());
		Disponibilidade disponibilidade = new Disponibilidade();
		disponibilidade.setProfessor(professor.get());
		disponibilidade.setDiaSemana(diaSemana.get());
		disponibilidade.setTurno(turno.get());
		disponibilidade.setSemestre(disponibilidadeDto.semestre());
		disponibilidade.setAno(disponibilidadeDto.ano());
		disponibilidade.setDisciplina(disciplina.get());
		disponibilidadeRepository.save(disponibilidade);
	
		return ResponseEntity.ok(new ApiResponse("Disponibilidade cadastrada com sucesso", disponibilidade));
	}
	
	public ResponseEntity<ApiResponse> getAll(){
		List<Disponibilidade> disponibilidades = disponibilidadeRepository.findAll();
		return ResponseEntity.ok(new ApiResponse("Sucesso! Todas as disponibilidades retornadas.", disponibilidades));
	}
	
	
	public ResponseEntity<ApiResponse> deleteByIdProfessor(Integer idProfessor){
		verificarProfessorId(idProfessor);
		if (disponibilidadeRepository.verifyDisponibilidadeProfessor(idProfessor)){
			disponibilidadeRepository.deleteByIdProfessor(idProfessor);
			return ResponseEntity.ok(new ApiResponse("Disponibilidades deletadas com sucesso", null));
		}

		return ResponseEntity.ok(new ApiResponse("Sem disponibilidades cadastradas", null));
		
	}
	
		
	

}