package br.com.gft.secureapp.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gft.secureapp.model.Casa;
import br.com.gft.secureapp.model.Evento;
import br.com.gft.secureapp.model.Genero;
import br.com.gft.secureapp.repository.CasaRepository;
import br.com.gft.secureapp.repository.EventoRepository;

@Controller
@RequestMapping("/evento")
public class EventoController {

	@Autowired // injeção de dependencia
	private EventoRepository eventos;

	@Autowired // injeção de dependencia
	private CasaRepository casas;
	
	
	@PostMapping
	public ModelAndView salvarEvento(Evento evento, RedirectAttributes redirectAttributes) {
		
		ModelAndView mv = new ModelAndView("redirect:/evento");
		eventos.save(evento);
        redirectAttributes.addFlashAttribute("sucesso","Evento salvo com sucesso!");
	return mv;
	}

	@GetMapping
	public ModelAndView listaEventos() {
		List<Evento> todasEventos = eventos.findAll();
		List<Casa> todasCasas = casas.findAll();
		ModelAndView mv = new ModelAndView("evento");
		mv.addObject("eventos", todasEventos);
		mv.addObject("casas", todasCasas);
		mv.addObject(new Evento()); //para usar o th:field
		return mv;
	}	
	@GetMapping("/{id}")
	public 	ModelAndView editEvento(@PathVariable("id") Long id) {
		List<Evento> todosEventos = eventos.findAll();
		List<Casa> todasCasas = casas.findAll();
		Evento evento = eventos.findById(id).get();
		ModelAndView mv = new ModelAndView("evento");
		mv.addObject("casas", todasCasas);
	    mv.addObject("eventos", todosEventos);
	    mv.addObject(evento);
	    return mv;
	}
	
	@PostMapping("/{id}") //
	public String updateEvento(@PathVariable("id") Long id, @Valid Evento evento, 
	  BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        evento.setId(id);
	        return "evento";
	    }	         
	    eventos.save(evento);
	    model.addAttribute("evento", eventos.findAll());
	    return "evento";
	}

	@GetMapping("/delete/{id}") 
	public ModelAndView deleteEvento(@PathVariable Long id) {
	ModelAndView mv = new ModelAndView("redirect:/evento");
	   eventos.deleteById(id);
	      return mv;
	    }
	
	@ModelAttribute("todosGenero")
	public List<Genero> todosGenero(){
		return Arrays.asList(Genero.values());
	}
	
	 @InitBinder
	    public void initBinder(WebDataBinder binder) {
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	    }

}