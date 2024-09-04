package com.homeboard.homeboard.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.homeboard.homeboard.model.Idea;
import com.homeboard.homeboard.service.IdeaService;

@RestController
public class IdeaController {

    IdeaService ideaService;

    public IdeaController(IdeaService ideaService){
        this.ideaService = ideaService;
    }

    @GetMapping("/public")
    public List<Idea> getAllPublicIdeas() {
        return ideaService.getPublicIdeas();
    }

    @GetMapping("/user/{user_Id}/category/{categoryId}")
    public List<Idea> getIdeasByUserIdAndCategoryId(@PathVariable Integer userId, @PathVariable Integer chategoryId){
        return ideaService.getIdeasByUserIdAndCategoryId(userId, chategoryId);
    }

    @PostMapping("/idea")
    public Idea addNewIdea(@RequestBody Idea idea){
        return ideaService.addNewIdea(idea);
    }

    @PutMapping("/{id}")
    public Idea updateIdea(@PathVariable Integer id, @RequestBody Idea idea){
        idea.setId(id);
        return ideaService.updateIdea(idea);
    }

        @DeleteMapping("/{id}")
    public void deleteIdea(@PathVariable Integer id){
        ideaService.deleteIdea(id);
    }
}
