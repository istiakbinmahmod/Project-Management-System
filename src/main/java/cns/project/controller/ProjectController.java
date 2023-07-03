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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/project")
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

    @GetMapping("/add")
    public String addProject(Model model) {
        model.addAttribute("project", new Project());
        return "new-project";
    }

    @PostMapping("/save-project")
    public String saveProject(@ModelAttribute("project") @Valid Project project, HttpSession session) {
        Logger logger = Logger.getLogger(ProjectController.class.getName());
        logger.info("Project: " + project);
        System.out.println("Project: " + project);
        User owner = (User) session.getAttribute("user");
        System.out.println("owner: " + owner);
        project.setOwner(owner);
        projectService.saveProject(project);
        return "redirect:/project/list";
    }
}