package com.project.projectCS50x.views;

import com.project.projectCS50x.controller.ChatController;
import com.project.projectCS50x.controller.VideogameController;
import com.project.projectCS50x.model.GameGenre;
import com.project.projectCS50x.model.Gamestatus;
import com.project.projectCS50x.model.Videogame;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.time.ZoneId;


@Route("")
public class HomeView extends VerticalLayout {

    private final VideogameController videogameController;
    private final ChatController chatController;

    public HomeView(VideogameController videogameController,
                    ChatController chatController) {
        this.videogameController = videogameController;
        this.chatController = chatController;

        add(new H1("Welcome to your new application"));
        add(new Paragraph("This is the home view"));

        FormLayout formLayout = new FormLayout();
        TextField titleField = new TextField("Title");
        titleField.getElement().setAttribute("class", "form-control");

        Select<String> field = new Select<>();
        field.setLabel("Game Genre");
        field.setHelperText("Choose one");
        field.setPlaceholder("Choose a genre");
        field.setTooltipText("Tooltip text");
        field.setPrefixComponent(VaadinIcon.VAADIN_H.create());
        field.setItems("Action", "Adventure", "RPG", "Sports", "Shooter", "Fight", "Horror", "Platform", "Puzzle", "Racing", "Simulation", "Strategy", "Survival");
        field.setRequiredIndicatorVisible(true);
        field.getElement().setAttribute("class", "form-control");

        Select<String> gameStatus = new Select<>();
        gameStatus.setLabel("Game Status");
        gameStatus.setHelperText("Choose one");
        gameStatus.setPlaceholder("Choose a status");
        gameStatus.setTooltipText("Tooltip text");
        gameStatus.setPrefixComponent(VaadinIcon.VAADIN_H.create());
        gameStatus.setItems("Not Started", "In Progress", "Completed");
        gameStatus.setRequiredIndicatorVisible(true);
        gameStatus.getElement().setAttribute("class", "form-control");

        TextField platformField = new TextField("Platform");
        platformField.getElement().setAttribute("class", "form-control");
        IntegerField personalRating= new IntegerField("Personal Rating");
        personalRating.getElement().setAttribute("class", "form-control");
        IntegerField id= new IntegerField("Id");
        id.getElement().setAttribute("class", "form-control");
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

        formLayout.add(titleField,platformField, personalRating, id, field, playedDate);
        Button saveButton = new Button("Save Videogame");
        saveButton.getElement().setAttribute("class", "btn btn-dark");
        saveButton.getElement().setAttribute("style", "margin-right: 10px");
        saveButton.getElement().setAttribute("type", "submit");
        saveButton.addClickListener(e -> {
            Videogame videogame = new Videogame();
            videogame.setId(Long.valueOf(id.getValue()));
            videogame.setTitle(titleField.getValue());
            videogame.setPlatform(platformField.getValue());
            videogame.setPersonalRating(personalRating.getValue());
            videogame.setGenre(GameGenre.valueOf(field.getValue()));
            videogame.setPlayedDate(playedDate.getValue());
            videogame.setGamestatus(Gamestatus.valueOf(gameStatus.getValue()));

            videogameController.saveVideogame(videogame);
            UI.getCurrent().navigate("/gamevault/videogames");

        });

        Button goToCollectionButton = new Button("Go to Collection");
        goToCollectionButton.getElement().setAttribute("class", "btn btn-dark");
        goToCollectionButton.getElement().setAttribute("style", "margin-right: 10px");

        goToCollectionButton.addClickListener(e -> {
            UI.getCurrent().navigate("/gamevault/videogames");
        });
        formLayout.add(goToCollectionButton);


        Button chatButton = new Button("Chat with the AI Bot");
        chatButton.getElement().setAttribute("class", "btn btn-warning");
        chatButton.addClickListener(e -> {
            UI.getCurrent().navigate("/ai/generate");
        });
        formLayout.add(saveButton, chatButton);

        add(formLayout);
}
}
