package com2.CoffeeBreak.CoffeBreak.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import org.springframework.validation.annotation.Validated;

@Data
@Builder
@Jacksonized
@Validated
public class SignupRequest {
    @JsonProperty("login")
    private String login;
    @JsonProperty("number")
    private String number;
}
