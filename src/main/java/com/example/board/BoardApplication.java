package com.example.board;

import com.example.board.persistence.migration.MigrationStrategy;
import com.example.board.ui.MainMenu;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

import static com.example.board.persistence.config.ConnectionConfig.getConnection;

@SpringBootApplication
public class BoardApplication {

    public static void main(String[] args) throws SQLException {
        try (var connection = getConnection()){
            new MigrationStrategy(connection).executeMigration();
        }
        new MainMenu().execute();
    }

}
