package br.com.senac.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senac.domain.Curso;
import br.com.senac.service.CursoService;
import br.com.senac.service.DisciplinaService;
import br.com.senac.service.ProfessorService;
import javassist.tools.rmi.ObjectNotFoundException;


@Controller                        
@RequestMapping("/curso")
public class CursoController {
	
	@Autowired
	private CursoService cursoService;
	
	
	@Autowired
	private ProfessorService professorService;
	
	
	@Autowired
	private DisciplinaService disciplinaService;
	
	
	
	@GetMapping ("/listar")
	public ModelAndView listarCursos() {
		
		ModelAndView mv = new ModelAndView("curso/listaCurso");
		mv.addObject("cursos", cursoService.searchAll());
		return mv;
	}
	
	
	
	@GetMapping ("/cadastrar")
	public ModelAndView cadastrarCurso() {
			
		ModelAndView mv = new ModelAndView("curso/cadastraCurso");
		mv.addObject("professores", professorService.searchAll());
		mv.addObject("disciplinas", disciplinaService.searchAll());
		mv.addObject("curso", new Curso());
		return mv;
		}
	
	
	@PostMapping ("/salvar")
	public ModelAndView salvarCurso( Curso curso) {
			
		cursoService.save(curso);
		return listarCursos();
	}
	
	
	@GetMapping ("/deletar/{id}")
	public ModelAndView deletarCurso(@PathVariable("id") Integer id) {
				
		cursoService.delete(id);
		return listarCursos();
	}
	
	
	@GetMapping ("/alterar/{id}")
	public ModelAndView alterarCurso(@PathVariable("id") Integer id) throws ObjectNotFoundException {
				
		ModelAndView mv = new ModelAndView("curso/alteraCurso");
		mv.addObject("curso", cursoService.search(id));
		mv.addObject("professores", professorService.searchAll());
		mv.addObject("disciplinas", disciplinaService.searchAll());
		return mv;
	}
	
	
	@PostMapping ("/alterar")
	public ModelAndView alterar(Curso curso) throws ObjectNotFoundException {
			
		cursoService.edit(curso);
		return listarCursos();
	}

}
