package com.example.hamsterapp.ModelsRETROFIT;

public class UserData {
    private int id;
    private String name;
    private String ApP;
    private String ApM;
    private int is_active;
    private String email;
    private String email_verified_at;
    private String created_at;
    private String updated_at;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApP() {
        return ApP;
    }

    public void setApP(String apP) {
        ApP = apP;
    }

    public String getApM() {
        return ApM;
    }

    public void setApM(String apM) {
        ApM = apM;
    }
}
