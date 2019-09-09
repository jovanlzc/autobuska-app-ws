package com.qubikapp.app.ws.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.qubikapp.app.ws.entity.PrevoznikEntity;

@Repository
public interface PrevoznikRepository extends CrudRepository<PrevoznikEntity, Long> {

}
