package com.lambdaschool.todo.service;

import com.lambdaschool.todo.model.Todo;
import com.lambdaschool.todo.repo.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "todoService")
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository repo;

    @Override
    public List<Todo> findAllById(long id) {
        List<Todo> todoList = new ArrayList<>();
        repo.getAllByID(id).iterator().forEachRemaining(todoList::add);
        return todoList;
    }

    @Override
    public Todo findTodoById(long id) {
        return repo.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Todo save(Todo todo) {
        return repo.save(todo);
    }

    @Override
    public void delete(long id) {
        if (repo.findById(id).isPresent()){
            repo.deleteById(id);
        }else{
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Todo update(Todo todo, long id) {
        Todo currentTodo = repo.findById(id).orElseThrow(EntityNotFoundException::new);
        if (todo.getDatestarted() != null){
            currentTodo.setDatestarted(todo.getDatestarted());
        }
        if (todo.getDescription() != null){
            currentTodo.setDescription(todo.getDescription());
        }
        if (todo.isCompleted()){
            currentTodo.setCompleted(true);
        }else{
            currentTodo.setCompleted(false);
        }
        repo.save(currentTodo);
        return currentTodo;
    }
}
