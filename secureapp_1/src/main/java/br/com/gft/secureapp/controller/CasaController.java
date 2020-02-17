	package br.com.gft.secureapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gft.secureapp.model.Casa;
import br.com.gft.secureapp.repository.CasaRepository;



@Controller
@RequestMapping("/casa")
public class CasaController {



	@Autowired // injeção de dependencia
	private CasaRepository casas;



	@GetMapping("/delete/{id}") 
	public String deleteCasa(@PathVariable Long id, RedirectAttributes redirectAttributes) {
	   casas.deleteById(id);

       redirectAttributes.addFlashAttribute("sucesso","Casa de show excluída com sucesso!");
	      return "redirect:/casa";
	    }

	@PostMapping
	public ModelAndView salvarCasa(@Validated Casa casa, RedirectAttributes redirectAttributes) {
		ModelAndView mv = new ModelAndView("redirect:/casa");
		casas.save(casa);

	       redirectAttributes.addFlashAttribute("sucesso","Casa de show salva com sucesso!");
		return mv;
	}

	@GetMapping
	public ModelAndView listaCasas() {
		List<Casa> todasCasas = casas.findAll();
		ModelAndView mv = new ModelAndView("casa");
		mv.addObject("casas", todasCasas);
		mv.addObject(new Casa());
		return mv;
	}	
	@GetMapping("/{id}")
	public 	ModelAndView editCasa(@PathVariable("id") Long id) {
		List<Casa> todasCasas = casas.findAll();
		Casa casa = casas.findById(id).get();
		ModelAndView mv = new ModelAndView("casa");

	    mv.addObject("casas", todasCasas);
	    mv.addObject(casa);
	    return mv;
	}
	
	@PostMapping("/{id}")
	public String updateCasa(@PathVariable("id") Long id, @Valid Casa casa, 
	  BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        casa.setId(id);
	        return "casa";
	    }
	         casas.save(casa);
	    model.addAttribute("casa", casas.findAll());
	    return "casa";
	}
	
}
