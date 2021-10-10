package br.com.senac.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.domain.Professor;
import br.com.senac.repository.ProfessorRepositorio;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ProfessorService {
	
	@Autowired
	ProfessorRepositorio repo;
	
	
	public Professor search(Integer id) throws ObjectNotFoundException{
		
		Optional<Professor> professor = repo.findById(id);
		return professor.orElseThrow(() -> new ObjectNotFoundException ("Não encontrado. Id: " + id + ", Tipo!" + Professor.class.getName()));
	}
	
	
	public List<Professor> searchAll () {
		
		return repo.findAll();
	}
	
	
	public Professor save (Professor professor) {
		
		return repo.save(professor);
	}
	
	
	public List<Professor> saveAll (List<Professor> professores) {
		
		return repo.saveAll(professores);
	}
	
	
	public Professor edit (Professor professor) throws ObjectNotFoundException {
		
		Professor professorAntigo = search(professor.getId());
		professorAntigo.setNome(professor.getNome());
		professorAntigo.setSobrenome(professor.getSobrenome());
		return save(professorAntigo);
	}
	
	
	public void delete (Integer id) {
		
		repo.deleteById(id);
	}

}
