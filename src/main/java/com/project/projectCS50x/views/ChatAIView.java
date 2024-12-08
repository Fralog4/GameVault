package com.project.projectCS50x.views;

import com.project.projectCS50x.controller.ChatController;
import com.project.projectCS50x.service.MistralAIService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;

@PermitAll
@Route("/gamevault/ai/generate")
public class ChatAIView extends Div {

    private final ChatController chatController;
    private final MistralAIService mistralAIService;
    private final MessageInput messageInput;
    private final MessageList messageList = new MessageList();




    public ChatAIView(ChatController chatController, MistralAIService mistralAIService) {
        this.chatController = chatController;
        this.mistralAIService = mistralAIService;

        this.getElement().setAttribute("class", "homeChat");

        add(new H1("Hey I'm Victor, welcome my friend!"));

        messageInput = new MessageInput(event -> sendMessage(event.getValue()));
        messageInput.getElement().setAttribute("class", "form-control");
        add(messageInput);
        add(messageList);
        messageList.setWidthFull();


        Button goBackToHome = new Button("Back to Home");
        goBackToHome.getElement().setAttribute("class", "btn btn-dark");
        goBackToHome.addClickListener(e -> {
            UI.getCurrent().navigate("/gamevault/home");
        });
        add(goBackToHome);
    }




    private void sendMessage(String message) {
        if (!message.isBlank()) {

            MessageList list = new MessageList();
            Instant yesterday = LocalDateTime.now().minusDays(1)
                    .toInstant(ZoneOffset.UTC);
            Instant fiftyMinsAgo = LocalDateTime.now().minusMinutes(50)
                    .toInstant(ZoneOffset.UTC);
            MessageListItem message1 = new MessageListItem(
                    message,
                    yesterday, "You");
            message1.setUserColorIndex(1);
            message1.setUserImage("https://cdn2.steamgriddb.com/icon/bce2e8945c21216d6a3e28aed4569e98.ico");

            String aiResponseText = chatController.generateWithPrompt(message);

            MessageListItem message2 = new MessageListItem(aiResponseText,
                    fiftyMinsAgo, "Victor");
            message2.setUserColorIndex(2);
            message2.setUserImage("https://ih1.redbubble.net/image.3234664643.0408/raf,360x360,075,t,fafafa:ca443f4786.jpg");
            list.setItems(Arrays.asList(message1, message2));
            add(list);
        } else {
            System.err.println("Please enter a message");
        }
    }








}
