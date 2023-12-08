package com.example.hamsterapp.ModelsRETROFIT;

public class ApiResponse {
    private String message;
    private String token;
    private String token_type;
    private long expires_in;


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