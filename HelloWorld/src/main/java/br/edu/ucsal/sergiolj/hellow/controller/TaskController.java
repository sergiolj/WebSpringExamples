package br.edu.ucsal.sergiolj.hellow.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ucsal.sergiolj.hellow.model.Task;
import br.edu.ucsal.sergiolj.hellow.service.TaskService;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController {
	
	private TaskService service;

	public TaskController(TaskService service) {
		super();
		this.service = service;
	}
	
	@GetMapping
	public List<Task> listAll(){
		return service.listAll();
	}
	
	@PostMapping
	public ResponseEntity<Task> create(@RequestBody Task task){
		Task newTask = service.add(task);
		return ResponseEntity.status(HttpStatus.CREATED).body(task);
	}
	
	@PutMapping
	public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody Task task){
		
		return service.update(id, task)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	@DeleteMapping
	public ResponseEntity<Task> delete(@PathVariable Long id){
		if(service.delete(id)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
}
