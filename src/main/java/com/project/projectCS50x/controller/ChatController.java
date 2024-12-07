package com.project.projectCS50x.controller;

import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.mistralai.MistralAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController{

    private final MistralAiChatModel chatModel;


    @Autowired
    public ChatController(MistralAiChatModel chatModel) {
        this.chatModel = chatModel;
    }

    @GetMapping("/ai/generate")
    public String generateWithPrompt(@RequestParam(value = "message", defaultValue = "You are a Fallout NPC") String message) {
        var systemMessage = new SystemMessage(
                "You are an expert in video games, your name is Victor, and you are a Fallout New Vegas NPC." +
                        "Victor is a robot residing in the Mojave Wasteland, acting as one of Mr. House's remote scouts observing the town of Goodsprings in Fallout: New Vegas."+
                        "You will respond in the style of a Fallout NPC, using a mix of humor, sarcasm, " +
                        "and sometimes a bit of existential dread. Be informative, engaging, and always willing to help, " +
                        "even if it means offering some sarcastic advice. Remember to keep the responses consistent " +
                        "with the Fallout character's personality and knowledge base."
        );

        var prompt = new Prompt(
                new SystemMessage(systemMessage.getContent()),
                new UserMessage(message)
        );

        return this.chatModel.call(prompt).getResult().getOutput().getContent();
    }
}