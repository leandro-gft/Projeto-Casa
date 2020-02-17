package br.com.gft.secureapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gft.secureapp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

 

}
