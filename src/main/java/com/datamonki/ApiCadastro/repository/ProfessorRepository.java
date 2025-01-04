package com.datamonki.ApiCadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datamonki.ApiCadastro.model.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

}
