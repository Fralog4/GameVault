/***package com.project.projectCS50x.configuration;

/*
 * Copyright 2023-2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/***
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.model.AbstractToolCallSupport;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.model.function.FunctionCallback;
import org.springframework.ai.model.function.FunctionCallbackContext;
import org.springframework.ai.model.function.FunctionCallingOptions;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.List;

@Configuration
public class MistralAiChatModel extends AbstractToolCallSupport implements ChatModel {

    protected MistralAiChatModel(FunctionCallbackContext functionCallbackContext) {
        super(functionCallbackContext);
    }

    protected MistralAiChatModel(FunctionCallbackContext functionCallbackContext, FunctionCallingOptions functionCallingOptions, List<FunctionCallback> toolFunctionCallbacks) {
        super(functionCallbackContext, functionCallingOptions, toolFunctionCallbacks);
    }

    @Override
    public String call(String message) {
        return ChatModel.super.call(message);
    }

    @Override
    public String call(Message... messages) {
        return ChatModel.super.call(messages);
    }

    @Override
    public ChatResponse call(Prompt prompt) {
        return null;
    }

    @Override
    public ChatOptions getDefaultOptions() {
        return null;
    }

    @Override
    public Flux<String> stream(String message) {
        return ChatModel.super.stream(message);
    }

    @Override
    public Flux<String> stream(Message... messages) {
        return ChatModel.super.stream(messages);
    }

    @Override
    public Flux<ChatResponse> stream(Prompt prompt) {
        return ChatModel.super.stream(prompt);
    }
}***/