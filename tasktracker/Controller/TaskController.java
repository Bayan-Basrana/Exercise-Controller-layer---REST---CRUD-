package com.example.tasktracker.Controller;

import com.example.tasktracker.ApiResponse.ApiResponse;
import com.example.tasktracker.Model.Task;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {
ArrayList<Task>  tasks = new ArrayList<>();

@GetMapping("/get")
public ArrayList<Task> getTasks() {
    return tasks;
}
@PostMapping("/add")
public ApiResponse addTask (@RequestBody Task task) {
    tasks.add(task);
    return new ApiResponse("Task added successfully");
}
@PutMapping("/put/{index}")
public ApiResponse updateTask(@PathVariable int index , @RequestBody Task task) {
 tasks.set(index, task);
 return new ApiResponse("Task updated successfully");
}

@DeleteMapping("/delete/{index}")
public ApiResponse deleteTask(@PathVariable int index) {
    tasks.remove(index);
    return new ApiResponse("Task deleted successfully");
}
@PutMapping("/changStatus")
public ApiResponse ChangTaskByStatus( ) {
for(Task task1 : tasks) {
    if (task1.isStatus()==false)
  task1.setStatus(true);}
return new ApiResponse("change status successfully");
}
@GetMapping("/search/{title}")
public Task getTaskByTitle(@PathVariable String title) {
    for(Task task : tasks) {
        if (task.getTitle().equals(title))
     return task;}
    return null;
}

}
