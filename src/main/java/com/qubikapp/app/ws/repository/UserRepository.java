package com.qubikapp.app.ws.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.qubikapp.app.ws.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, String> {
	UserEntity findByEmail(String email);
	UserEntity findByUserId(String userId);
	UserEntity findByEmailVerificationToken(String token);
}
