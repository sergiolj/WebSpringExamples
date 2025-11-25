package br.edu.ucsal.sergiolj.hellow.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.ucsal.sergiolj.hellow.model.Task;
import br.edu.ucsal.sergiolj.hellow.service.TaskService;

@Controller
public class TaskViewControler {
	
	private final TaskService service;
	
	public TaskViewControler(TaskService service) {
		this.service = service;
	}
	
//	@GetMapping("/")
//	public String indexPage(Model model) {
//		List<Task> tasks = service.listAll();
//		model.addAttribute("tasks", tasks);
//		model.addAttribute("totalOfTasks", tasks.size());
//		return "index";
//	}
//	
//	@GetMapping("/about")
//	public String aboutPage() {
//		return "about";
//	}
	
	@GetMapping("/tasks/view")
	public String getTaskList(Model model) {
		List<Task> tasks = service.listAll();
		model.addAttribute("tasks", tasks);
		model.addAttribute("totalOfTasks", tasks.size());
		
		//Informa o nome do template a ser criado, um html na pasta templates.
		return "taskViewList";
	}

}
