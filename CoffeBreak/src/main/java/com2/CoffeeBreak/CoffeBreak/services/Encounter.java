package com2.CoffeeBreak.CoffeBreak.services;

import com2.CoffeeBreak.CoffeBreak.model.DailyUsers;
import com2.CoffeeBreak.CoffeBreak.model.Users;
import com2.CoffeeBreak.CoffeBreak.repository.DailyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Table;

@Service
@Table(name = "daily")
public class Encounter {

    @Autowired
    DailyUserRepository repo;

    public DailyUsers generate(String number) {
        DailyUsers user = repo.findByNumber(number);
        System.out.println("The number is " + user.getNumber());
        int num = user.getId() - 2;
        return repo.findById(num).get();
    }
}
