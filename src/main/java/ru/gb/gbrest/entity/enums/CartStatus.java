package ru.gb.gbrest.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CartStatus {
    EMPTY("empty"),
    NOT_EMPTY("not empty");
    private final String title;
}