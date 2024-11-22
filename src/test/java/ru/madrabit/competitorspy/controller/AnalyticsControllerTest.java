package ru.madrabit.competitorspy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.madrabit.competitorspy.entity.ProductHistory;
import ru.madrabit.competitorspy.service.AnalyticsService;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AnalyticsController.class)
public class AnalyticsControllerTest {
    @MockBean
    AnalyticsService service;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void whenRetrieveProductsPopularityThenReturnTopPopularProducts() throws Exception {
        List<ProductHistory> productHistory = List.of(new ProductHistory(1, "ПОДФТ",
                LocalDate.of(2024, 2, 3),
                LocalDate.of(2024, 2, 5),
                10990),
                new ProductHistory(2, "СВК что-то",
                        LocalDate.of(2023, 1, 1),
                        LocalDate.of(2023, 1, 1),
                        20990)
                );
        when(service.retrieveTopPopular()).thenReturn(productHistory);
        mockMvc.perform(MockMvcRequestBuilders.get("/analytics/popularity"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(productHistory)));
    }


    @Test
    void retrievePriceDynamicsByProduct() throws Exception {
        List<ProductHistory> productHistory = List.of(new ProductHistory(1, "ПОДФТ",
                        LocalDate.of(2024, 2, 3),
                        LocalDate.of(2024, 2, 5),
                        10990),
                new ProductHistory(2, "СВК что-то",
                        LocalDate.of(2023, 1, 1),
                        LocalDate.of(2023, 1, 1),
                        20990)
        );
        when(service.retrieveHistoryOfProduct(1l)).thenReturn(productHistory);
        mockMvc.perform(MockMvcRequestBuilders.get("/analytics/products/{1}/history/"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(productHistory)));
    }
}
