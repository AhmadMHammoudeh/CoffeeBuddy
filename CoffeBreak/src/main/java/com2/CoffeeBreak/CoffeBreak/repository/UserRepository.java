package com2.CoffeeBreak.CoffeBreak.repository;


import com2.CoffeeBreak.CoffeBreak.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
}
