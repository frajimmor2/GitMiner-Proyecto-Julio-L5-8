package AISS.AISS.controllers;

import AISS.AISS.model.Comment;
import AISS.AISS.model.Commit;
import AISS.AISS.model.Issue;
import AISS.AISS.model.Project;
import AISS.AISS.service.GitLabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/gitlab")
public class GitLabController {

    @Autowired
    GitLabService service;

    @GetMapping("/{projectId}")
    public Project findProject(@PathVariable String projectId, @RequestParam(value = "sinceCommits") Optional<Integer> sinceCommits, @RequestParam("sinceIssues") Optional<Integer> sinceIssues, @RequestParam("maxPages") Optional<Integer> maxPages) {
        return service.findOne(projectId, sinceCommits, sinceIssues, maxPages);
    }


    @PostMapping("/{projectId}")
    public Project create(@PathVariable String projectId, @RequestParam(value = "sinceCommits") Optional<Integer> sinceCommits, @RequestParam("sinceIssues") Optional<Integer> sinceIssues, @RequestParam("maxPages") Optional<Integer> maxPages) {
        return service.postProject(service.findOne(projectId, sinceCommits, sinceIssues, maxPages));
    }

}