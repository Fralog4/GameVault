package com.project.projectCS50x.repository;

import com.project.projectCS50x.model.Videogame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideogameRepository extends JpaRepository<Videogame, Long> {
    List<Videogame> findByTitle(String title);
}
