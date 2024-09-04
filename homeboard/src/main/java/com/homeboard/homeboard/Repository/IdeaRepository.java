package com.homeboard.homeboard.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homeboard.homeboard.model.Board;
import com.homeboard.homeboard.model.Idea;
import com.homeboard.homeboard.model.User;

@Repository
public interface IdeaRepository extends JpaRepository <Idea, Integer>{
    List<Idea> findByIsPublicTrue();
    List<Idea> findByUserAndBoard (User user, Board board);
}
