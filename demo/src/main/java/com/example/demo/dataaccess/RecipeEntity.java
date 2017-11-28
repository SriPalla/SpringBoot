package com.example.demo.dataaccess;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "recipes", schema = "demo")
public class RecipeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "recipe_id")
	private Integer id;
	@Column(name = "recipe_name")
	private String name;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
