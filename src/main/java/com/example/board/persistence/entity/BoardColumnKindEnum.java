package com.example.board.persistence.entity;

import java.util.stream.Stream;

public enum BoardColumnKindEnum {

    INITIAL, FINAL, CANCEL, PENDING;

    public static BoardColumnKindEnum findByName(final String name)  {
        return Stream.of(BoardColumnKindEnum.values()).
                filter(e -> e.name().equals(name)).
                findFirst().orElseThrow();
    }
}
