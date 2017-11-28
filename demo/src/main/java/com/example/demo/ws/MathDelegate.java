package com.example.demo.ws;

import org.example.math.Add;
import org.example.math.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dataaccess.RecipeTechDelegate;

@Service
public class MathDelegate {
	@Autowired RecipeTechDelegate delegate;
	
	public Answer sum(Add add) {
		Answer answer = new Answer();
		answer.setResult(add.getX() + add.getY());
		System.out.println("@@@@@@@@@@@@@@@@@@");
		System.out.println(delegate.getAllRecipe());
		System.out.println(delegate.getSpecificRecipe(1));
		System.out.println("@@@@@@@@@@@@@@@@@@");
		return answer;
	}
}
