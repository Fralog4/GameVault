package com.project.projectCS50x.views;

import com.project.projectCS50x.controller.ChatController;
import com.project.projectCS50x.controller.VideogameController;
import com.project.projectCS50x.model.Videogame;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;


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
        TextField platformField = new TextField("Platform");
        IntegerField personalRating= new IntegerField("Personal Rating");
        IntegerField id= new IntegerField("Id");

        formLayout.add(titleField,platformField, personalRating, id);
        Button saveButton = new Button("Save Videogame");
        saveButton.addClickListener(e -> {
            Videogame videogame = new Videogame();
            videogame.setId(Long.valueOf(id.getValue()));
            videogame.setTitle(titleField.getValue());
            videogame.setPlatform(platformField.getValue());
            videogame.setPersonalRating(personalRating.getValue());

            videogameController.saveVideogame(videogame);
        });


        Button chatButton = new Button("Chat with the AI Bot");
        chatButton.addClickListener(e -> {
            UI.getCurrent().navigate("/ai/generate");
        });
        formLayout.add(saveButton, chatButton);

        add(formLayout);
}
}
