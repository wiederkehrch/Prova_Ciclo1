package br.com.senac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senac.domain.Curso;


@Repository
public interface CursoRepositorio extends JpaRepository <Curso, Integer> {

}
