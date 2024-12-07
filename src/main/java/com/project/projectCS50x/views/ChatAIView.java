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
import org.springframework.messaging.Message;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Route("/gamevault/ai/generate")
public class ChatAIView extends Div {

    private final ChatController chatController;
    private String channelId;
    private final MistralAIService mistralAIService;
    private final List<Message> receivedMessages = new ArrayList<>();
    private final MessageInput messageInput;
    private final List<Div> messageHistory = new ArrayList<>();
    private final MessageList messageList = new MessageList();




    public ChatAIView(ChatController chatController, MistralAIService mistralAIService) {
        this.chatController = chatController;
        this.mistralAIService = mistralAIService;
        add(new H1("Welcome to our AI Powered Chat!"));
        messageInput = new MessageInput(event -> sendMessage(event.getValue()));
        messageInput.getElement().setAttribute("class", "form-control");
        add(messageInput);
//        add(messageHistoryLayout);
        add(messageList);
        messageList.setWidthFull();

//        messageHistoryLayout.setWidthFull();
//        messageHistoryLayout.setSpacing(true);
        Button goBackToHome = new Button("Back to Home");
        goBackToHome.getElement().setAttribute("class", "btn btn-primary");
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
                    yesterday, "User");
            message1.setUserColorIndex(1);

            // Extract the AI response text from the JSON response
            String aiResponseText = chatController.generate(message).toString();
            int startIndex = aiResponseText.indexOf("=") + 1;
            int endIndex = aiResponseText.lastIndexOf("}");
            String aiMessage = aiResponseText.substring(startIndex, endIndex);

            MessageListItem message2 = new MessageListItem(aiMessage,
                    fiftyMinsAgo, "Mistral");
            message2.setUserColorIndex(2);
            list.setItems(Arrays.asList(message1, message2));
            add(list);
        } else {
            System.err.println("Please enter a message");
        }
    }








}
