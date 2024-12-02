package com.project.projectCS50x.views;

import com.project.projectCS50x.controller.ChatController;
import com.project.projectCS50x.service.MistralAIService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.messaging.Message;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Route("/ai/generate")
public class ChatAIView extends VerticalLayout {

    private final ChatController chatController;
    private String channelId;
    private final MistralAIService mistralAIService;
    private final List<Message> receivedMessages = new ArrayList<>();
    private final MessageInput messageInput;
    private final List<Div> messageHistory = new ArrayList<>();
    private final VerticalLayout messageHistoryLayout = new VerticalLayout();




    public ChatAIView(ChatController chatController, MistralAIService mistralAIService) {
        this.chatController = chatController;
        this.mistralAIService = mistralAIService;
        add(new H1("Welcome to your new AI Powered Chat!"));
        messageInput = new MessageInput(event -> sendMessage(event.getValue()));
        messageInput.getElement().setAttribute("class", "form-control");
        add(messageInput);
        add(messageHistoryLayout);
        messageHistoryLayout.setWidthFull();
        messageHistoryLayout.setSpacing(true);

        Button testButton= new Button("Test");
        testButton.getElement().setAttribute("class", "btn btn-dark");  //bootstrap test
        add(testButton);


    }




    private void sendMessage(String message) {
        if (!message.isBlank()) {
            Div userMessage = new Div();
            userMessage.setText(message);
            userMessage.getStyle().set("backgroundColor", "lightblue");
            userMessage.getElement().setAttribute("class",  "form-control-sm");
            messageHistory.add(userMessage);
            messageHistoryLayout.add(userMessage);
            String aiResponse = chatController.generate(message).toString();
            Div aiMessage = new Div();
            aiMessage.setText(aiResponse);
            aiMessage.getStyle().set("backgroundColor", "lightgray");
            aiMessage.getElement().setAttribute("class",  "form-control-sm");
            messageHistory.add(aiMessage);
            messageHistoryLayout.add(aiMessage);
            messageHistoryLayout.scrollIntoView();
        }
        else{
            System.err.println("Please enter a message");
        }
    }









}
