package ru.madrabit.competitorspy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.madrabit.competitorspy.dto.LogsDTOResp;
import ru.madrabit.competitorspy.dto.SetParseFrequencyDTOResp;
import ru.madrabit.competitorspy.service.ParserService;
import ru.madrabit.competitorspy.util.ParsingStatus;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ParserAdminController.class)
public class ParserAdminControllerTest {
    @MockBean
    ParserService service;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        // Подключение JavaTimeModule для LocalDateTime
        objectMapper.registerModule(new JavaTimeModule());
        // Отключение записи дат как таймштампов
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
    @Test
    public void whenSetParsingFrequencyThenParseAfter2hours() throws Exception {
        SetParseFrequencyDTOResp dtoResp = new SetParseFrequencyDTOResp(2);
        when(service.setFrequency(2)).thenReturn(dtoResp);
        mockMvc.perform(MockMvcRequestBuilders.get("/parser/admin/")
                        .param("frequency", "2"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(dtoResp)));
    }

    /*
    Запуск парсера вручную
    "Как администратор, я хочу иметь возможность вручную запускать парсер, чтобы контролировать процесс сбора данных."
    * */
    @Test
    public void whenPressStartParseImmediately() throws Exception {
        when(service.startParsing(true)).thenReturn("Парсер запущен");

        mockMvc.perform(MockMvcRequestBuilders.get("/parser/admin/start")
                        .param("start", "true"))
                .andExpect(status().isOk())
                .andExpect(content().string("Парсер запущен"));
    }

    /*
    Просмотр статуса парсера
    "Как администратор, я хочу видеть текущий статус работы парсера, чтобы понимать, идет ли сбор данных."
     */
    @Test
    public void whenCheckStatusThenReturnInProgress() throws Exception {
        when(service.checkStatus()).thenReturn(ParsingStatus.IN_PROGRESS);
        mockMvc.perform(MockMvcRequestBuilders.get("/parser/admin/status"))
                .andExpect(status().isOk())
                .andExpect(content().string("IN_PROGRESS"));
    }

    /*
    Просмотр логов работы парсера
    "Как администратор, я хочу просматривать логи работы парсера, чтобы диагностировать ошибки или отслеживать успех операций."
    */
    @Test
    public void whenGetLogsThenReturnLogs() throws Exception {
        LogsDTOResp dtoResp = new LogsDTOResp(
                LocalDateTime.of(2024, 11, 27, 12, 0, 0),
                ParsingStatus.START,
                ParsingStatus.IN_PROGRESS,
                        LocalDateTime.of(2024, 11, 27, 12, 0, 0),
                ParsingStatus.FINISHED
        );
        when(service.getLogs()).thenReturn(dtoResp);

        mockMvc.perform(MockMvcRequestBuilders.get("/parser/admin/logs"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(dtoResp)));
    }
}
