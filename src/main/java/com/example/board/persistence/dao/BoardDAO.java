package com.example.board.persistence.dao;

import com.example.board.persistence.entity.BoardEntity;
import com.mysql.jdbc.StatementImpl;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

@AllArgsConstructor
public class BoardDAO {

    private final Connection connection;

    public BoardEntity insert(final BoardEntity entity) throws SQLException {
        try (var statement = connection.prepareStatement("INSERT INTO board (name) VALUES (?)")) {
            statement.setString(1, entity.getName());
            statement.executeUpdate();
            if (statement instanceof StatementImpl impl){
                entity.setId(impl.getLastInsertID());
            }
        }
        return entity;
    }

    public void delete(final Long id) throws SQLException {
        try (var statement = connection.prepareStatement("DELETE FROM BOARDS WHERE id = ?")){
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    public Optional<BoardEntity> findById(final Long id) throws SQLException {
        try (var statement = connection.prepareStatement("SELECT id, name FROM BOARDS WHERE id = ?")) {
            statement.setLong(1, id);
            statement.executeQuery();
            var resultSet = statement.getResultSet();
            if (resultSet.next()) {
                var entity = new BoardEntity();
                entity.setId(resultSet.getLong("id"));
                entity.setName(resultSet.getString("name"));
                return Optional.of(entity);
            }
            return Optional.empty();
        }
    }

    public boolean exists(final Long id) throws SQLException {
        try (var statement = connection.prepareStatement("SELECT 1 FROM BOARDS WHERE id = ?")) {
            statement.setLong(1, id);
            statement.executeQuery();
            return statement.getResultSet().next();
        }
    }
}
