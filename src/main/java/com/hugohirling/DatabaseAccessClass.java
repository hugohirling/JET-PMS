package com.hugohirling;

import com.hugohirling.objects.Participant;

import java.sql.*;
public class DatabaseAccessClass {

    public Connection connection;

    public void connect() throws SQLException {
        this.connection = DriverManager.getConnection("censored", "censored", "censored");
    }

    public ResultSet loadAllDataFromTable(final String tablename) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("select * from " + tablename);
        return ps.executeQuery();
    }
}
