package com2.CoffeeBreak.CoffeBreak.controllers;

import com2.CoffeeBreak.CoffeBreak.model.*;
import com2.CoffeeBreak.CoffeBreak.services.CoffeeBreakSignup;
import com2.CoffeeBreak.CoffeBreak.services.DailyCoffeeSignup;
import com2.CoffeeBreak.CoffeBreak.services.Encounter;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/CoffeeBreak")
@AllArgsConstructor
public class CoffeeBreakController {

    private final CoffeeBreakSignup addUser;
    private final Encounter encounter;
    private final DailyCoffeeSignup daily;
    @PostMapping(path = "/signup")
    public ResponseEntity<Users> signup(@ApiParam(value = "42 account login", required = true)
                                                      @RequestBody final SignupRequest request) {
        return ResponseEntity.ok(addUser.addUser(request));
    }

    @PostMapping(path = "/DailyCoffee")
    public ResponseEntity<String> dailyCoffee(@ApiParam(value = "Signup for todays coffee", required = true)
                                             @RequestBody final DailyCoffee request) {
        daily.addDailyUser(request);
        return ResponseEntity.ok(request.getUsername());
    }

    @GetMapping(path = "/Generate/{number}")
    public ResponseEntity<CoffeeResponse> generatePartner(@PathVariable String number) {
//        System.out.println(number);

        return ResponseEntity.ok(CoffeeResponse.builder().username(encounter.generate(number).getUsername()).build());
    }

}
