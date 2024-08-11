package com.micro.question_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro.question_service.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel,Integer>{
	UserModel findByUserName(String userName);
}