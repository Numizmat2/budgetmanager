package com.example.budgetmanager.service;

import com.example.budgetmanager.model.entities.Register;
import com.example.budgetmanager.model.entities.Transfer;
import com.example.budgetmanager.repository.RegisterRepository;
import com.example.budgetmanager.repository.TransferRepository;
import com.example.budgetmanager.utils.Utils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RegisterService {

    private final RegisterRepository registerRepository;
    private final TransferRepository transferRepository;

    public RegisterService(RegisterRepository registerRepository, TransferRepository transferRepository) {
        this.registerRepository = registerRepository;
        this.transferRepository = transferRepository;
    }

    public Register rechargeRegister(String registerName, String amount) {
        validateRegisters(registerName);
        var amountToAdd = Utils.convertFundsAmount(amount);
        var register = registerRepository.findFirstByNameOrderByTimestampDesc(registerName).get();
        var newAmount = Double.sum(amountToAdd, register.getValue());

        return registerRepository.save(new Register(registerName, Double.toString(newAmount)));
    }

    public void transferFunds(String originRegisterName, String targetRegisterName, String amount) {
        validateRegisters(originRegisterName, targetRegisterName);
        var amountToTransfer = Utils.convertFundsAmount(amount);
        var currentTimestamp = new Date();

        var originRegister = registerRepository.findFirstByNameOrderByTimestampDesc(originRegisterName).get();
        var targetRegister = registerRepository.findFirstByNameOrderByTimestampDesc(targetRegisterName).get();

        var originResult = originRegister.getValue() - amountToTransfer;
        if (originResult < 0) throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Insufficient funds to make a transfer.");
        var targetResult = Double.sum(targetRegister.getValue(), amountToTransfer);

        transferRepository.save(new Transfer(originRegister, targetRegister, currentTimestamp, amountToTransfer.toString()));

        registerRepository.saveAll(List.of(
                new Register(originRegisterName, Double.toString(originResult), currentTimestamp),
                new Register(targetRegisterName, Double.toString(targetResult), currentTimestamp))
        );
    }

    public Map<String, Double> getCurrentBalance() {
        var currentRegistersList = registerRepository.getCurrentBalance();

        return currentRegistersList.stream().collect(Collectors.toMap(Register::getName, Register::getValue));
    }

    private void validateRegisters(String... registersNames) {
        var invalidRegisters = new HashSet<String>();

        Arrays.stream(registersNames).forEach(name -> {
            name = Utils.fromSnakeCase(name);
            var registerName = registerRepository.findFirstByNameOrderByTimestampDesc(name);
            if (registerName.isEmpty()) invalidRegisters.add(name);
        });

        if (!invalidRegisters.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "No existing registers: " +  String.join(", ", invalidRegisters));

        if (registersNames.length != new HashSet(Arrays.asList(registersNames)).size()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Origin and target registers cannot be the same");
    }

}
