package com.homeboard.homeboard.controller;

import static com.homeboard.homeboard.config.ConstansSecurity.BOARDS_URL;
import static com.homeboard.homeboard.config.ConstansSecurity.BOARD_ID_URL;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.homeboard.homeboard.exception.HomeBoardException;
import com.homeboard.homeboard.model.Board;
import com.homeboard.homeboard.service.BoardService;

@RestController
public class BoardController {

    BoardService boardService;

    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }

    @GetMapping(BOARDS_URL)
    public List<Board> getAllCategories() throws HomeBoardException{
        return boardService.getAllCategories();
    }
    
    @GetMapping(BOARD_ID_URL)
    public Board getCategoryById(@PathVariable Integer id) throws HomeBoardException{
        return boardService.getCategoryById(id);
    }
}
