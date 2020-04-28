package Mian;

import Mian.model.Task;
import Mian.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class DefaultController {

    @Autowired
    private TaskRepository taskRepository;

//    @Value("${someParameter.value}")
//    private Integer someParameter;

    @RequestMapping("/")
    public String index(Model model)
    {
        Iterable<Task> taskIterable = taskRepository.findAll();
        ArrayList<Task> taskList = new ArrayList<>();

        taskIterable.forEach(taskList::add);

        model.addAttribute("tasks", taskList);
        model.addAttribute("tasksCount", taskList.size());
//        model.addAttribute("someParameter", someParameter);

        return "index";
    }
}
