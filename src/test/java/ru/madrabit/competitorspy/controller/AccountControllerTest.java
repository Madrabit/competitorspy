package ru.madrabit.competitorspy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.madrabit.competitorspy.service.AccountService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        when(service.subscribeNewProducts(1l)).thenReturn("Вы подписались на уведомления о новых семинарах");
        mockMvc.perform(MockMvcRequestBuilders.post("/account/notifications")
                        .queryParam("userId", "1l")
                        .queryParam("newProducts", "true"))
                .andExpect(status().isOk())
                .andExpect(content().string("Вы подписались на уведомления о новых семинарах"));
    }
}
