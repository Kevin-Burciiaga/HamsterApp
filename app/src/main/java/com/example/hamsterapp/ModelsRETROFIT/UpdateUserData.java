package com.example.hamsterapp.ModelsRETROFIT;

public class UpdateUserData {
    private String name;
    private String ApP, ApM;

    public UpdateUserData(String name, String ApP, String ApM) {
        this.name = name;
        this.ApP = ApP;
        this.ApM = ApM;
    }

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
