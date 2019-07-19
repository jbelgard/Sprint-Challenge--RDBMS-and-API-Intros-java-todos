package com.lambdaschool.todo.service;

import com.lambdaschool.todo.model.Role;
import com.lambdaschool.todo.repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository repo;

    @Override
    public List<Role> findAll()
    {
        List<Role> list = new ArrayList<>();
        repo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }


    @Override
    public Role findRoleById(long id)
    {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }


    @Override
    public void delete(long id)
    {
        repo.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
        repo.deleteById(id);
    }


    @Transactional
    @Override
    public Role save(Role role)
    {
        return repo.save(role);
    }
}
