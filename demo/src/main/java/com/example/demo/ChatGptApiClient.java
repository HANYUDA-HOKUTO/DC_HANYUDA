package com.example.demo;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import org.json.JSONArray;
import org.json.JSONObject;

public class ChatGptApiClient {



//    public static void main(String[] args) {
//        try {
//            String responseMessage = callChatGptApi();
//            System.out.println("Response from ChatGPT: " + responseMessage);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public static String callChatGptApi(Person person) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        String prompt = "次の武将が2024年に生きていたらやりそうな事を教えて下さい。\n"+person.getName()+"\n";
        JSONArray messages = new JSONArray();
        messages.put(new JSONObject().put("role", "system").put("content", "You are a helpful assistant."));
        messages.put(new JSONObject().put("role", "user").put("content", prompt));

        JSONObject requestBody = new JSONObject();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("messages", messages);
        requestBody.put("temperature", 1.0);
        requestBody.put("max_tokens", 512);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + API_KEY)
                .POST(BodyPublishers.ofString(requestBody.toString(), StandardCharsets.UTF_8))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            JSONObject jsonResponse = new JSONObject(response.body());
            return jsonResponse
                    .getJSONArray("choices")
                    .getJSONObject(0)
                    .getJSONObject("message")
                    .getString("content");
        } else {
            throw new RuntimeException("Failed to call ChatGPT API: " + response.body());
        }
    }
}
