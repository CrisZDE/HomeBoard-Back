package com.homeboard.homeboard.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.homeboard.homeboard.Repository.BoardRepository;
import com.homeboard.homeboard.model.Board;

@Service
public class BoardService {

    private BoardRepository boardRepository;
    
    public BoardService (BoardRepository boardRepository){
        this.boardRepository = boardRepository;
    }

    public List<Board> getAllCategories(){
        return boardRepository.findAll();
    }

    public Board getCategoryById(Integer categoryId) {
        return boardRepository.findById(categoryId).orElse(null);
    }
}
