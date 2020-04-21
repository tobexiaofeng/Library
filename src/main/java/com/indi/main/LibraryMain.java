package com.indi.main;


import com.mysql.cj.jdbc.Driver;

import java.sql.*;

/**
 * @author 华峰
 * @create: 2020-04-06 11:54
 */
public class LibraryMain {
    static{
        try {
            Class.forName(Driver.class.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/library?charset=utf-8&useSSL=false&serverTimezone=GMT%2B8&allowPublicKeyRetrieval=true";
        Connection connection = DriverManager.getConnection(url,"root","123");
        PreparedStatement ps = connection.prepareStatement("select * from Library_user");
        ResultSet resultSet = ps.executeQuery();
        while(resultSet.next()){
            System.out.println(resultSet.getString(1));
            System.out.println(resultSet.getString(2));
        }
        resultSet.close();
        ps.close();
        connection.close();
    }
}
