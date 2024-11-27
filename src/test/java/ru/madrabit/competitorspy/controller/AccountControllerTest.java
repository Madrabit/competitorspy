package ru.madrabit.competitorspy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.madrabit.competitorspy.dto.SubscribeNewProductsDTOResp;
import ru.madrabit.competitorspy.service.AccountService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {
    @MockBean
    AccountService service;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    void whenUserChooseToGetUpdatesReturnSubscribeNotification() throws Exception {
        SubscribeNewProductsDTOResp dtoResp = new SubscribeNewProductsDTOResp("Вы подписались на уведомления о новых семинарах", true);
        when(service.subscribeNewProducts(1l)).thenReturn(dtoResp);
        mockMvc.perform(MockMvcRequestBuilders.post("/account/notifications")
                        .queryParam("userId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Вы подписались на уведомления о новых семинарах"))
                .andExpect(jsonPath("$.isSubscribe").value(true));
    }
}
