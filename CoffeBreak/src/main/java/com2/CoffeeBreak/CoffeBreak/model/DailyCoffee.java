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
public class DailyCoffee {
    @JsonProperty("login")
    private String username;
    @JsonProperty("number")
    private String number;
    @JsonProperty("daily")
    private boolean daily;
    @JsonProperty("purpose")
    private boolean purpose;
}
