package com.example.board.dto;

import com.example.board.persistence.entity.BoardColumnKindEnum;

public record BoardColumnDTO (Long id, String name, BoardColumnKindEnum kind, int cardsAmount) {

}
