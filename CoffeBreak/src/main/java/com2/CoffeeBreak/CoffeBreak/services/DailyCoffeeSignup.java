package com2.CoffeeBreak.CoffeBreak.services;


import com2.CoffeeBreak.CoffeBreak.model.DailyCoffee;
import com2.CoffeeBreak.CoffeBreak.model.Users;
import com2.CoffeeBreak.CoffeBreak.model.DailyUsers;
import com2.CoffeeBreak.CoffeBreak.repository.DailyUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DailyCoffeeSignup {

    @Autowired
    private DailyUserRepository dailyRepo;
    public void addDailyUser(DailyCoffee request) {
        if (request.isDaily()) {
            log.info("DAILY USER");
            DailyUsers newUser = new DailyUsers(request.getUsername(), request.isPurpose(), request.getNumber());
            dailyRepo.save(newUser);
        }
    }
}
