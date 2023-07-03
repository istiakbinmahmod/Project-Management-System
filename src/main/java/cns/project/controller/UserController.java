package cns.project.controller;

import cns.project.entity.Project;
import cns.project.entity.User;
import cns.project.service.ProjectService;
import cns.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.logging.Logger;

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

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
        User user = userService.login(email, password);
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/project/list";
        } else {
            model.addAttribute("error", "Invalid Credentials");
            return "login";
        }
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("newUser", new User());
        System.out.println("User: " + new Project());
        return "register";
    }

    @PostMapping("/save-user")
    public String saveUser(@ModelAttribute("newUser") @Valid User user, Model model, HttpSession session) {
        Logger logger = Logger.getLogger(UserController.class.getName());
        logger.info("User: " + user);
        userService.saveUser(user);
        session.setAttribute("user", user);
        return "redirect:/user/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        System.out.println(session.getAttribute("user"));
        session.invalidate();
        System.out.println(session.getAttribute("user"));
        return "redirect:/user/login";
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