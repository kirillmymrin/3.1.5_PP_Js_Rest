package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.GetMapping;
=======
import org.springframework.web.bind.annotation.*;
>>>>>>> origin/master
import ru.kata.spring.boot_security.demo.entities.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;
import java.security.Principal;

@Controller
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserServiceImpl userServiceImpl) {
        this.userService = userServiceImpl;
    }

    @GetMapping(value = "/admin")
    public String getUsers(ModelMap modelMap, Principal principal) {
        modelMap.addAttribute("users", userService.allUsers());
        modelMap.addAttribute("us", userService.loadUserByUsername(principal.getName()));
        return "admin";
    }

    @GetMapping("/new")
    public String getNewFrom(Model model, Principal principal) {
        model.addAttribute("user", new User());
        model.addAttribute("us", userService.loadUserByUsername(principal.getName()));
        return "newUser";
    }

<<<<<<< HEAD
=======
    @PostMapping
    public String save(@RequestParam("username") String name,
                       @RequestParam("lastName") String surname,
                       @RequestParam("age") Integer age,
                       @RequestParam("email") String email,
                       @RequestParam("password") String password,
                       @RequestParam("roles") String role) {

        User user =  userServiceImpl.getUserWithFields(name, surname, age, email, password, role);
        userServiceImpl.save(user);
        return "redirect:/admin";
    }


    @PostMapping("/admin/{id}")
    public String update(
            @RequestParam("username") String name,
            @RequestParam("lastName") String surname,
            @RequestParam("age") Integer age,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("roles") String role,
            @PathVariable String id) {
        User user = userServiceImpl.getUserWithFields(name, surname, age, email, password, role);
        userServiceImpl.update(Long.valueOf(id), user);
        return "redirect:/admin";
    }

    @PostMapping("/{id}")
    public String delete(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userServiceImpl.delete(id);
        return "redirect:/admin";
    }


>>>>>>> origin/master
}


