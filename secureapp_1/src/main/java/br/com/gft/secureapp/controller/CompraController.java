package br.com.gft.secureapp.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.gft.secureapp.model.Carrinho;
import br.com.gft.secureapp.model.Compra;
import br.com.gft.secureapp.model.Genero;
import br.com.gft.secureapp.model.User;
import br.com.gft.secureapp.repository.CarrinhoRepository;
import br.com.gft.secureapp.repository.CompraRepository;
import br.com.gft.secureapp.repository.EventoRepository;
import br.com.gft.secureapp.repository.UserRepository;

@Controller
public class CompraController {

			
	@Autowired // injeção de dependencia
	private CarrinhoRepository carrinhos;

	@Autowired // injeção de dependencia
	private CompraRepository compras;

	@Autowired // injeção de dependencia
	private EventoRepository eventos;
	
	@Autowired // injeção de dependencia
	private UserRepository users;
	
	

	@GetMapping("/historico")
	public ModelAndView historico(@AuthenticationPrincipal UserDetails userDetails) {
		User user = users.findByUsername(userDetails.getUsername());
		Iterable<Compra> todasCompras = compras.findByUser(user);
		ModelAndView mv = new ModelAndView("historico");
		mv.addObject("compras", todasCompras);
		mv.addObject(new Compra());
		return mv;
	}

	@PostMapping("/historico")
	public ModelAndView salvarCart(@Validated Compra compra) {
		ModelAndView mv = new ModelAndView("historico");
		Carrinho carrinho = new Carrinho();
		carrinho.setQtd(carrinho.getQtd());
		compras.save(compra);
		return mv;
	}

	@PostMapping("/historico/{id}")
	public ModelAndView confirma(@PathVariable("id") Long id, int qtd, Compra compra,
		RedirectAttributes redirectAttributes, @AuthenticationPrincipal UserDetails userDetails) {
		ModelAndView mv = new ModelAndView("redirect:/carrinho");
		User user = users.findByUsername(userDetails.getUsername());
		
		// atualizando a quantidade de acordo com a escolha do usuario
		Carrinho carrinho = carrinhos.findById(id).get();
		carrinho.setQtd(qtd);
		carrinhos.save(carrinho);

		// pegando os itens do carrinho
		compra.setUser(user);
		compra.setCasa(carrinho.getEvento().getCasa());
		compra.setGenero(carrinho.getEvento().getGenero());
		compra.setData(carrinho.getEvento().getData());
		compra.setNomeEvento(carrinho.getEvento().getNomeEvento());
		compra.setQtd(carrinho.getQtd());
		compra.setValor(carrinho.getEvento().getValor());
		
		
		// atualizando o estoque/capacidade
		if (carrinho.getEvento().getCapacidade() < compra.getQtd()) {

			redirectAttributes.addFlashAttribute("insucesso", "Quantidade insuficiente de ingressos disponíveis.");
			return mv;
		}
		carrinho.getEvento().setCapacidade(carrinho.getEvento().getCapacidade() - compra.getQtd());
		eventos.save(carrinho.getEvento());

		redirectAttributes.addFlashAttribute("sucesso", "Compra realizada com sucesso!");
		compras.save(compra);
		carrinhos.deleteById(id);
		return mv;

	}

	@GetMapping("/historico/delete/{id}")
	public String deleteCompra(@PathVariable Long id) {
		compras.deleteById(id);
		return "redirect:/historico";
	}

	@ModelAttribute("todosGenero")
	public List<Genero> todosGenero() {
		return Arrays.asList(Genero.values());
	}

}
