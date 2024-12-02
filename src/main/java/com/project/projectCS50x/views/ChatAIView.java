package com.project.projectCS50x.views;

import com.project.projectCS50x.controller.ChatController;
import com.project.projectCS50x.service.MistralAIService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.messaging.Message;

import java.util.ArrayList;
import java.util.List;


@Route("/ai/generate")
public class ChatAIView extends VerticalLayout {

    private final ChatController chatController;
    private String channelId;
    private final MistralAIService mistralAIService;
    private final List<Message> receivedMessages = new ArrayList<>();


    public ChatAIView(ChatController chatController, MistralAIService mistralAIService) {
        this.chatController = chatController;
        this.mistralAIService = mistralAIService;
        add(new H1("Welcome to your new AI Powered Chat!"));
        MessageInput messageInput = new MessageInput(event -> sendMessage(event.getValue()));
        add(messageInput);
    }

    private void sendMessage(String message) {
        if (!message.isBlank()) {
            String url = "/ai/generate?message=" + message;
            UI.getCurrent().navigate(url);
        }
        else{
            System.err.println("Please enter a message");
        }
    }









}
