package ru.madrabit.competitorspy.dto;

import ru.madrabit.competitorspy.entity.Product;
import ru.madrabit.competitorspy.entity.Program;

public record ProductProgramDTOResp(Product product, Program programActual, Program programPrev) {
}
