package br.com.senac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.domain.Disciplina;
import br.com.senac.service.DisciplinaService;
import javassist.tools.rmi.ObjectNotFoundException;


@Controller                          
@RequestMapping("/disciplina") 
public class DisciplinaController {
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	
	@GetMapping ("/listar")
	public ModelAndView listarDisciplinas() {
		
		ModelAndView mv = new ModelAndView("disciplina/listaDisciplina");
		mv.addObject("disciplinas", disciplinaService.searchAll());
		return mv;
	}
	
	
	
	@GetMapping ("/cadastrar")
	public ModelAndView cadastrarDisciplina() {
			
		ModelAndView mv = new ModelAndView("disciplina/cadastraDisciplina");
		mv.addObject("disciplina", new Disciplina());
		return mv;
		}
	
	
	@PostMapping ("/salvar")
	public ModelAndView salvarDisciplina( Disciplina disciplina) {
			
		disciplinaService.save(disciplina);
		return listarDisciplinas();
	}
	
	
	@GetMapping ("/deletar/{id}")
	public ModelAndView deletarDisciplina(@PathVariable("id") Integer id) {
				
		disciplinaService.delete(id);
		return listarDisciplinas();
	}
	
	
	@GetMapping ("/alterar/{id}")
	public ModelAndView alterarDisciplina(@PathVariable("id") Integer id) throws ObjectNotFoundException {
				
		ModelAndView mv = new ModelAndView("disciplina/alteraDisciplina");
		mv.addObject("disciplina", disciplinaService.search(id));
		return mv;
	}
	
	
	@PostMapping ("/alterar")
	public ModelAndView alterar(Disciplina disciplina) throws ObjectNotFoundException {
			
		disciplinaService.edit(disciplina);
		return listarDisciplinas();
	}


}
