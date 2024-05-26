package com.example.restfulwebservices.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URL;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TodoResource {

    @Autowired
    private TodoHardCodedService todoService;

    @GetMapping("/users/{username}/todos")
    public List<Todo> getAllTodos(@PathVariable String username){
        //Thread.sleep(3000);
    return todoService.findAll();
    }

    @GetMapping("/users/{username}/todos/{id}")
    public Todo getTodo(@PathVariable String username, @PathVariable long id){
        //Thread.sleep(3000);
        return todoService.findById(id);
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable Long id) {
        Todo todo = todoService.deleteById(id);
        if (todo != null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable String username,
                                           @PathVariable long id, @RequestBody Todo todo) {
        Todo todoUpdated = todoService.save(todo);
        return new ResponseEntity<Todo>(todo, HttpStatus.OK);
    }

    @PostMapping("/users/{username}/todos")
    public ResponseEntity<Void> creatTodo(@PathVariable String username,
                                          @RequestBody Todo todo) {
        Todo createTodo = todoService.save(todo);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{/id}").buildAndExpand(createTodo.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

}
