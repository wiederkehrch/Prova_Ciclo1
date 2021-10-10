package br.com.senac.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.domain.Disciplina;
import br.com.senac.repository.DisciplinaRepositorio;
import javassist.tools.rmi.ObjectNotFoundException;


@Service
public class DisciplinaService {
	
	@Autowired
	DisciplinaRepositorio repo;
	
	
	public Disciplina search(Integer id) throws ObjectNotFoundException{
		
		Optional<Disciplina> disciplina = repo.findById(id);
		return disciplina.orElseThrow(() -> new ObjectNotFoundException ("Não encontrado. Id: " + id + ", Tipo!" + Disciplina.class.getName()));
	}
	
	
	public List<Disciplina> searchAll () {
		
		return repo.findAll();
	}
	
	
	public Disciplina save (Disciplina disciplina) {
		
		return repo.save(disciplina);
	}
	
	
	public List<Disciplina> saveAll (List<Disciplina> disciplinas) {
		
		return repo.saveAll(disciplinas);
	}
	
	
	public Disciplina edit (Disciplina disciplina) throws ObjectNotFoundException {
		
		Disciplina disciplinaAntigo = search(disciplina.getId());
		disciplinaAntigo.setNome(disciplina.getNome());
		disciplinaAntigo.setDescricao(disciplina.getDescricao());
		return save(disciplinaAntigo);
	}
	
	
	public void delete (Integer id) {
		
		repo.deleteById(id);
	}

}
