package com.example.hamsterapp.ModelsRETROFIT;
import com.example.hamsterapp.ModelsRETROFIT.UserData;

import java.util.List;

public class ApiResponse {
    private String message;
    private String token;
    private String token_type;
    private long expires_in;

    private List<String> messages;
    private UserData data;

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }


    public String getMessage() {
        return message;
    }


    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
