package com.lambdaschool.todo.repo;

import com.lambdaschool.todo.model.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo, Long> {
}
