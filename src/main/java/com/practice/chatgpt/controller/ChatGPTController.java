package com.practice.chatgpt.controller;

import com.practice.chatgpt.dto.ChatGPTRequest;
import com.practice.chatgpt.dto.ChatGPTResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/bot")
public class ChatGPTController {
    @Value("${openai.model}")
    private String model;
    @Value("${openai.api.url}")
    private String apiURL;

    @Autowired
    private RestTemplate template;
    @GetMapping("/chat")
    public String chat(@RequestParam("prompt")  String prompt){

        ChatGPTRequest chatGPTRequest = new ChatGPTRequest(model,"prompt");

       ChatGPTResponse response = template.postForObject(apiURL,chatGPTRequest,ChatGPTResponse.class);
         return response.getChoices().get(0).getMessage().getContent();
    }
}
