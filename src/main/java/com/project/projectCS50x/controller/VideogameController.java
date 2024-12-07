package com.project.projectCS50x.controller;

import com.project.projectCS50x.model.Videogame;
import com.project.projectCS50x.service.VideogameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gamevaultController")
@RequiredArgsConstructor
public class VideogameController {
    private final VideogameService videogameService;

    @GetMapping("/videogames")
    public List<Videogame> getAllVideogames() {
        return videogameService.getAllVideogames();
    }

    @GetMapping("/videogame/{id}")
    public Videogame getVideogameById(@PathVariable Long id) {
        return videogameService.getVideogameById(id);
    }

    @GetMapping("/videogame/title/{title}")
    public List<Videogame> getVideogameByTitle(@PathVariable String title) {
        return videogameService.getVideogameByTitle(title);
    }

    @PostMapping("/uploadvideogame")
    public Videogame saveVideogame(@RequestBody Videogame videogame) {
        return videogameService.saveVideogame(videogame);
    }

    @DeleteMapping("/videogame/{id}")
    public void deleteVideogame(@PathVariable Long id) {
        videogameService.deleteVideogame(id);
    }

    @PutMapping("/videogame/{id}")
    public Videogame updateVideogame(@PathVariable Long id, @RequestBody Videogame videogame) {
        return videogameService.updateVideogame(id, videogame);
    }

}
