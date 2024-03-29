package br.com.lucassacerdote.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import lombok.var;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  @Autowired
  private ITaskRepository taskRepository;

  @PostMapping("/")
  public TaskModel create (@RequestBody TaskModel taskModel, HttpServletRequest request) {
    System.out.println("Chegou no controller" + request.getAttribute("idUser"));
    var idUser = request.getAttribute("idUser");
    taskModel.setIdUser((UUID)idUser);

    var currentDate = LocalDateTime.now();
    //'10/11/2023' Current
    //10/10/2023 startAt
    if (currentDate.isAfter(taskModel.getStartAt()));{
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
    .body("a data de inicio deve ser maior do que a atual");
    }


var task = this.taskRepository.save(taskModel);
 return ResponseEntity.status(HttpStatus.OK).body(task);
  
}
