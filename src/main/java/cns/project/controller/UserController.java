package cns.project.controller;

import cns.project.entity.User;
import cns.project.service.ProjectService;
import cns.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @GetMapping({"/login", "/"})
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/list")
    public String fetchUserList(Model model) {
        model.addAttribute("userList", userService.fetchUserList());
        return "user-list";
    }

//    @GetMapping("/{id}")
//    public User fetchUserById(@PathVariable("id") Long userId) {
//        return userService.fetchUserById(userId);
//    }
}