package com.example.board.service;

import com.example.board.persistence.dao.BoardColumnDAO;
import com.example.board.persistence.dao.BoardDAO;
import com.example.board.persistence.entity.BoardEntity;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

@AllArgsConstructor
public class BoardQueryService {

    private final Connection connection;

    public Optional <BoardEntity> findById( final Long id) throws SQLException {
        var dao = new BoardDAO(connection);
        var boardColumnDAO = new BoardColumnDAO(connection);
        var optional = dao.findById(id);
        if (optional.isPresent()) {
           var entity = optional.get();
           entity.setBoardColumns(boardColumnDAO.findByBoardId(entity.getId()));
           return Optional.of(entity);
        }
        return Optional.empty();
    }
}
