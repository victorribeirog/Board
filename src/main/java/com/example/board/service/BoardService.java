package com.example.board.service;

import com.example.board.persistence.dao.BoardDAO;
import lombok.AllArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;

@AllArgsConstructor
public class BoardService {

    private final Connection connection;

    public boolean delete(final Long id) throws SQLException {
        var dao = new BoardDAO(connection);
        try{
            if (!dao.exists(id)){
                return false;
            }
            dao.delete(id);
            connection.commit();
            return true;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }
}
