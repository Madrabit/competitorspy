package ru.madrabit.competitorspy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.madrabit.competitorspy.dto.SubscribeDTOReq;
import ru.madrabit.competitorspy.dto.SubscribeDTOResp;
import ru.madrabit.competitorspy.entity.Product;
import ru.madrabit.competitorspy.entity.Subscribes;
import ru.madrabit.competitorspy.service.SubscribeService;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SubscribeController.class)
public class SubscribeControllerTest {

    @MockBean
    SubscribeService service;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


/*
* Подписка на обновления по семинарам:
"Как пользователь, я хочу подписаться на обновления по выбранным семинарам,
* чтобы получать уведомления об изменениях даты, программы или цены."
*/

    @Test
    public void whenSelectProductReturnUpdates() throws Exception {
        SubscribeDTOReq req = new SubscribeDTOReq(1l, List.of(1,2,3));
        SubscribeDTOResp resp = new SubscribeDTOResp(123, Product.builder().id(1).build(), "success" );
        when(service.subscribe(1l, List.of(1,2,3))).thenReturn(resp);
        mockMvc.perform(MockMvcRequestBuilders.post("/subscribe/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().json(objectMapper.writeValueAsString(resp)));

    }

    /**
     * Вернуть список того, на что уже подписан.
     * @throws Exception
     */
    @Test
    public void whenChooseEmplThenReturnSubscribes() throws Exception {
        Subscribes mockSubscribes = new Subscribes(123l, Collections.singletonList(Product.builder().id(1).name("ПОДФТ").build()));
        when(service.getSubscribes(1L)).thenReturn(mockSubscribes);
        mockMvc.perform(MockMvcRequestBuilders.get("/subscribe/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockSubscribes)));

    }
}
