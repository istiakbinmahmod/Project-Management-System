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
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = currentDate.format(formatter);
        Timestamp timestampDate = Timestamp.from(Instant.parse(formattedDate + "T00:00:00.00Z"));

        model.addAttribute("projects", projectService.fetchProjectList());
        model.addAttribute("formattedDate", timestampDate);
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

    @GetMapping("/delete/{id}")
    public String deleteProject(@PathVariable("id") Long id) {
        projectService.deleteProject(id);
        return "redirect:/project/list";
    }

    @GetMapping("/details/{id}")
    public String projectDetails(@PathVariable("id") Long id, Model model) {
        Project project = projectService.fetchProjectById(id);
        User owner = project.getOwner();
        Set<User> userList = project.getUsers();
        // find the userNames from userList set
        // name is formed by joining firstName and lastName
        List<String> userNames = userList.stream()
                .map(user -> user.getFirstName() + " " + user.getLastName())
                .collect(Collectors.toList());

//        List<String> userNames = userList.stream().map(user -> user.getFirstName() + " " + user.getLastName()).toList();
        model.addAttribute("project", project);
        model.addAttribute("owner", owner);
        model.addAttribute("userNames", userList);
        return "project-detail";
    }
}