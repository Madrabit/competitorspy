package ru.madrabit.competitorspy.entity;

import java.time.LocalDateTime;

public record Program(long id, long productId, String content, LocalDateTime lastUpdate) {
}
