package com.qubikapp.app.ws.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.qubikapp.app.ws.entity.MestoEntity;

@Repository
public interface MestoRepository extends CrudRepository<MestoEntity, Long> {
	MestoEntity findByMestoId(Long mestoId);
}
