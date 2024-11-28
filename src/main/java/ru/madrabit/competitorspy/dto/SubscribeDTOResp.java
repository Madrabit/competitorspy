package ru.madrabit.competitorspy.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import ru.madrabit.competitorspy.entity.Product;

public record SubscribeDTOResp(
        @Positive(message = "id не может быть меньше нуля")
        long userId,
        @NotNull(message = "product не может быть null")
        Product product,
        @NotEmpty(message = "не может быть пустым")
        String success) {
}
