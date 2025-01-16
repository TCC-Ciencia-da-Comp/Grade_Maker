package com.datamonki.ApiCadastro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datamonki.ApiCadastro.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	 Optional<Usuario> findByUsername(String username);
	
}
