package com.example.hamsterapp.ModelsRETROFIT;

public class RegistroData {
    private String name;
    private String email;
    private String ApP;
    private String ApM;
    private String password;
    private String password_confirmation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return password_confirmation;
    }

    public void setPasswordConfirmation(String password_confirmation) {
        this.password_confirmation = password_confirmation;
    }

     public RegistroData(String name, String email, String ApP, String ApM, String password, String password_confirmation) {
        this.name = name;
         this.email = email;
         this.ApP = ApP;
         this.ApM = ApM;
         this.password = password;
         this.password_confirmation = password_confirmation;
     }


}

