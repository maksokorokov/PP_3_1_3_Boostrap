package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleCrudRepository;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;


import java.util.Set;

@Controller
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }



    @GetMapping("/admin")
    public String findAll(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", userService.getUser(user.getId()));
        model.addAttribute("allUsers", userService.getUsersList());
        model.addAttribute("newUser", new User());
        model.addAttribute("rol", roleService.findAllByRoleNameNotNull());

        return "boost/admin2";
    }


    @GetMapping("/user")
    public ModelAndView userPage(@AuthenticationPrincipal User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("boost/user");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    //

    @PostMapping("/admin/create")
    public String createUser(User newUser, @RequestParam("roles") Set<Role> roles) {
        newUser.setRole(roles);
        userService.saveUser(newUser);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/delete")
    public String deleteUser(@RequestParam(value = "id") long id) {
        userService.deleteUser(userService.getUser(id));
        return "redirect:/admin";
    }

    @PutMapping("/admin/update")
    public String updateUser(@ModelAttribute("user1") User user1,
                             @RequestParam("roles") Set<Role> roles) {
        user1.setRole(roles);
        userService.saveUser(user1);
        return "redirect:/admin";
    }

}
