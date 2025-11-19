package br.edu.ucsal.sergiolj.registervalidation.DbRegisterAndValidation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ucsal.sergiolj.registervalidation.DbRegisterAndValidation.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByEmail_Email(String email);

}
