package com2.CoffeeBreak.CoffeBreak.model;

import lombok.AllArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "daily")
public class DailyUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private boolean education;

    private String number;

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    @CreatedDate
    private Date dateToday = new Date();

    public Date getDateToday() {
        return dateToday;
    }

    public int getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEducation(boolean education) {
        this.education = education;
    }

    public String getUsername() {
        return username;
    }

    public boolean isEducation() {
        return education;
    }

    public DailyUsers(){}
    public DailyUsers(String username, boolean education, String number) {
        this.username = username;
        this.education = education;
        this.number = number;
    }
}
