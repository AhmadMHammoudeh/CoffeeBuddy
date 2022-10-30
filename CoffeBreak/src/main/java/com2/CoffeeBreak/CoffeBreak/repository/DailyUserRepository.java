package com2.CoffeeBreak.CoffeBreak.repository;


import com2.CoffeeBreak.CoffeBreak.model.DailyUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DailyUserRepository extends JpaRepository<DailyUsers, Integer> {
    DailyUsers findByNumber(String number);
}
