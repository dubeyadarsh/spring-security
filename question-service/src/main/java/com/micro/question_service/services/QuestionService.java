package com.micro.question_service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micro.question_service.models.QuestionModel;
import com.micro.question_service.repositories.QuestionRepository;

@Service
public class QuestionService {
@Autowired QuestionRepository quesRepo;

public String addQuestions(List<QuestionModel> qm) {
	try {
	quesRepo.saveAll(qm);
	return "Data saved Successfully";
	}catch(Exception e) {
		return "Something went wrong";
	}
}

public List<QuestionModel> getQuestions(String category) {
	List<QuestionModel> resList=quesRepo.findByCategory(category);
	return resList;
}
}
