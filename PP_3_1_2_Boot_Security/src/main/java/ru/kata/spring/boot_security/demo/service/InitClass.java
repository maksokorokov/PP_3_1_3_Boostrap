package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleCrudRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
public class InitClass {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public InitClass(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @javax.annotation.PostConstruct
    public void init() {

        Role roleAdmin = new Role("ROLE_ADMIN");
        roleService.saveRole(roleAdmin);
        Role roleUser = new Role("ROLE_USER");
        roleService.saveRole(roleUser);

        Set<Role> roleSet1 = new HashSet<>();
        roleSet1.add(roleAdmin);
        roleSet1.add(roleUser);
        Set<Role> roleSet2 = new HashSet<>();
        roleSet2.add(roleAdmin);
        Set<Role> roleSet3 = new HashSet<>();
        roleSet3.add(roleUser);

        userService.saveUser(new User("max1@mail.ru", "max1", "Иван", "Иванов",  roleSet1));
        userService.saveUser(new User("max2@mail.ru", "max2", "Петр", "Петров",  roleSet2));
        userService.saveUser(new User("max3@mail.ru", "max3", "Макс", "Сидоров",  roleSet3));


    }


}
