package br.com.gft.secureapp.controller;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gft.secureapp.model.User;
import br.com.gft.secureapp.repository.UserRepository;



@Controller
public class HomeController {

	@Autowired
	private UserRepository users;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@RequestMapping({"/", "/home"})
	public String home() {
		return "home";
	}

	
	@RequestMapping("/login")
	public String loginPage() {
		return "login";
	}

	@RequestMapping("/logout")
	public String logoutPage() {
		return "logout";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView register(User user) {
				
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setConfirmPassword(bCryptPasswordEncoder.encode(user.getConfirmPassword()));
		users.save(user);
		
		ModelAndView mv = new ModelAndView("registration");
		mv.addObject("mensagem", "Cadastro realizado com sucesso");
		return mv;
	}

	@RequestMapping("/registration")
	public String registration() {
		return "registration";
	}

		
	 @ExceptionHandler(ConstraintViolationException.class)
	    public ModelAndView usuarioExiste(RedirectAttributes redirectAttributes){
		 ModelAndView mv = new ModelAndView("redirect:/registration");
	        redirectAttributes.addFlashAttribute("username", "Usuário já existe. Tente novamente!");
	        return mv;
	    }
	 
	 
	 
	 
	 
}
