package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleService {


    void saveRole(Role role);

    void deleteRole(Role role);

    public Role getRoleByRoleName(String roleName);

    public List<Role> findAllByRoleNameNotNull();
}
