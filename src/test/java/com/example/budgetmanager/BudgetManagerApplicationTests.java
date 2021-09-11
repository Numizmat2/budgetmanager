package com.example.budgetmanager;

import com.example.budgetmanager.repository.RegisterRepository;
import com.example.budgetmanager.repository.TransferRepository;
import com.example.budgetmanager.service.RegisterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BudgetManagerApplicationTests {

    @Mock
    private RegisterRepository registerRepository;

    @Mock
    private TransferRepository transferRepository;

    @InjectMocks
    private RegisterService registerService;


    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void contextLoads() {
    }

    @Test
    void rechargeRegisterShouldThrowAnErrorIfRegisterIsInvalid() {
        Mockito.lenient().doReturn(Optional.empty()).when(registerRepository).findFirstByNameOrderByTimestampDesc(anyString());

        var ex = Assertions.assertThrows(ResponseStatusException.class, () -> {
            registerService.rechargeRegister("invalid", "100");
        });

        Assertions.assertEquals("No existing registers: invalid", ex.getReason());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, ex.getStatus());
    }

}
