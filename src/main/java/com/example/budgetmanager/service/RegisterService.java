package com.example.budgetmanager.service;

import com.example.budgetmanager.model.entities.Register;
import com.example.budgetmanager.repository.RegisterRepository;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private final RegisterRepository registerRepository;

    public RegisterService(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    public Register rechargeRegister(String registerName, String amount) {
        return null;
    }

    public void transferFunds(String originRegister, String targetRegister, String amount) {

    }

    public String getCurrentBalance() {
        return null;
    }
}
