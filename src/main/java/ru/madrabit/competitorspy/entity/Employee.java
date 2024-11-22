package ru.madrabit.competitorspy.entity;

import lombok.Builder;

@Builder
public record Employee( long id, String name) {
}
