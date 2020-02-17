package br.com.gft.secureapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gft.secureapp.model.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long> {
}
