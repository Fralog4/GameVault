package com.project.projectCS50x.views;

import com.project.projectCS50x.controller.VideogameController;
import com.project.projectCS50x.model.Videogame;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Route("/gamevault/videogames")
public class VideogamesView extends Div {

    private final VideogameController videogameController;
    public VideogamesView(VideogameController videogameController) {
        this.videogameController = videogameController;
        this.getElement().setAttribute("class", "videogamesView");
        Grid<Videogame> videogameGrid = new Grid<>(Videogame.class, false); // false for read-only>
        videogameGrid.addColumn(Videogame::getTitle).setHeader("Title");
        videogameGrid.addColumn(Videogame::getGenre).setHeader("Genre");
        videogameGrid.addColumn(Videogame::getPlatform).setHeader("Platform");
        videogameGrid.addColumn(Videogame::getPlayedDate).setHeader("Played Date");
        videogameGrid.addColumn(Videogame::getGamestatus).setHeader("Game Status");
        videogameGrid.addColumn(Videogame::getPersonalRating).setHeader("Personal Rating");
//        videogameGrid.addColumn(createPictureRenderer()).setHeader("Picture")
//                .setAutoWidth(true).setFlexGrow(0);
        //videogameGrid.addColumn(Videogame::getImage).setHeader("Image");

        videogameGrid.setItems(videogameController.getAllVideogames());
        videogameGrid.getElement().getStyle().set("border-collapse", "collapse");
        videogameGrid.getElement().getStyle().set("border", "1px solid #ccc");
        videogameGrid.getElement().getStyle().set("background-image", "linear-gradient(to bottom, #f5f5f5, #e5e5e5)");

        videogameGrid.getElement().setAttribute("class", "videogameGrid");
        add(videogameGrid);


        Button goBackToHome = new Button("Back to Home");
        goBackToHome.getElement().setAttribute("class", "btn btn-dark");
        goBackToHome.addClickListener(e -> {
            UI.getCurrent().navigate("/gamevault/home");
        });
        add(goBackToHome);

    }

//    private void refreshVideogames() {
//        UI.getCurrent().getPage().reload();
//
//    }


}


