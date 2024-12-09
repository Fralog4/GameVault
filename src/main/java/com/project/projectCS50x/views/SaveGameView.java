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
import jakarta.annotation.security.PermitAll;

import java.time.LocalDate;
import java.time.ZoneId;

@PermitAll
@Route("/gamevault/uploadvideogame")
public class SaveGameView  extends VerticalLayout {

    private final VideogameController videogameController;

    public SaveGameView(VideogameController videogameController) {
        this.videogameController = videogameController;

        this.addClassName("saving-videogameview");

        FormLayout formLayout = new FormLayout();
        TextField titleField = new TextField("Title");
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
        gameGenre.setPrefixComponent(VaadinIcon.TOUCH.create());
        gameGenre.setItems("Action", "Adventure", "RPG", "Sports", "Shooter", "Fight", "Horror", "Platform", "Puzzle", "Racing", "Simulation", "Strategy", "Survival");
        gameGenre.setRequiredIndicatorVisible(true);
        gameGenre.setI18n(new Select.SelectI18n()
                .setRequiredErrorMessage("Did you forget something didn't you? "));

        Select<String> gameStatus = new Select<>();
        gameStatus.setLabel("Game Status");
        gameStatus.setHelperText("Choose one");
        gameStatus.setPlaceholder("Choose a status");
        gameStatus.setTooltipText("Tooltip text");
        gameStatus.setPrefixComponent(VaadinIcon.TOUCH.create());
        gameStatus.setItems("In Progress", "Completed", "Dropped", "Replayed", "Planned");
        gameStatus.setRequiredIndicatorVisible(true);
        gameStatus.setI18n(new Select.SelectI18n()
                .setRequiredErrorMessage("Did you forget something didn't you? "));


        Select<String> platformField = new Select<>();
        platformField.setLabel("Platform");
        platformField.setHelperText("Choose one");
        platformField.setPlaceholder("Choose a platform");
        platformField.setTooltipText("Tooltip text");
        platformField.setPrefixComponent(VaadinIcon.TOUCH.create());
        platformField.setItems("PC", "Xbox", "Playstation", "Nintendo","Other");
        platformField.setRequiredIndicatorVisible(true);
        platformField.setI18n(new Select.SelectI18n()
                .setRequiredErrorMessage("Did you forget something didn't you? "));

        IntegerField personalRating = new IntegerField("Personal Rating");

        personalRating.setHelperText("How much did you enjoyed from 1 to 10? ");
        personalRating.setRequiredIndicatorVisible(true);
        personalRating.setMin(1);
        personalRating.setMax(10);
        personalRating.setStepButtonsVisible(true);
        personalRating.setClearButtonVisible(true);
        personalRating.setPrefixComponent(VaadinIcon.STAR.create());
        personalRating.setI18n(new IntegerField.IntegerFieldI18n()
                .setRequiredErrorMessage("Did you forget something didn't you? ")
                .setBadInputErrorMessage("Hey put a number here!")
                .setMinErrorMessage("0? Really?")
                .setMaxErrorMessage("10 is the limit fellow!"));


        LocalDate now = LocalDate.now(ZoneId.systemDefault());

        DatePicker playedDate = new DatePicker("Played Date");
        playedDate.setRequiredIndicatorVisible(true);
        playedDate.setMax(now.plusDays(60));
        playedDate.setHelperText("Must be within 60 days from today");
        playedDate.addClassName("form-games");


        playedDate.setI18n(new DatePicker.DatePickerI18n()
                .setBadInputErrorMessage("Invalid date format")
                .setRequiredErrorMessage("Field is required")
                .setMaxErrorMessage("Too late, choose another date"));

        formLayout.add(titleField, platformField, personalRating, gameGenre, playedDate, gameStatus);
        formLayout.addClassName("form-games");

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
