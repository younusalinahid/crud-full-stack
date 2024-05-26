package com.example.restfulwebservices.jwt.resource;

import java.io.Serializable;

public class  JwtTokenRequest implements Serializable {

    private static final long serialVersionUID = -5616176897013108345L;

    private String username;
    private String password;

//    {
//        "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuYWhpZCIsImV4cCI6MTcxNjk3NzY4OSwiaWF0IjoxNzE2MzcyODg5fQ.Cqrn-IDoAcmUhOt9DswstofYTyFNq93pDV828e5Wsqxou3oLykBPQSnpVXD5A0fnnF2x77o9ujSiyBFRuwdRyQ"
//    }

    public JwtTokenRequest() {
        super();
    }

    public JwtTokenRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}