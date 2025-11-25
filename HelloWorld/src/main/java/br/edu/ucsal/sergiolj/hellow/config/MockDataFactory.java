package br.edu.ucsal.sergiolj.hellow.config;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.edu.ucsal.sergiolj.hellow.model.Task;
import br.edu.ucsal.sergiolj.hellow.repository.TaskRepository;


@Configuration

public class MockDataFactory implements CommandLineRunner{
	
	private final TaskRepository repository;
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(MockDataFactory.class);
	
	public MockDataFactory(TaskRepository repository) {
		this.repository = repository;
	}
	

	@Override
	public void run(String... args) throws Exception {
		logger.info("Creating database mockup data...");
		repository.saveAll(List.of(
				new Task(null, "Ao acordar", "Tomar os suplementos", false),
				new Task(null, "Ao acordar", "Beber 500ml de água", false),
				new Task(null, "Ao acordar", "Café da manhã com 2 ovos e duas claras, 80gr batata doce", false),
				new Task(null, "Antes de sair", "Molhar as plantas", false),
				new Task(null, "Antes de sair", "Colocar na mochila almoço", false),
				new Task(null, "Ao sair", "Checar se ficou algo ligado", false),
				new Task(null, "Ao sair", "Trancar a porta", false)
				));
		
		logger.info("Mockup data done. Total of registers : {}", repository.count());
		
	}

}
