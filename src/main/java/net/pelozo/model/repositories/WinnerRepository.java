package net.pelozo.model.repositories;

import net.pelozo.model.Human;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class WinnerRepository {

    private static final String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/tournament";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private static WinnerRepository instance;

    private WinnerRepository(){}


    public static WinnerRepository getInstance(){
        if(instance == null){
            instance = new WinnerRepository();
        }
        return instance;
    }


    public void addWinner(Human winner) throws ClassNotFoundException, SQLException {

            Class.forName(DATABASE_DRIVER);
            Connection con = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);


            Statement stInsert = con.createStatement();
            stInsert.executeUpdate("INSERT INTO winners (name, consumed) values " +
                    "('"+ winner.getName()+"', "+ winner.getDrinkConsumed()+")");

            stInsert.close();
            con.close();

    }
}
