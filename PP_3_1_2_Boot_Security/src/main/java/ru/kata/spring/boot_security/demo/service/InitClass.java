package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleCrudRepository;

import java.util.ArrayList;
import java.util.List;


@Component
public class InitClass {
    private UserService userService;
    private RoleCrudRepository roleCrudRepository;

    @Autowired
    public InitClass(UserService userService, RoleCrudRepository roleCrudRepository) {
        this.userService = userService;
        this.roleCrudRepository = roleCrudRepository;
    }

    @javax.annotation.PostConstruct
    public void init() {

        Role roleAdmin = new Role("ROLE_ADMIN");
        roleCrudRepository.save(roleAdmin);
        Role roleUser = new Role("ROLE_USER");
        roleCrudRepository.save(roleUser);

        List<Role> roleSet1 = new ArrayList<>();
        roleSet1.add(roleAdmin);
        roleSet1.add(roleUser);
        List<Role> roleSet2 = new ArrayList<>();
        roleSet2.add(roleAdmin);
        List<Role> roleSet3 = new ArrayList<>();
        roleSet3.add(roleUser);

        userService.saveUser(new User("max1@mail.ru", "max1", "Иван", "Иванов",  roleSet1));
        userService.saveUser(new User("max2@mail.ru", "max2", "Петр", "Петров",  roleSet2));
        userService.saveUser(new User("max3@mail.ru", "max3", "Макс", "Сидоров",  roleSet3));


    }


}
