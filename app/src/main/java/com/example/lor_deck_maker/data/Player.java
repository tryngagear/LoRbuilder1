package com.example.lor_deck_maker.data;

public class Player {
    private String userName;
    private String password;

    public Player(String name, String pass){
        this.userName = name;
        this.password = pass;
    }

    public String getUserName() {return userName;}
}
