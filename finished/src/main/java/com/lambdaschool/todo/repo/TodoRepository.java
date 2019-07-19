package com.lambdaschool.todo.repo;

import com.lambdaschool.todo.model.Todo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoRepository extends CrudRepository<Todo, Long> {
    @Query(value = "SELECT * FROM todo WHERE userid=:userid", nativeQuery = true)
    List<Todo> getAllByID(long id);
}
