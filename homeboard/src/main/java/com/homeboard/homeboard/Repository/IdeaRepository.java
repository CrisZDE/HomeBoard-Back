package com.homeboard.homeboard.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homeboard.homeboard.model.Idea;

@Repository
public interface IdeaRepository extends JpaRepository <Idea, Integer>{

}
