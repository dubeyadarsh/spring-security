package com.micro.question_service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.micro.question_service.models.QuestionModel;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionModel,Integer> {
	List<QuestionModel> findByCategory(String category);
}
