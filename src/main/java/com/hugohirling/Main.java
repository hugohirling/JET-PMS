package com.hugohirling;
import com.hugohirling.objects.Participant;

import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        ConsolePrinter.printWelcome();

        DatabaseAccessClass databaseAccessClass = new DatabaseAccessClass();

        try{
            databaseAccessClass.connect();
            ConsolePrinter.printInfo("Connection to database established!");
        }catch(SQLException e) {
            ConsolePrinter.printError("Connection to database failed!");
            ConsolePrinter.printError(e.getMessage());
        }

        try{
            databaseAccessClass.loadAllDataFromTable("participants");
            ConsolePrinter.printInfo("Retrieved participants successfully!");
        }catch (SQLException e) {
            ConsolePrinter.printError("Couldn't retrieve participants!");
            ConsolePrinter.printError(e.getMessage());
        }

        ManagementSystem ms = new ManagementSystem(databaseAccessClass.connection);
        List<Participant> participants = ms.getAllParticipants();
        System.out.println("a");
    }
}