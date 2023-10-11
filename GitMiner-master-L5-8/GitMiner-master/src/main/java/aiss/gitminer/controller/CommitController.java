package aiss.gitminer.controller;

import aiss.gitminer.model.Commit;
import aiss.gitminer.model.Project;
import aiss.gitminer.service.GitMinerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gitminer")
public class CommitController {
    @Autowired
    GitMinerService service;

    @GetMapping("/commits")
    public List<Commit> getCommits(@RequestParam(value = "email") Optional<String> email) {
        List<Commit> commits;
        if (email.isPresent()) {
            commits = service.getCommitByEmail(email.get());
        } else {
            commits = service.getCommits();
        }
        return commits;
    }

   
    @GetMapping("/commits/{commitId}")
    public Commit getCommitById(@PathVariable String commitId) {
        return service.getCommit(commitId).get();
    }
}