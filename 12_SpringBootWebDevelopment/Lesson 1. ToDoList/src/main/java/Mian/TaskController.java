package Mian;

import model.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    @GetMapping("/tasks/")
    public synchronized ResponseEntity<List<Task>> list() {
        List<Task> taskList = Storage.getAllTask();

        return !taskList.isEmpty()
                ? new ResponseEntity<>(taskList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/tasks/")
    public synchronized ResponseEntity<?> add(Task task) {

        Storage.addTask(task);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/tasks/{id}")
    public synchronized ResponseEntity<?> get(@PathVariable int id) {

        Task task = Storage.getTask(id);
        return (task == null)
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)
                : new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PutMapping("/tasks/{id}")
    public  synchronized ResponseEntity<?> update(@PathVariable int id,
                                                  @RequestParam String name,
                                                  @RequestParam String purpose) {

        final boolean updated = Storage.update(id, name, purpose);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/tasks/{id}")
    public synchronized ResponseEntity<?> delete(@PathVariable int id) {

        final boolean deleted = Storage.delete(id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
