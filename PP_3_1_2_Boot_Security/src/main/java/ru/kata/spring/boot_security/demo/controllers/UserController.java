package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleCrudRepository;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class UserController {
    private UserService userService;
//    private RoleCrudRepository roleCrudRepository;
    private RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/admin")
    public String findAll(Model model) {
        model.addAttribute("allUsers", userService.getUsersList());
        return "user-list";
    }



//    @GetMapping("/user")
//    public ModelAndView userPage(@AuthenticationPrincipal User user) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("user");
//        modelAndView.addObject("user", user);
//        return modelAndView;
//    }

    @GetMapping("/user")
    public ModelAndView userPage(@AuthenticationPrincipal User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("boost/user");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @GetMapping("/admin/edit/{userId}")
    public ModelAndView userEditPage(@PathVariable("userId") Long userId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edituser");
        modelAndView.addObject("user", userService.getUser(userId));
        modelAndView.addObject("roles", roleService.findAllByRoleNameNotNull());
        return modelAndView;
    }

    @PutMapping("/admin/edit")
    public ModelAndView userEdit(@ModelAttribute("user") User user,
                                 @RequestParam(value = "action") String action) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin");
        if (action.equals("save")) {
            userService.saveUser(user);
        }
        return modelAndView;
    }

    @GetMapping(value = "/admin/add")
    public ModelAndView userAddPage() {
        User newUser = new User();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adduser");
        modelAndView.addObject("newuser", newUser);
        modelAndView.addObject("roles", roleService.findAllByRoleNameNotNull());
        return modelAndView;
    }

    @PostMapping("/admin/add")
    public ModelAndView userAdd(@ModelAttribute("user") User user,
                                @RequestParam(value = "action") String action) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin");
        if (action.equals("save")) {
            userService.saveUser(user);
        }
        return modelAndView;
    }

    @DeleteMapping("/admin/delete/{userId}")
    public String deleteUser(@PathVariable("userId") Long id){
        userService.deleteUser(userService.getUser(id));
        return "redirect:/admin";
    }


}
