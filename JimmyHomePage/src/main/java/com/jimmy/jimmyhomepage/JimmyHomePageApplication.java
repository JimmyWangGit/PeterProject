package com.jimmy.jimmyhomepage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;


@RestController
@SpringBootApplication

public class JimmyHomePageApplication {

    public static void main(String[] args) {
        SpringApplication.run(JimmyHomePageApplication.class, args);
    }

    @GetMapping("/highschool")
    public String highschool (){
        return "Highschool";
    }


    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    @GetMapping("/springPrj/add")
    @CrossOrigin
    public void AddComments(@RequestParam(value = "comment", defaultValue = "World") String commentValue) {
        AddComment(commentValue);
    }

    @GetMapping("/srpingPrj/get")
    public void GetComments(@RequestParam(value = "comment", defaultValue = "World") String commentValue) {
        GetComment(commentValue);

    }


    public void AddComment(String commentValue) {
        try {
            Connection conn= connectDB();

            Calendar calendar = Calendar.getInstance();
            java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

            String query = " insert into comment (date, comment)"
                    + " values (?, ?)";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setDate(1, startDate);
            preparedStmt.setString(2,commentValue);

            preparedStmt.execute();

            conn.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }
    }



    public Connection connectDB() {
        try {
            String myDriver =
                    "com.mysql.cj.jdbc.Driver";


            Class.forName(myDriver);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        try{
            String myUrl = "jdbc:mysql://localhost:3306/comment";
            Connection conn = DriverManager.getConnection(myUrl, "root", "WAN1384-8");
            return conn;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public void GetComment(String commentValue) {

        Connection conn = connectDB();
        Calendar calendar = Calendar.getInstance();
        java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());

        try {
            String query = " insert into comment (date, comment)"
                    + " values (?, ?)";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setDate(1, startDate);
            preparedStmt.setString(2, commentValue);

            preparedStmt.execute();

            conn.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}