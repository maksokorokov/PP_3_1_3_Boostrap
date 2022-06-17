package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleCrudRepository;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService{


    private final RoleCrudRepository roleCrudRepository;

    @Autowired
    public RoleServiceImpl(RoleCrudRepository roleCrudRepository) {
        this.roleCrudRepository = roleCrudRepository;
    }

    @Override
    public void saveRole(Role role) {
        roleCrudRepository.save(role);
    }


    @Override
    public Set<Role> findAllByRoleNameNotNull() {
        return roleCrudRepository.findAll();
    }
}
