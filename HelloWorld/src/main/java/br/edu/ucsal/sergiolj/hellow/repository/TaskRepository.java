package br.edu.ucsal.sergiolj.hellow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ucsal.sergiolj.hellow.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
	
	List<Task> findByDone(boolean done);

}
