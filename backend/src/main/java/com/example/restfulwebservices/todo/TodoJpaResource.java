package com.example.restfulwebservices.todo;

import com.example.restfulwebservices.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class TodoJpaResource {

    @Autowired
    private TodoHardCodedService todoService;

    @Autowired
    TodoRepository todoRepository;

    @GetMapping("/jpa/users/{username}/todos")
    public List<Todo> getAllTodos(@PathVariable String username) {
        return todoRepository.findByUsername(username);
    }

    @GetMapping("/jpa/users/{username}/todos/{id}")
    public Todo getTodo(@PathVariable String username, @PathVariable long id) {
        return todoRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/jpa/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable Long id) {
        todoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/jpa/users/{username}/todos/{id}")
    public ResponseEntity<Todo> updateTodo(
            @PathVariable String username,
            @PathVariable long id, @RequestBody Todo todo) {
        todo.setUsername(username);
        Todo todoUpdated = todoRepository.save(todo);
        return new ResponseEntity<>(todoUpdated, HttpStatus.OK);
    }

    @PostMapping("/jpa/users/{username}/todos")
    public ResponseEntity<Void> createTodo(@PathVariable String username,
                                           @RequestBody Todo todo) {
        todo.setUsername(username);
        Todo createTodo = todoRepository.save(todo);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(createTodo.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
