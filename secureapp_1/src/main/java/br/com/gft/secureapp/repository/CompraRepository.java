package br.com.gft.secureapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gft.secureapp.model.Compra;
import br.com.gft.secureapp.model.User;

public interface CompraRepository extends JpaRepository<Compra, Long> {
		Iterable<Compra> findByUser(User user);
	 
	
}
