package br.edu.ucsal.sergiolj.hellow.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_tasks")
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message = "Name cannot be blank")
	private String name;
	
	private String description;
	private boolean done = false;
	
	
	public Task() {}
	
	
	public Task(Long id, String name, String description, boolean done) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.done = done;
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}

	public Long getId() {
		return id;
	}
	
	

}
