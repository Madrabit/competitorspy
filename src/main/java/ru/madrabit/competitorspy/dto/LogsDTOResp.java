package ru.madrabit.competitorspy.dto;

import ru.madrabit.competitorspy.util.ParsingStatus;

import java.time.LocalDateTime;

public record LogsDTOResp(LocalDateTime startTime,
                          ParsingStatus start,
                          ParsingStatus inProgress,
                          LocalDateTime finishedTime,
                          ParsingStatus finished) {
}
