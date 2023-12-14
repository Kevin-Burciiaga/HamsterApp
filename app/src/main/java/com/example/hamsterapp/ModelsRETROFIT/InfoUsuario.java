package com.example.hamsterapp.ModelsRETROFIT;

public class InfoUsuario {

    private int id;
    private String name;
    private String ApP;
    private String ApM;
    private int is_active;
    private String email;
    private String email_verified_at;
    private String created_at;
    private String updated_at;

    public InfoUsuario(int id, String name, String apP, String apM, int is_active, String email, String email_verified_at, String created_at, String updated_at) {
        this.id = id;
        this.name = name;
        ApP = apP;
        ApM = apM;
        this.is_active = is_active;
        this.email = email;
        this.email_verified_at = email_verified_at;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getName() {
        return name;
    }

    public String getApP() {
        return ApP;
    }

    public String getApM() {
        return ApM;
    }

    public int getId() {
        return id;
    }

    public int getIs_active() {
        return is_active;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail_verified_at() {
        return email_verified_at;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }
}
