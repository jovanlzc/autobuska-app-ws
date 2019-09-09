package com.qubikapp.app.ws.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.qubikapp.app.ws.entity.AutobuskaLinijaEntity;

@Repository
public interface LinijaRepository extends CrudRepository<AutobuskaLinijaEntity, String>{
	AutobuskaLinijaEntity findByLinijaId(String id);
}
