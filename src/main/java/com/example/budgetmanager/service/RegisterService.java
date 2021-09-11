package com.example.budgetmanager.service;

import com.example.budgetmanager.repository.RegisterRepository;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private final RegisterRepository registerRepository;

    public RegisterService(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }
}
