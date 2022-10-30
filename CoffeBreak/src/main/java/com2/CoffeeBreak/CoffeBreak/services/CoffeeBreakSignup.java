package com2.CoffeeBreak.CoffeBreak.services;

import com2.CoffeeBreak.CoffeBreak.model.SignupRequest;
import com2.CoffeeBreak.CoffeBreak.model.Users;
import com2.CoffeeBreak.CoffeBreak.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoffeeBreakSignup {

    @Autowired
    private UserRepository repo;
    public Users addUser(SignupRequest request) {
        System.out.println(request);
        Users newUser = new Users(request.getLogin(), request.getNumber());
        repo.save(newUser);
        return newUser;
    }
}
