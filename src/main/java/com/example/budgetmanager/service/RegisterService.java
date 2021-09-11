package com.example.budgetmanager.service;

import com.example.budgetmanager.model.entities.Register;
import com.example.budgetmanager.repository.RegisterRepository;
import com.example.budgetmanager.utils.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class RegisterService {

    private final RegisterRepository registerRepository;

    public RegisterService(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    public Register rechargeRegister(String registerName, String amount) {
        validateRegisters(registerName);
        var amountToAdd = Utils.convertFundsAmount(amount);
        var register= registerRepository.findFirstByName(registerName).get();
        var newAmount = Double.sum(amountToAdd, register.getValue());

        return registerRepository.save(new Register(registerName, Double.toString(newAmount)));
    }

    public void transferFunds(String originRegister, String targetRegister, String amount) {
        validateRegisters(originRegister, targetRegister);

    }

    public String getCurrentBalance() {
        return null;
    }

    private void validateRegisters(String... registersNames) {
        var invalidRegisters = new HashSet<String>();

        Arrays.stream(registersNames).forEach(name -> {
            var registerName = registerRepository.findFirstByName(name);
            if (registerName.isEmpty()) invalidRegisters.add(name);
        });

        if (!invalidRegisters.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "No existing registers: " +  String.join(", ", invalidRegisters));
    }

}
