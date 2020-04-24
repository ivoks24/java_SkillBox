package Mian;

import model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Storage {

    private static final AtomicInteger currentId = new AtomicInteger(1);
    private static final ConcurrentHashMap<Integer, Task> tasks = new ConcurrentHashMap<>();

    public static List<Task> getAllTask() {
        return new ArrayList<>(tasks.values());
    }

    public static void addTask(Task task) {

        int id = currentId.get();
        currentId.incrementAndGet();

        task.setId(id);
        tasks.put(id, task);
    }

    public static Task getTask(int taskId) {
        return tasks.getOrDefault(taskId, null);
    }

    public static boolean update(int taskID, String name, String purpose) {

        Task task = getTask(taskID);
        if (task != null) {
            task.setName(name);
            task.setPurpose(purpose);
            return true;
        }
        return false;
    }

    public static boolean delete(int taskID) {

        Task task = tasks.remove(taskID);
        return task != null;
    }
}
