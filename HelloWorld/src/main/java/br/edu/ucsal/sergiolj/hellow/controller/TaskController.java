package br.edu.ucsal.sergiolj.hellow.controller;

import java.util.List;
import java.util.Map;

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
		service.add(task);
		return ResponseEntity.status(HttpStatus.CREATED).body(task);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody Task task){
		return service.update(id, task)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		try {
			if(service.delete(id)) {
				return ResponseEntity.ok().body("Tarefa apagada da lista");
			}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Não foi encontrada uma tarefa com esse ID.");
		}
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Houve um erro inesperado");
		}
	}
	
	@PutMapping("/{id}/done")
	public ResponseEntity<?> updateTaskDone(@PathVariable Long id, @RequestBody Map<String, Boolean> requestBody){
		boolean done = requestBody.get("done");
		boolean updated = service.updateTaskDone(id, done);
		
		if(updated) {
			return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.notFound().build();
		}
			
	}
}
