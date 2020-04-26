package Mian;

import Mian.model.Task;
import Mian.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/tasks/")
    public synchronized ResponseEntity<List<Task>> list() {

        Iterable<Task> taskIterable = taskRepository.findAll();
        ArrayList<Task> taskList = new ArrayList<>();

        taskIterable.forEach(taskList::add);

        return !taskList.isEmpty()
                ? new ResponseEntity<>(taskList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/tasks/")
    public synchronized ResponseEntity<?> add(@RequestBody Task task) {

        taskRepository.save(task);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/tasks/{id}")
    public synchronized ResponseEntity<?> get(@PathVariable int id) {

        Optional<Task> optionalTask = taskRepository.findById(id);

        return (optionalTask.isPresent())
                ? new ResponseEntity<>(optionalTask.get(), HttpStatus.OK)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }

    @PutMapping("/tasks/{id}")
    public  synchronized ResponseEntity<?> update(@PathVariable int id,
                                                  @RequestParam String name,
                                                  @RequestParam String purpose) {

        Optional<Task> optionalTask = taskRepository.findById(id);

        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();

            if (!name.equals("")) {
                task.setName(name);
            }

            if (!purpose.equals("")) {
                task.setPurpose(purpose);
            }

            taskRepository.save(task);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/tasks/{id}")
    public synchronized ResponseEntity<?> delete(@PathVariable int id) {

        if (taskRepository.existsById(id)) {

            taskRepository.deleteById(id);
            return  new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
