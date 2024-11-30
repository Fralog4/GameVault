package com.project.projectCS50x.service;

import com.project.projectCS50x.model.Videogame;
import com.project.projectCS50x.repository.VideogameRepository;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class VideogameService {
    private final VideogameRepository videogameRepository;

    public List<Videogame> getAllVideogames() {
        log.info("Videogames found");
        return videogameRepository.findAll().stream().toList(); }

    public Videogame getVideogameById(Long id){
        log.info("Videogame found by id: " + id);
        return videogameRepository.findById(id).orElse(null); }

    public List<Videogame> getVideogameByTitle(String title){
        log.info("Videogame found by title: " + title);
        return videogameRepository.findByTitle(title); }

    public Videogame saveVideogame(Videogame videogame){
        log.info("Videogame saved: " + videogame);
        return videogameRepository.save(videogame);}

    public void deleteVideogame(Long id){videogameRepository.deleteById(id);
        log.info("Videogame deleted with id: " + id);}

    public Videogame updateVideogame(Long id, Videogame videogame){
        Videogame videogameToUpdate = getVideogameById(id);
        videogameToUpdate.setTitle(videogame.getTitle());
        videogameToUpdate.setPlatform(videogame.getPlatform());
        videogameToUpdate.setGenre(videogame.getGenre());
        videogameToUpdate.setPlayedDate(videogame.getPlayedDate());
        videogameToUpdate.setPersonalRating(videogame.getPersonalRating());
        videogameToUpdate.setGamestatus(videogame.getGamestatus());
        log.info("Videogame updated: " + videogameToUpdate);
        return videogameRepository.save(videogameToUpdate);
    }
}
