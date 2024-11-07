package ru.lisenkova;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBCreationScript {
    public static void createDB() {
        try (Connection con = DriverManager.getConnection("jdbc:h2:mem:LibraryBD;DB_CLOSE_DELAY=-1","sa","")) {
            Statement stm = con.createStatement();
//            stm.executeUpdate("DROP TABLE Reader IF EXISTS");
//            stm.executeUpdate("CREATE TABLE Reader(ID INT PRIMARY KEY AUTO_INCREMENT, NAME VARCHAR(60), SURNAME VARCHAR(60), DATE DATE)");
            stm.executeUpdate("INSERT INTO Reader(ID, NAME, SURNAME, BIRTHDATE) VALUES(0,'Vasya','Vasiliev','1990-11-10')");
            stm.executeUpdate("INSERT INTO Reader(ID, NAME, SURNAME, BIRTHDATE) VALUES(1,'Ivan','Ivanov','2009-05-01')");
            //stm.executeUpdate("INSERT INTO Readers VALUES(3,'Petr','Petrov','19630923')");

          //  stm.executeUpdate("DROP TABLE Book IF EXISTS");
         //   stm.executeUpdate("CREATE TABLE Book(ISBN INT PRIMARY KEY, TITLE VARCHAR(255), AUTHOR VARCHAR(250))");
            stm.executeUpdate("INSERT INTO Book VALUES(9781841499895,'James S. A. Corey','Leviathan Wakes',null)");
            stm.executeUpdate("INSERT INTO Book VALUES(9781841499932,'James S. A. Corey','Abaddons Gate', null)");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
