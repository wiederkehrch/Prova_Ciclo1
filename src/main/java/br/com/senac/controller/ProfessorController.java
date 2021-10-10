package br.com.senac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.domain.Professor;
import br.com.senac.service.ProfessorService;
import javassist.tools.rmi.ObjectNotFoundException;


@Controller                          
@RequestMapping("/professor") 
public class ProfessorController {
	
	@Autowired
	private ProfessorService professorService;
	
	
	@GetMapping ("/listar")
	public ModelAndView listarProfessores() {
		
		ModelAndView mv = new ModelAndView("professor/listaProfessor");
		mv.addObject("professores", professorService.searchAll());
		return mv;
	}
	
	
	
	@GetMapping ("/cadastrar")
	public ModelAndView cadastrarProfessor() {
			
		ModelAndView mv = new ModelAndView("professor/cadastraProfessor");
		mv.addObject("professor", new Professor());
		return mv;
		}
	
	
	@PostMapping ("/salvar")
	public ModelAndView salvarProfessor( Professor professor) {
			
		professorService.save(professor);
		return listarProfessores();
	}
	
	
	@GetMapping ("/deletar/{id}")
	public ModelAndView deletarProfessor(@PathVariable("id") Integer id) {
				
		professorService.delete(id);
		return listarProfessores();
	}
	
	
	@GetMapping ("/alterar/{id}")
	public ModelAndView alterarProfessor(@PathVariable("id") Integer id) throws ObjectNotFoundException {
				
		ModelAndView mv = new ModelAndView("professor/alteraProfessor");
		mv.addObject("professor", professorService.search(id));
		return mv;
	}
	
	
	@PostMapping ("/alterar")
	public ModelAndView alterar(Professor professor) throws ObjectNotFoundException {
			
		professorService.edit(professor);
		return listarProfessores();
	}

}
