package com.project.projectCS50x.views;

import com.project.projectCS50x.controller.VideogameController;
import com.project.projectCS50x.model.GameGenre;
import com.project.projectCS50x.model.Gamestatus;
import com.project.projectCS50x.model.Videogame;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.time.ZoneId;


@Route("/gamevault/uploadvideogame")
public class SaveGameView  extends VerticalLayout {

    private final VideogameController videogameController;

    public SaveGameView(VideogameController videogameController) {
        this.videogameController = videogameController;

        FormLayout formLayout = new FormLayout();
        TextField titleField = new TextField("Title");
        titleField.getElement().setAttribute("class", "form-control");
        Button goBackToHome = new Button("Back to Home");
        goBackToHome.getElement().setAttribute("class", "btn btn-dark");
        goBackToHome.addClickListener(e -> {
            UI.getCurrent().navigate("/gamevault/home");
        });
        add(goBackToHome);

        Select<String> gameGenre = new Select<>();
        gameGenre.setLabel("Game Genre");
        gameGenre.setHelperText("Choose one");
        gameGenre.setPlaceholder("Choose a genre");
        gameGenre.setTooltipText("Tooltip text");
        gameGenre.setPrefixComponent(VaadinIcon.VAADIN_H.create());
        gameGenre.setItems("Action", "Adventure", "RPG", "Sports", "Shooter", "Fight", "Horror", "Platform", "Puzzle", "Racing", "Simulation", "Strategy", "Survival");
        gameGenre.setRequiredIndicatorVisible(true);
        gameGenre.getElement().setAttribute("class", "form-control");

        Select<String> gameStatus = new Select<>();
        gameStatus.setLabel("Game Status");
        gameStatus.setHelperText("Choose one");
        gameStatus.setPlaceholder("Choose a status");
        gameStatus.setTooltipText("Tooltip text");
        gameStatus.setPrefixComponent(VaadinIcon.VAADIN_V.create());
        gameStatus.setItems("In Progress", "Completed", "Dropped", "Replayed", "Planned");
        gameStatus.setRequiredIndicatorVisible(true);
        gameStatus.getElement().setAttribute("class", "form-control");


        TextField platformField = new TextField("Platform");
        platformField.getElement().setAttribute("class", "form-control");
        IntegerField personalRating = new IntegerField("Personal Rating");
        personalRating.getElement().setAttribute("class", "form-control");

        LocalDate now = LocalDate.now(ZoneId.systemDefault());

        DatePicker playedDate = new DatePicker("Played Date");
        playedDate.getElement().setAttribute("class", "form-control");
        playedDate.setRequiredIndicatorVisible(true);
        playedDate.setMax(now.plusDays(60));
        playedDate.setHelperText("Must be within 60 days from today");


        playedDate.setI18n(new DatePicker.DatePickerI18n()
                .setBadInputErrorMessage("Invalid date format")
                .setRequiredErrorMessage("Field is required")
                .setMaxErrorMessage("Too late, choose another date"));

        formLayout.add(titleField, platformField, personalRating, gameGenre, playedDate, gameStatus);
        Button saveButton = new Button("Save Videogame");
        saveButton.getElement().setAttribute("class", "btn btn-dark");
        saveButton.getElement().setAttribute("style", "margin-right: 10px");
        saveButton.getElement().setAttribute("type", "submit");
        saveButton.addClickListener(e -> {
            Videogame videogame = new Videogame();
            videogame.setTitle(titleField.getValue());
            videogame.setPlatform(platformField.getValue());
            videogame.setPersonalRating(personalRating.getValue());
            videogame.setGamestatus(Gamestatus.valueOf(gameStatus.getValue().toUpperCase().replace(" ", "_")));
            videogame.setPlayedDate(playedDate.getValue());
            videogame.setGenre(GameGenre.valueOf(gameGenre.getValue().toUpperCase()));


            videogameController.saveVideogame(videogame);
            //refresh the list
            UI.getCurrent().navigate("/gamevault/home");
        });
        add(formLayout, saveButton);

    }
}
