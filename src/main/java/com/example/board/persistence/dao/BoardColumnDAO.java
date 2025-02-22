package com.example.board.persistence.dao;

import com.example.board.persistence.entity.BoardColumnEntity;
import com.mysql.jdbc.StatementImpl;
import lombok.AllArgsConstructor;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@AllArgsConstructor
public class BoardColumnDAO {

    private final Connection connection;

    public BoardColumnEntity insert( final BoardColumnEntity entity) throws SQLException {
        try (var statement = connection.prepareStatement("INSERT INTO BOARDS_COLUMNS (name,`order`, kind, board_id) VALUES (?,?,?,?)")){
            var i = 1;
            statement.setString(i++, entity.getName());
            statement.setInt(i++, entity.getOrder());
            statement.setString(i++, entity.getKind().name());
            statement.setLong(i, entity.getBoard().getId());
            statement.executeUpdate();
            if (statement instanceof StatementImpl impl){
                entity.setId(impl.getLastInsertID());
            }
            return entity;
        }
    }

    public List<BoardColumnEntity> findByBoardId(Long id) throws SQLException {
        return null;
    }
}
