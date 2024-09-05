package com.homeboard.homeboard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.homeboard.homeboard.Repository.BoardRepository;
import com.homeboard.homeboard.Repository.IdeaRepository;
import com.homeboard.homeboard.Repository.UserRepository;
import com.homeboard.homeboard.exception.HomeBoardException;
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

    public List<Idea> getPublicIdeas() throws HomeBoardException{
        try {
            return ideaRepository.findByIsPublicTrue();
        } catch (Exception e) {
            throw new HomeBoardException("Error al recuperar las ideas públicas.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public List<Idea> getIdeasByUserIdAndCategoryId(Integer userId, Integer categoryId) throws HomeBoardException {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new HomeBoardException("Usuario no encontrado.", HttpStatus.NOT_FOUND));
        Board board = boardRepository.findById(categoryId)
            .orElseThrow(() -> new HomeBoardException("Categoría no encontrada.", HttpStatus.NOT_FOUND));
    
        return ideaRepository.findByUserAndBoard(user, board);
    }

    public Idea getIdeaById(int id) throws HomeBoardException{
        Optional<Idea> idea = ideaRepository.findById(id);
        return idea.orElseThrow(() -> new HomeBoardException("La idea con el ID proporcionado no existe.", HttpStatus.NOT_FOUND));
    }

    public Idea addNewIdea(Idea idea) throws HomeBoardException{
        if(idea.getTitle() == null || idea.getTitle().isEmpty()) {
            throw new HomeBoardException("El título de la idea no puede estar vacío.", HttpStatus.BAD_REQUEST);
        }
        if(idea.getNotes() == null || idea.getNotes().isEmpty()) {
            throw new HomeBoardException("El campo de notas de la idea no puede estar vacío.", HttpStatus.BAD_REQUEST);
        }
        if(idea.getImg() == null || idea.getImg().isEmpty()) {
            throw new HomeBoardException("El campo de imagen de la idea no puede estar vacío.", HttpStatus.BAD_REQUEST);
        }
        
        User user = userRepository.findById(idea.getUser().getId()).orElseThrow(() -> new HomeBoardException("Usuario no encontrado.", HttpStatus.NOT_FOUND));
        Board board = boardRepository.findById(idea.getBoard().getId()).orElseThrow(() -> new HomeBoardException("Categoría no encontrada.", HttpStatus.NOT_FOUND));

        idea.setUser(user);
        idea.setBoard(board);
        return ideaRepository.save(idea);
    }

    public Idea updateIdea(Idea idea) throws HomeBoardException{

        // Verificar si el título es válido
        if (idea.getTitle() == null || idea.getTitle().isEmpty()) {
            throw new HomeBoardException("El título de la idea no puede estar vacío.", HttpStatus.BAD_REQUEST);
        }

        // Verificar si las notas son válidas
        if (idea.getNotes() == null || idea.getNotes().isEmpty()) {
            throw new HomeBoardException("El campo de notas de la idea no puede estar vacío.", HttpStatus.BAD_REQUEST);
        }

        // Verificar si la imagen es válida (si es obligatoria)
        if (idea.getImg() == null || idea.getImg().isEmpty()) {
            throw new HomeBoardException("El campo de imagen de la idea no puede estar vacío.", HttpStatus.BAD_REQUEST);
        }
        return ideaRepository.save(idea);
    }

    public void deleteIdea(Integer id) throws HomeBoardException {
        if (!ideaRepository.existsById(id)) {
            throw new HomeBoardException("La idea con el ID proporcionado no existe.", HttpStatus.NOT_FOUND);
        }
        ideaRepository.deleteById(id);
    }

}
