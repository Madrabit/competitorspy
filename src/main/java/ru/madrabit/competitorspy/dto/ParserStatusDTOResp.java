package ru.madrabit.competitorspy.dto;

import ru.madrabit.competitorspy.util.ParsingStatus;

public record ParserStatusDTOResp(String message, ParsingStatus status) {
}
