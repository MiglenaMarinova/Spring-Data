package lab;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter user:");
        String user = sc.nextLine();

        System.out.println("Enter pass:");
        String password = sc.nextLine();


        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/soft_uni", props);

        System.out.println("Enter salary:");
        PreparedStatement prst = connection.prepareStatement("SELECT * FROM employees WHERE salary > ?");

        String salary = sc.nextLine();
        prst.setDouble(1, Double.parseDouble(salary));


        ResultSet rs = prst.executeQuery();

        while (rs.next()){
            System.out.println(rs.getString("first_name"));
        }


    }
}
