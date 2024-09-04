package com.homeboard.homeboard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.homeboard.homeboard.Repository.BoardRepository;
import com.homeboard.homeboard.Repository.IdeaRepository;
import com.homeboard.homeboard.Repository.UserRepository;
import com.homeboard.homeboard.model.Board;
import com.homeboard.homeboard.model.Idea;
import com.homeboard.homeboard.model.User;

@Service
public class IdeaService {

    private IdeaRepository ideaRepository;
    private UserRepository userRepository;
    private BoardRepository boardRepository;

    public IdeaService(IdeaRepository ideaRepository, UserRepository userRepository, BoardRepository boardRepository){
        this.ideaRepository = ideaRepository;
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    public List<Idea> getPublicIdeas(){
        return ideaRepository.findByIsPublicTrue();
    }

    public List<Idea> getIdeasByUserIdAndCategoryId(Integer userId, Integer categoryId) {
        // Obtener User y Board usando los IDs
        User user = userRepository.findById(userId).orElse(null);
        Board board = boardRepository.findById(categoryId).orElse(null);

        // Verificar si ambos existen antes de realizar la consulta
        if (user != null && board != null) {
            return ideaRepository.findByUserAndBoard(user, board);
        } else {
            // Manejar el caso en que el usuario o la categoría no existen
            return List.of(); // Retornar una lista vacía o manejar el error según sea necesario
        }
    }

    public Idea getIdeaById(int id){
        Optional<Idea> idea = ideaRepository.findById(id);
        return idea.orElse(null);
    }

    public Idea addNewIdea(Idea idea){
        if(idea.getTitle() == null || idea.getTitle().isEmpty()) {
            //Handle Error
        
        }
        User user = userRepository.findById(idea.getUser().getId()).orElse(null);
        Board board = boardRepository.findById(idea.getBoard().getId()).orElse(null);

        idea.setUser(user);
        idea.setBoard(board);
        return ideaRepository.save(idea);
    }

    public Idea updateIdea(Idea idea){
        return ideaRepository.save(idea);
    }

    public void deleteIdea(Integer id) {
        ideaRepository.deleteById(id);
    }

}
