package com.bi16java;

// SQL support
import java.sql.*;
// Java utils for lists and arrays
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tsytrin on 03.01.2017.
 */

// AccountDAOImpl implementation of the IAccountDAO interface.
//This class can contain all database specific Java code and SQL statements for Account entity.

public class AccountDAOImpl implements IAccountDAO {

    private static final String DELETE = "DELETE FROM Account WHERE Account_ID=?";
    private static final String FIND_ALL = "SELECT * FROM Account ORDER BY Account_ID";
    private static final String FIND_BY_ID = "SELECT * FROM Account WHERE Account_ID=?";
    private static final String INSERT = "INSERT INTO Account(FirstName, LastName, Country) VALUES(?, ?, ?)";
    private static final String UPDATE = "UPDATE Account SET FirstName=?, LastName=?, Country=? WHERE Account_ID=?";

    private Connection connection = null;

    public int delete(int accountID) {
        PreparedStatement stmt = null;
        try {
            if (connection == null || !connection.isValid(1)) {
                connection = ConnectionManager.getConnection();
            }
            stmt = connection.prepareStatement(DELETE);
            stmt.setInt(1, accountID);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return -1;
    }

    public Account get(int accountID) {
        PreparedStatement stmt = null;
        try {
            if (connection == null || !connection.isValid(1)) {
                connection = ConnectionManager.getConnection();
            }
            stmt = connection.prepareStatement(FIND_BY_ID);
            stmt.setInt(1, accountID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Account account = new Account(rs.getInt("Account_ID"), rs.getString("FirstName"),
                        rs.getString("LastName"), rs.getString("Country"));
                return account;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public int update(Account account) {
        PreparedStatement stmt = null;
        try {
            if (connection == null || !connection.isValid(1)) {
                connection = ConnectionManager.getConnection();
            }
            stmt = connection.prepareStatement(UPDATE);
            stmt.setString(1, account.getFirstName());
            stmt.setString(2, account.getLastName());
            stmt.setString(3, account.getCountry());
            stmt.setInt(4, account.getAccountID());
            return stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return -1;
    }

    public int insert(Account account) {

        PreparedStatement stmt = null;
        try {
            if (connection == null || !connection.isValid(1)) {
                connection = ConnectionManager.getConnection();
            }
            stmt = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, account.getFirstName());
            stmt.setString(2, account.getLastName());
            stmt.setString(3, account.getCountry());
            stmt.executeUpdate();

            ResultSet tableKeys = stmt.getGeneratedKeys();
            tableKeys.next();
            int autoGeneratedID = tableKeys.getInt(1);
            return autoGeneratedID;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return -1;
    }

    public List<Account> getAll() {
        PreparedStatement stmt = null;
        List<Account> list = new ArrayList<>();

        try {
            if (connection == null || !connection.isValid(1)) {
                connection = ConnectionManager.getConnection();
            }
            stmt = connection.prepareStatement(FIND_ALL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Account account = new Account(rs.getInt("Account_ID"), rs.getString("FirstName"),
                        rs.getString("LastName"), rs.getString("Country"));
                list.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }


}
