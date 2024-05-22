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
//	Git.iginoreにAPIを登録する方法明日聞く!!
    public String callChatGptApi(Person person, Person person2) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        String prompt = "次の武将が2024年に生きていたらやりそうな事を教えて下さい。\n"+person.getName()+"\n"
        +"次の人がこの武将と出会ったら何をやりそうか様子を教えて下さい。\n"+person2.getName()+"さん。"+person2.getAge()+"歳。趣味は"
        +person2.getHobby();
        JSONArray messages = new JSONArray();
        messages.put(new JSONObject().put("role", "system").put("content", "You are a helpful assistant."));
        messages.put(new JSONObject().put("role", "user").put("content", prompt));

        JSONObject requestBody = new JSONObject();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("messages", messages);
        requestBody.put("temperature", 1.0);
        requestBody.put("max_tokens", 1024);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ChatGptApiConst.API_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + ChatGptApiConst.API_KEY)
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
    
    public String questionChatGptApi(String keyword) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        String prompt = keyword+"は生前何をやりましたか。";
        JSONArray messages = new JSONArray();
        messages.put(new JSONObject().put("role", "system").put("content", "You are a helpful assistant."));
        messages.put(new JSONObject().put("role", "user").put("content", prompt));

        JSONObject requestBody = new JSONObject();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("messages", messages);
        requestBody.put("temperature", 1.0);
        requestBody.put("max_tokens", 1024);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ChatGptApiConst.API_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + ChatGptApiConst.API_KEY)
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
    
    public String castleChatGptApi(String castle) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        String prompt = "この"+castle+"は誰の城ですか。又現在における住所どこになりますか。";
        JSONArray messages = new JSONArray();
        messages.put(new JSONObject().put("role", "system").put("content", "You are a helpful assistant."));
        messages.put(new JSONObject().put("role", "user").put("content", prompt));

        JSONObject requestBody = new JSONObject();
        requestBody.put("model", "gpt-3.5-turbo");
        requestBody.put("messages", messages);
        requestBody.put("temperature", 1.0);
        requestBody.put("max_tokens", 1024);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ChatGptApiConst.API_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + ChatGptApiConst.API_KEY)
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
