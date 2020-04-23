package Mian;

import model.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    @GetMapping("/tasks/")
    public List<Task> list() {
        return Storage.getAllTask();
    }

    @PostMapping("/tasks/")
    public int add(Task task) {
        return Storage.addTask(task);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity get(@PathVariable int id) {

        Task task = Storage.getTask(id);
        return (task == null) ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(null) :
                new ResponseEntity(task, HttpStatus.OK);
    }
}
