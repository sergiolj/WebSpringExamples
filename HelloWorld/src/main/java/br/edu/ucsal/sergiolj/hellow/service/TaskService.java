package br.edu.ucsal.sergiolj.hellow.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.edu.ucsal.sergiolj.hellow.model.Task;
import br.edu.ucsal.sergiolj.hellow.repository.TaskRepository;

@Service
public class TaskService {

	private final TaskRepository repository;
	
	public TaskService(TaskRepository repository) {
		this.repository = repository;
		
	}
	
	public Task add(Task task) {
		return repository.save(task);
	}
	
	public List<Task> listAll(){
		return repository.findAll();
	}
	
	public boolean delete(Long id) {
		if(repository.existsById(id)) {
			repository.deleteById(id);
			return true;
		}
		return false;
	}
	
	public Optional<Task> update(Long id, Task updatedTask){
		return repository.findById(id).map(task->{
			task.setDescription(updatedTask.getDescription());
			task.setDone(updatedTask.isDone());
			return repository.save(task);
		});
	}
}
