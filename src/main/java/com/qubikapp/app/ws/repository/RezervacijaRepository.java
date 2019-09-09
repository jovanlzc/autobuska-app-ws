package com.qubikapp.app.ws.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.qubikapp.app.ws.entity.RezervacijaEntity;

@Repository
public interface RezervacijaRepository extends JpaRepository<RezervacijaEntity, String>{
	RezervacijaEntity findByRezervacijaId(String rezervacijaId);
}
