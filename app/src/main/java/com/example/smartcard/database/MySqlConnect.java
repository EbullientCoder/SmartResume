package com.example.smartcard.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;


public class MySqlConnect {
    private static MySqlConnect instance = null;
    private Connection conn = null;

    protected MySqlConnect() {}

    public static MySqlConnect getInstance() {
        if(MySqlConnect.instance==null) {
            MySqlConnect.instance = new MySqlConnect();
        }

        return MySqlConnect.instance;
    }


    public Connection getDBConnection() throws SQLException {

        if (this.conn == null){
            //Check the Language
            //English
            if(Locale.getDefault().getDisplayLanguage().equals("English"))
                this.conn = DriverManager.getConnection("jdbc:mysql://unispark-db.cmh7hqdc7yex.us-east-1.rds.amazonaws.com:3306/PocketPresenter", "admin", "password");

            //Italian
            else
                this.conn = DriverManager.getConnection("jdbc:mysql://unispark-db.cmh7hqdc7yex.us-east-1.rds.amazonaws.com:3306/SmartCardITA", "admin", "password");

        }
        return this.conn;
    }

}

