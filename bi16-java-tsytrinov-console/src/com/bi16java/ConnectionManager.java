package com.bi16java;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by tsytrin on 03.01.2017.
 */
public class ConnectionManager {

    //static reference to itself
    private static ConnectionManager instance = new ConnectionManager();
    public static String dbServerURL;
    public static String dbAccount;
    public static String dbPassword;
    public static String dbDriver;

    private ConnectionManager() {
        initConfigParameters();
        try {
            Class.forName(dbDriver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static void initConfigParameters() {

        PropertiesConfiguration config = new PropertiesConfiguration();
        try {
            config.load("config.properties");
        } catch (ConfigurationException ex) {
            System.out.println("WARNINIG: Configuration parameters could not be read. Program quits");
            System.exit(0);
        }
        dbServerURL = config.getString("DB_SERVER_URL");
        dbAccount = config.getString("DB_ACCOUNT");
        dbPassword = config.getString("DB_PASSWORD");
        dbDriver = config.getString("DRIVER_CLASS");
    }


    private Connection createConnection() {
        initConfigParameters();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbServerURL, dbAccount, dbPassword);
        } catch (SQLException e) {
            System.out.println("ERROR: Unable to connect to database bi16-java on MySQL server!");
        }
        return connection;
    }

    public static Connection getConnection() {

        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance.createConnection();
    }



}
