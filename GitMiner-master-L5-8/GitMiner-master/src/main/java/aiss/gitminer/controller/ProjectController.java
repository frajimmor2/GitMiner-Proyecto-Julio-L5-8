package aiss.gitminer.controller;

import aiss.gitminer.model.Project;
import aiss.gitminer.service.GitMinerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/gitminer")
public class ProjectController {
    @Autowired
    GitMinerService service;

    
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/projects")
    public Project saveProject(@Valid @RequestBody Project project) {
        service.saveProject(project);
        return project;
    }

    @GetMapping("/projects")
    public List<Project> getProjects() {
        return service.getProjects();
    }

    
    @GetMapping("/projects/{projectId}")
    public Project getProject(@PathVariable String projectId) {
        return service.findProject(projectId).get();
    }

}