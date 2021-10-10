package br.com.senac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senac.domain.Disciplina;

@Repository
public interface DisciplinaRepositorio extends JpaRepository <Disciplina, Integer> {

}
