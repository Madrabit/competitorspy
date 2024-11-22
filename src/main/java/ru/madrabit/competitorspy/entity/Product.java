package ru.madrabit.competitorspy.entity;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Data
public class Product {
    private long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private int price;
    private LastUpdated lastUpdated;
    @AllArgsConstructor
    @Getter
    @Setter
    public static class LastUpdated {
        private LocalDateTime name;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private LocalDateTime price;
    }
}
