package com.luucasor.goldenraspberryawards.dtos;

public class LoginDTO {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public String getLowerCaseEmail(){
        return getEmail().toLowerCase();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
