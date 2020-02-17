package br.com.gft.secureapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gft.secureapp.model.Role;


public interface RoleRepository extends JpaRepository<Role, Long>{
		default List<Role> findAllByUserId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
