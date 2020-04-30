package com.bridgelabz.userservices.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.userservices.model.UsersEntity;


@Repository
public interface IUsersRepository extends CrudRepository<UsersEntity,Long>{

	Optional<UsersEntity> findOneByEmail(String email);

	
}