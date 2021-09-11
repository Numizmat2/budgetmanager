package com.example.budgetmanager.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class Utils {

    public static Double convertFundsAmount(String stringValue) {
        var converted = 0.0;
        try {
            converted = Double.parseDouble(stringValue);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong format fof funds amount provided");
        }

        return converted;
    }
}
