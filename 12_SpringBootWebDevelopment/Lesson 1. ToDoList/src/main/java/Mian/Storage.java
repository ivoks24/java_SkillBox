package Mian;

import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Storage {

    private static int currentId = 1;
    private static HashMap<Integer, Task> tasks = new HashMap<>();

    public static List<Task> getAllTask() {

        return new ArrayList<>(tasks.values());
    }

    public static int addTask(Task task) {

        task.setId(currentId);
        tasks.put(currentId, task);
        return currentId++;
    }

    public static Task getTask(int taskId) {

        return tasks.getOrDefault(taskId, null);
    }
}
