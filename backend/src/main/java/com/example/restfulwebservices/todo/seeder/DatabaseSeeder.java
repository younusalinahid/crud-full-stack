package com.example.restfulwebservices.todo.seeder;

import com.example.restfulwebservices.todo.Todo;
import com.example.restfulwebservices.todo.repository.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class DatabaseSeeder {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseSeeder.class);

    @Autowired
    TodoRepository todoRepository;

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        logger.info("Inserting todo data");
        seedTodosData();
        logger.info("Finished inserting todo data");
    }

    private void seedTodosData() {

        Todo todo1 = new Todo("nahid", "Learn React", new Date(), false);
        Todo todo2 = new Todo("nahid", "Learn Java", new Date(), false);
        Todo todo3 = new Todo("nahid", "Learn Spring boot", new Date(), false);

        List<Todo> todos = Arrays.asList(todo1, todo2, todo3);
        todoRepository.saveAll(todos);

    }
}
