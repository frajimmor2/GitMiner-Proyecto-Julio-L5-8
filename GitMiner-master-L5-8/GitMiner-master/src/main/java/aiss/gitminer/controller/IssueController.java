package aiss.gitminer.controller;

import aiss.gitminer.model.Comment;
import aiss.gitminer.model.Issue;
import aiss.gitminer.model.Project;
import aiss.gitminer.service.GitMinerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/gitminer")
public class IssueController {
    @Autowired
    GitMinerService service;

    @GetMapping("/issues")
    public List<Issue> getIssues(@RequestParam(value = "authorId") Optional<String> authorId, @RequestParam(value = "state") Optional<String> state) {
        List<Issue> issues;
        if (authorId.isPresent()) {
            issues =  service.getIssuesByAuthorId(authorId.get());
        } else if (state.isPresent()) {
            issues =  service.getIssuesByState(state.get());
        } else {
            issues =  service.getIssues();
        }
        return issues;
    }

    
    @GetMapping("/issues/{issueId}")
    public Issue getIssueById(@PathVariable String issueId) {
        return service.getIssue(issueId).get();
    }

    
    @GetMapping("/issues/{issueId}/comments")
    public List<Comment> getCommentsByIssueId(@PathVariable String issueId) {
        return service.getCommentsByIssueId(issueId);
    }
}