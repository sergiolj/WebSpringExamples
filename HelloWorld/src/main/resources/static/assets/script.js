class Task{
	constructor(name, description){
		this.name = name;
		this.description = description;
	}
}

var arrayTarefas = [];

function addTask(){
	let nameInput = document.getElementById('task_name');
	let descriptionInput = document.getElementById('task_descr');
	
	let name = nameInput.value.trim();
	let description = descriptionInput.value.trim();
	
	
	if(name!=''){
		fetch('http://localhost:8080/tasks', {
		method:'POST',
		headers:{
			    'Content-Type': 'application/json'
			    },
			    body: JSON.stringify({
			        name: name,
			        description: description,
			    })
			})
			.then(response =>{
				if(response.ok){
					console.log("Tarefa Salva com sucesso");
					nameInput.value = "";
					descriptionInput.value = "";
				}else{
					console.error("Erro ao salvar tarefa!")
				}
			})
			.catch(error => console.error("Erro de rede", error));
	}else{
		alert("Nome da tarefa não pode ser vazio!")
	}
}

function getTaskList(){
	fetch('http://localhost:8080/tasks', {
		method:'GET',
		
	})
	.then(response => response.json())
	.then(data=>{
		arrayTarefas.length = 0;
		data.forEach(task => {
			arrayTarefas.push(new Task(
				task.id,
				task.name, 
				task.description, 
				task.done
			));
		})
		createDataFromDB();
	})
	.catch();
}

function createDataFromDB() {
    // 1. Captura a DIV que vai segurar a lista
    const listContainer = document.getElementById('main_task_list_enlisted');
    
    // 2. IMPORTANTE: Limpa o HTML atual para não duplicar itens ao atualizar
    listContainer.innerHTML = '';

    // 3. Itera sobre o array de tarefas (arrayTarefas)
    arrayTarefas.forEach(task => {
        
        // Cria o elemento visual da tarefa
        const taskCard = document.createElement('div');
        taskCard.className = 'task-card'; // Classe para estilizar no CSS
        
        // Verifica se tem descrição para não imprimir "undefined"
        const descriptionText = task.description ? task.description : '';

        // Monta o HTML interno (Título, Descrição e Botão Deletar)
        // Note o onclick="deleteTask(${task.id})" -> Ele passa o ID do banco para a função
        taskCard.innerHTML = `
            <div class="task-info">
                <h3 class="task-title">${task.name}</h3>
                <p class="task-desc">${descriptionText}</p>
            </div>
            <button class="btn-delete" onclick="deleteTask(${task.id})">
                🗑️
            </button>
        `;

        // 4. Adiciona o card dentro da lista na tela
        listContainer.appendChild(taskCard);
    });

    // 5. Atualiza os contadores (Total e Completas)
    updateCounters();
}

// Função auxiliar para atualizar aqueles números "(0)" e "2/4 completed"
function updateCounters() {
    const countDiv = document.querySelector('.main_task_list_count');
    if(countDiv) {
        countDiv.innerText = `(${arrayTarefas.length})`;
    }
}

// Função placeholder para o Delete (para o botão não dar erro)
function deleteTask(id) {
    if(confirm("Deseja excluir a tarefa " + id + "?")) {
        fetch(`http://localhost:8080/tasks/${id}`, { method: 'DELETE' })
        .then(res => {
            if(res.ok) { 
                getTaskList(); // Recarrega a lista
            }
        });
    }
}

getTaskList();
