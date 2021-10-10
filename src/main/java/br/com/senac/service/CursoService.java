package br.com.senac.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.domain.Curso;
import br.com.senac.repository.CursoRepositorio;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class CursoService {
	
	@Autowired
	CursoRepositorio repo;
	
	
	public Curso search(Integer id) throws ObjectNotFoundException{
		
		Optional<Curso> curso = repo.findById(id);
		return curso.orElseThrow(() -> new ObjectNotFoundException ("Não encontrado. Id: " + id + ", Tipo!" + Curso.class.getName()));
	}
	
	
	public List<Curso> searchAll () {
		
		return repo.findAll();
	}
	
	
	public Curso save (Curso curso) {
		
		return repo.save(curso);
	}
	
	
	public List<Curso> saveAll (List<Curso> cursos) {
		
		return repo.saveAll(cursos);
	}
	
	
	public Curso edit (Curso curso) throws ObjectNotFoundException {
		
		Curso cursoAntigo = search(curso.getId());
		cursoAntigo.setId(curso.getId());
		cursoAntigo.setNome(curso.getNome());
		cursoAntigo.setDescricao(curso.getDescricao());
		cursoAntigo.setProfessor(curso.getProfessor());
		cursoAntigo.setDisciplina(curso.getDisciplina());
		return save(cursoAntigo);
	}
	
	
	public void delete (Integer id) {
		
		repo.deleteById(id);
	}

}
