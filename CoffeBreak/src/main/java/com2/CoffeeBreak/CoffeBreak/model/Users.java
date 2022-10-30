package com2.CoffeeBreak.CoffeBreak.model;


import javax.persistence.*;

@Entity
@Table(name = "42users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String username;

    private String number;

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Users(String username, String number) {
        this.setUsername(username);
        this.setNumber(number);
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
