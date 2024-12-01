package com.project.projectCS50x;

import com.project.projectCS50x.service.MistralAIService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MistralAIServiceTest {

    @Autowired
    private MistralAIService mistralAIService;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    void whenPostRequestToAI_thenExpectedResponseIsReturned() {
        String mockResponse = "Hello, Mistral!";
        when(restTemplate.exchange(anyString(), any(), any(), eq(String.class)))
                .thenReturn(new ResponseEntity<>(mockResponse, HttpStatus.OK));

        String response = mistralAIService.postRequestToAI("/test-endpoint", "{}");
        assertThat(response).isEqualTo(mockResponse);
    }
}