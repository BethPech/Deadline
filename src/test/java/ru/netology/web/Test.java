package ru.netology.web;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;

import java.sql.*;

public class Test {
    @BeforeEach
    void setUp() throws SQLException {
        val faker = new Faker();
        String dataSQL = "INSERT INTO users(login, password) VALUES (?,?);";

        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysqldb://localhost:3306/app", "app", "pass"
                );
                PreparedStatement dataStmt = conn.prepareStatement(dataSQL);
        ) {
            dataStmt.setString(1, faker.name().username());
            dataStmt.setString(2, "password");
            dataStmt.executeUpdate();

            dataStmt.setString(1, faker.name().username());
            dataStmt.setString(2, "passvort");
            dataStmt.executeUpdate();
        }
    }
    @org.junit.jupiter.api.Test
    void stubSQL()throws SQLException{
        val countSQL= "SELECT COUNT(*) FROM users;";
        val cardsSQL="SELECT id, number, balance_in_kopecks FROM cards WHERE used_id = ?;";

        try (
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysqldb://localhost:3306/app", "app", "pass"
            );
            Statement countStmt = conn.createStatement();
            PreparedStatement cardsStmt = conn.prepareStatement(cardsSQL);
        ){
            try (
                    val  rs = countStmt.executeQuery(countSQL)){
                if (rs.next()){
                    val count = rs.getInt(1);
                    System.out.println(count);

                }

            }
        }
    }

}
