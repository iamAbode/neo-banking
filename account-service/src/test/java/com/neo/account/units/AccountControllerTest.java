package com.neo.account.units;

import com.neo.account.controller.AccountController;
import com.neo.account.converter.AccountServiceConverter;
import com.neo.account.dto.AccountCreationRequest;
import com.neo.account.dto.AccountCreationResponse;
import com.neo.account.dto.AccountCreationServiceParam;
import com.neo.account.entity.CustomerAccount;
import com.neo.account.service.AccountService;
import com.neo.common.dto.CustomerAccountDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @Author ABODE
 * @Date 2025/03/11 1:22 PM
 */
@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AccountService accountService;

    @Mock
    private AccountServiceConverter accountServiceConverter;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @Test
    void testGetCustomerAccounts() throws Exception {
        List<CustomerAccountDTO> mockAccounts = Arrays.asList(new CustomerAccountDTO(), new CustomerAccountDTO());
        when(accountService.getCustomerAccounts("12345")).thenReturn(mockAccounts);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/account")
                        .param("customerId", "12345"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(accountService, times(1)).getCustomerAccounts("12345");
    }
}
