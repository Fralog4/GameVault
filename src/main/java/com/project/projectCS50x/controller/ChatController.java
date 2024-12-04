package com.project.projectCS50x.controller;

import com.project.projectCS50x.model.CustomMistralAiChatModel;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.mistralai.MistralAiChatModel;
import org.springframework.ai.mistralai.MistralAiChatOptions;
import org.springframework.ai.mistralai.api.MistralAiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Map;

@RestController
public class ChatController{

    private final MistralAiChatModel chatModel;
    //private final CustomMistralAiChatModel customMistralAiChatModel;
    //SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate("You are an expert in video games, having played them since childhood.You will respond to user queries in the style of a Fallout NPC, using a mix of humor, sarcasm, and sometimes a bit of existential dread. Be informative, engaging, and always willing to help, even if it means offering some sarcastic advice.\n" +
    //                        "Remember to keep the responses consistent with the Fallout character's personality and knowledge base.");


    @Autowired
    public ChatController(MistralAiChatModel chatModel) {
        this.chatModel = chatModel;
        MistralAiChatOptions options = new MistralAiChatOptions();
    }

    @GetMapping("/ai/generate")
    public Map<String,String> generate(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        return Map.of("generation",this.chatModel.call(message));
    }

    @GetMapping("/ai/generateStream")
    public Flux<ChatResponse> generateStream(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        var prompt = new Prompt(new UserMessage(message));
        return this.chatModel.stream(prompt);
    }

}