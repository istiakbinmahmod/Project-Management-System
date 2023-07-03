package cns.project.controller;

import cns.project.entity.Project;
import cns.project.entity.User;
import cns.project.service.ProjectService;
import cns.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/project/")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @GetMapping("/create")
    public String createProject(Model model) {
        Project project = new Project();
        model.addAttribute("project", project);
        List<User> userList = userService.fetchUserList();
//        projectService.viewInit(model, null);

        return "new-project";
    }

//    @GetMapping({"/", "/list"})
//    public List<Project> fetchProjectList() {
//        return projectService.fetchProjectList();
//    }

    @GetMapping({"/", "/list"})
    public String fetchProjectList(Model model) {
        model.addAttribute("projects", projectService.fetchProjectList());
        return "projects";
//        return projectService.fetchProjectList();
    }

//    @PostMapping("/add")
//    public Project saveProject(Project project) {
//        return projectService.saveProject(project);
//    }

    @PostMapping("/add")
    public String saveProject(@Valid @ModelAttribute("project") Project project) {
        System.out.println("project: " + project);

        return "register";
//        return projectService.saveProject(project);
    }
}