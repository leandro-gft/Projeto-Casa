package br.com.gft.secureapp.controller;

import java.util.Arrays;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gft.secureapp.model.Carrinho;
import br.com.gft.secureapp.model.Evento;
import br.com.gft.secureapp.model.Genero;
import br.com.gft.secureapp.repository.CarrinhoRepository;
import br.com.gft.secureapp.repository.EventoRepository;

@Controller
public class CarrinhoController {
	
	
	@Autowired // injeção de dependencia
	private EventoRepository eventos;
	@Autowired // injeção de dependencia
	private CarrinhoRepository carrinhos;
	
	
	@GetMapping("/carrinho") 
	public ModelAndView cart() {
		List<Carrinho> todosCarrinhos = carrinhos.findAll();
		ModelAndView mv = new ModelAndView("carrinho");
		
		mv.addObject("carrinhos", todosCarrinhos);
		mv.addObject(new Carrinho()); //para usar o th:field
		return mv;
	}	
	
	@GetMapping("/carrinho/{id}")
	public ModelAndView toCart(@PathVariable("id") Long id, Carrinho carrinho, RedirectAttributes redirectAttributes) {
		ModelAndView mv  = new ModelAndView("redirect:/evento"); 

		Evento evento = eventos.findById(id).get();
		carrinho.setEvento(evento);
		if (carrinho.getEvento().getCapacidade() == 0) {
			redirectAttributes.addFlashAttribute("insucesso", "Quantidade insuficiente de ingressos disponíveis.");
			return mv;
		}				
		carrinhos.save(carrinho);
		mv.addObject("carrinhos", carrinho);
		redirectAttributes.addFlashAttribute("sucesso","Produto adicionado no carrinho!");
		
		return mv;
	}
	
	
	@GetMapping("/carrinho/delete/{id}") 
	public String deleteCart(@PathVariable Long id) {
		carrinhos.deleteById(id);
	      return "redirect:/carrinho";
	    }
	

	@ModelAttribute("todosGenero")
	public List<Genero> todosGenero(){
		return Arrays.asList(Genero.values());
		
		
	}
	
}
