package ru.madrabit.competitorspy.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import ru.madrabit.competitorspy.util.ParsingStatus;

import java.time.LocalDateTime;

public record LogsDTOResp(
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime startTime,
        ParsingStatus start,
        ParsingStatus inProgress,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime finishedTime,
        ParsingStatus finished) {
}
