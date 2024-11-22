package ru.madrabit.competitorspy.dto;

import ru.madrabit.competitorspy.entity.Product;

public record SubscribeDTOResp(long id, Product product, String success) {
}
