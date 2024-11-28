package ru.madrabit.competitorspy.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.madrabit.competitorspy.dto.ProductProgramDTOResp;
import ru.madrabit.competitorspy.entity.Product;
import ru.madrabit.competitorspy.entity.Program;
import ru.madrabit.competitorspy.service.ProductService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    private static Product product;
    private static Program programActual;
    private static Program programPrev;

    @MockBean
    ProductService service;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeAll
    public static void setUp() {
        product = new Product(1,
                "ПОДФТ",
                LocalDate.of(2024, 2, 3),
                LocalDate.of(2024, 2, 5),
                10990,
                new Product.LastUpdated(
                        LocalDateTime.of(2014, 04, 8, 12, 30, 00),
                        LocalDateTime.now(),
                        LocalDateTime.of(2014, 04, 8, 12, 30, 00),
                        LocalDateTime.now()));

        programActual = new Program(
                2,
                1,
                "Описание программы ...",
                LocalDateTime.of(2014, 04, 8, 12, 30, 00)
        );
        programPrev = new Program(
                1,
                1,
                "Старое: Описание программы ...",
                LocalDateTime.of(2013, 03, 7, 11, 00, 00)
        );

    }

    @Test
    public void retrieveFullProductsList() throws Exception {
        List<Product> mockProducts = List.of(product);
        when(service.getAll()).thenReturn(mockProducts);
        mockMvc.perform(MockMvcRequestBuilders.get("/products/"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockProducts)));

    }

    /*
    * Когда нажимаешь на семинар и проваливаешься в его описание. С визуализацией изменений в тексте программы.
    * */
    @Test
    public void retrieveProgramByProductId() throws Exception {
        ProductProgramDTOResp dtoResp = new ProductProgramDTOResp(product, programActual, programPrev);
        when(service.getProgamById(1l)).thenReturn(dtoResp);
        mockMvc.perform(MockMvcRequestBuilders.get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(dtoResp)));
    }

    @Test
    public void retrieve20PageProductsList() throws Exception {
        List<Product> mockProducts = List.of(product);
        Page<Product> mockPage = new PageImpl<>(mockProducts);
        when(service.getProductPaginated(2,20)).thenReturn(mockPage);
        mockMvc.perform(MockMvcRequestBuilders.get("/products/paginated?page=2&size=20"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockPage)));

    }
}
