package aiss.gitminer.controller;

import aiss.gitminer.model.Comment;
import aiss.gitminer.model.Project;
import aiss.gitminer.service.GitMinerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/gitminer")
public class CommentController {
    @Autowired
    GitMinerService service;

    
    @GetMapping("/comments")
    public List<Comment> getComments() {
        return service.getComments();
    }

   
    @GetMapping("/comments/{commentId}")
    public Comment getCommentById(@PathVariable String commentId) {
        return service.getComment(commentId).get();
    }
}