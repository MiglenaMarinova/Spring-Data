package diabloLab;

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

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/diablo", props);

        PreparedStatement stmt = connection.prepareStatement("SELECT users.first_name, users.last_name, COUNT(ug.game_id) FROM diablo.users\n" +
                "JOIN diablo.users_games ug on users.id = ug.user_id\n" +
                "WHERE users.user_name = ?\n" +
                "GROUP BY users.first_name, users.last_name");

        String userName = sc.nextLine();
        stmt.setString(1, userName);

        ResultSet rs = stmt.executeQuery();

        boolean isExist = rs.next();

        if(isExist){
            System.out.println("User: " + userName);
            System.out.printf("%s %s has played %d games",
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getInt(3));
        }else {
            System.out.println("No such user exists");
        }








    }
}
