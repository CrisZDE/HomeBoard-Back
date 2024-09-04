package com.homeboard.homeboard.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.homeboard.homeboard.model.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer>{

}
