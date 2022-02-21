package sample.controllers;

import sample.Configs;
import sample.Const;
import sample.User;

import java.beans.Statement;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public ArrayList<String[]> selectQuery(String select){
        ArrayList<String[]> rows = new ArrayList<>();

        try {
            PreparedStatement ps = getDbConnection().prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String[] row = new String[3];
                row[0] = rs.getString(1);
                row[1] = rs.getString(  2);
                row[2] = rs.getString(3);
                rows.add(row);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        for (String[] row: rows){
            for (String el: row){
                System.out.print(el + "      ");
            }
            System.out.println();
        }

        return rows;
    }

    public void update(String query){
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(query);
            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void signUpUser(String firstname, String lastname, String username, String password, String usertype){
        String insert = "INSERT INTO " + Const.USERS_TABLE + "("
                + Const.USERS_FIRSTNAME + "," + Const.USERS_LASTNAME + ","
                + Const.USERS_USERNAME + "," + Const.USERS_PASSWORD + "," + Const.USERS_TYPE + ",salary)"
                + "VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, firstname);
            prSt.setString(2, lastname);
            prSt.setString(3, username);
            prSt.setString(4, password);
            prSt.setString(5, usertype);
            prSt.setString(6, "0");

            System.out.println(insert);

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean checkAuth(String login, String password){
        String select = "SELECT * FROM " + Const.USERS_TABLE + " WHERE "
                + Const.USERS_USERNAME + " = \"" + login + "\" AND "
                + Const.USERS_PASSWORD + " = \"" + password + "\"";
        System.out.println(select);

        int res = 0;

        try {
            PreparedStatement ps = getDbConnection().prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString(Const.USERS_ID));
                res += 1;
                User.user_id = rs.getString(Const.USERS_ID);
                User.user_firstname = rs.getString(Const.USERS_FIRSTNAME);
                User.user_lastname = rs.getString(Const.USERS_LASTNAME);
                User.user_username = rs.getString(Const.USERS_USERNAME);
                User.user_password = rs.getString(Const.USERS_PASSWORD);
                User.user_type = rs.getString(Const.USERS_TYPE);
                User.user_salary = rs.getInt("salary");
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return res == 1;
    }
}
