package com.homeboard.homeboard.controller;

import java.util.List;
import static com.homeboard.homeboard.config.ConstansSecurity.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.homeboard.homeboard.exception.HomeBoardException;
import com.homeboard.homeboard.model.Idea;
import com.homeboard.homeboard.service.IdeaService;

@RestController
public class IdeaController {

    IdeaService ideaService;

    public IdeaController(IdeaService ideaService){
        this.ideaService = ideaService;
    }

    @GetMapping(PUBLIC_IDEAS_URL)
    public List<Idea> getAllPublicIdeas() throws HomeBoardException{
        return ideaService.getPublicIdeas();
    }

    @GetMapping(USER_IDEAS_URL)
    public List<Idea> getIdeasByUserIdAndCategoryId(@PathVariable Integer userId, @PathVariable Integer categoryId) throws HomeBoardException{
        return ideaService.getIdeasByUserIdAndCategoryId(userId, categoryId);
    }

    @PostMapping(NEW_IDEA_URL)
    public Idea addNewIdea(@RequestBody Idea idea) throws HomeBoardException{
        return ideaService.addNewIdea(idea);
    }

    @PutMapping(UPDATE_IDEA_URL)
    public Idea updateIdea(@PathVariable Integer id, @RequestBody Idea idea) throws HomeBoardException{
        idea.setId(id);
        return ideaService.updateIdea(idea);
    }

        @DeleteMapping(DELETE_IDEA_URL)
    public void deleteIdea(@PathVariable Integer id) throws HomeBoardException{
        ideaService.deleteIdea(id);
    }
}
