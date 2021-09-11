package com.example.budgetmanager.controller;

import com.example.budgetmanager.model.entities.Register;
import com.example.budgetmanager.service.RegisterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "/api")
public class RegisterController {

    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PutMapping(path = "/recharge/{registerName}/{amount}")
    @ResponseBody
    public ResponseEntity<Register> rechargeRegister(@PathVariable String registerName, @PathVariable String amount) {
        var updatedRegister = registerService.rechargeRegister(registerName, amount);
        return ResponseEntity.ok().body(updatedRegister);
    }

    @PutMapping(path = "/transfer/{originRegister}/{targetRegister}/{amount}")
    public HttpStatus transferFunds(@PathVariable String originRegister,
                                    @PathVariable String targetRegister,
                                    @PathVariable String amount) {
        registerService.transferFunds(originRegister, targetRegister, amount);
        return HttpStatus.OK;
    }

    @GetMapping(path = "/currentBalance")
    @ResponseBody
    public ResponseEntity<String> getCurrentBalance() {
        var currentBalance = registerService.getCurrentBalance();
        return ResponseEntity.ok().body(currentBalance);
    }
}
