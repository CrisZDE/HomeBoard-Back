package com.homeboard.homeboard.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.homeboard.homeboard.model.Board;
import com.homeboard.homeboard.service.BoardService;

@RestController
public class BoardController {

    BoardService boardService;

    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    @GetMapping("board")
    public List<Board> getAllCategories(){
        return boardService.getAllCategories();
    }
    
    @GetMapping("board/{id}")
    public Board getCategoryById(@PathVariable Integer id){
        return boardService.getCategoryById(id);
    }
}
